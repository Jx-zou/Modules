package org.modules.reactive.pojo;


import org.modules.reactive.model.IdWorker;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * <p>名称：IdWorker.java</p>
 * <p>描述：分布式自增长ID</p>
 * <pre>
 *     Twitter的 Snowflake　JAVA实现方案
 * </pre>
 * 核心代码为其IdWorker这个类实现，其原理结构如下，我分别用一个0表示一位，用—分割开部分的作用：
 * 1||0---0000000000 0000000000 0000000000 0000000000 0 --- 00000 ---00000 ---000000000000
 * 在上面的字符串中，第一位为未使用（实际上也可作为long的符号位），接下来的41位为毫秒级时间，
 * 然后5位datacenter标识位，5位机器ID（并不算标识符，实际是为线程标识），
 * 然后12位该毫秒内的当前毫秒内的计数，加起来刚好64位，为一个Long型。
 * 这样的好处是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenter和机器ID作区分），
 * 并且效率较高，经测试，snowflake每秒能够产生26万ID左右，完全满足需要。
 * <p>
 * 64位ID (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加))
 *
 * @author Jx-zou
 */
public class SnowflakeIdWorker implements IdWorker<Long> {

    //时间起始标记点，作为 基准，一般取系统的最近时间（一旦确定不能变动）
    private static final long twepoch = 1651059075265L;
    //机器标识位数
    private static final long workerIdBits = 5L;
    //数据中心标识位数
    private static final long datacenterIdBits = 5L;
    //机器ID最大值
    private static final long maxWorkerId = ~(-1L << workerIdBits);
    //数据中心ID最大值
    private static final long maxDatacenterId = ~(-1L << datacenterIdBits);
    //毫秒内自增位
    private static final long sequenceBits = 12L;
    //机器ID偏左移12位
    private static final long workerIdShift = sequenceBits;
    //数据中心ID左移17位
    private static final long datacenterIdShift = sequenceBits + workerIdBits;
    //时间毫秒左移22位
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private static final long sequenceMask = ~(-1L << sequenceBits);
    //上次生产ID时间戳
    private static long lastTimestamp = -1L;
    //并发控制
    private long sequence = 0L;

    private final long workerId;
    //数据标识id部分
    private final long datacenterId;

    public SnowflakeIdWorker(){
        this.datacenterId = getDatacenterId(maxDatacenterId);
        this.workerId = getMaxWorkerId(datacenterId, maxWorkerId);
    }

    /**
     * @param workerId     工作机器ID
     * @param datacenterId 序列号
     */
    public SnowflakeIdWorker(long workerId, long datacenterId){
        if (workerId > maxWorkerId || workerId < 0){
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0){
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获取下一个id
     * @return long
     */
    @Override
    public synchronized Long nextId(){
        long timestamp = timeGen();
        if (timestamp < lastTimestamp){
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp){
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0){
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp){
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen(){
        return System.currentTimeMillis();
    }

    /**
     * <p>获取 maxWorkerId</p>
     */
    private long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuilder mpId = new StringBuilder();
        mpId.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()){
            mpId.append(name.split("@")[0]);
        }
        return (mpId.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }

    /**
     * <p>获取数据标识ID部分</p>
     */
    private long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try{
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null){
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1])
                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (maxDatacenterId + 1);
            }
        } catch (SocketException | UnknownHostException e) {
            System.out.println(" getDatacenterId: " + e.getMessage());
        }
        return id;
    }
}

