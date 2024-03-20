package org.modules.reactive.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modules.reactive.entity.abstracts.SnowflakeIdEntity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The type Resource.
 *
 * @author Jx-zou
 */
@Getter
@Setter
@ToString
@Table(name = "t_resource", schema = "auth")
public class Resource extends SnowflakeIdEntity {

    @Column
    private String name;
    @Column
    private String pattern;
    @Column
    private String method;
    @Column
    private Byte type;
    @Column
    private Byte status;
    @Column
    private Boolean enabled;
    @Column
    private String comments;
}
