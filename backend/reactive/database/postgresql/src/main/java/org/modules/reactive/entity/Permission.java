package org.modules.reactive.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modules.reactive.enmus.BaseStatus;
import org.modules.reactive.entity.abstracts.SnowflakeIdEntity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.StringJoiner;

/**
 * The type Permission.
 *
 * @author Jx-zou
 */
@Getter
@Setter
@ToString
@Table(name = "t_permission", schema = "auth")
public class Permission extends SnowflakeIdEntity {

    @Column
    private String name;
    @Column
    private String pattern;
    @Column
    private String method;
    @Column
    private Integer status = BaseStatus.NORMAL.getStatus();
    @Column
    private Boolean enabled = true;
    @Column
    private String comments;

    @Override
    public String toString() {
        return new StringJoiner(", ", Permission.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("pattern='" + pattern + "'")
                .add("method='" + method + "'")
                .add("status=" + status)
                .add("enabled=" + enabled)
                .add("id=" + super.getId())
                .add("createBy=" + super.getCreateBy())
                .add("updateBy=" + super.getUpdateBy())
                .add("createTime=" + super.getCreateTime())
                .add("updateTime=" + super.getUpdateTime())
                .toString();
    }
}
