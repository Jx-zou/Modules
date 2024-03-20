package org.modules.reactive.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;

/**
 * response工具类
 *
 * @author Jx-zou
 */
public class ResponseUtils {

    private static final Logger log = LoggerFactory.getLogger(ResponseUtils.class);

    /**
     *
     * @param webFilterExchange 通道
     * @param result            data
     * @return              Mono<Void>
     */
    public static Mono<Void> jsonWriteAndFlushWith(WebFilterExchange webFilterExchange, ResponseEntity<String> result) {
        return jsonWriteAndFlushWith(webFilterExchange.getExchange().getResponse(), result);
    }

    /**
     *
     * @param serverWebExchange 通道
     * @param result            data
     * @return                  Mono<Void>
     */
    public static Mono<Void> jsonWriteAndFlushWith(ServerWebExchange serverWebExchange, ResponseEntity<String> result) {
        return jsonWriteAndFlushWith(serverWebExchange.getResponse(), result);
    }

    /**
     * 回写消息
     * @param response ServerHttpResponse
     * @param result 响应消息
     * @return  Mono<Void>
     */
    public static Mono<Void> jsonWriteAndFlushWith(ServerHttpResponse response, ResponseEntity<String> result) {
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        Mono<Mono<DataBuffer>> mono = Mono.empty();
        try {
            mono = Mono.just(ByteBufMono.just(response.bufferFactory().wrap(new ObjectMapper().writeValueAsBytes(result))));
        } catch (JsonProcessingException e) {
            log.error("json编译失败");
        }
        return response.writeAndFlushWith(mono);
    }

}
