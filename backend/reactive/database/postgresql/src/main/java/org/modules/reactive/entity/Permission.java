package org.modules.reactive.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modules.reactive.entity.abstracts.SnowflakeIdEntity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The type Permission.
 *
 * @author Jx-zou
 */
@Getter
@Setter
@ToString
@Table(name = "t_perm", schema = "auth")
public class Permission extends SnowflakeIdEntity {

    @Column
    private String name;
    @Column
    private Byte status;
    @Column
    private Boolean enabled;
    @Column
    private String comments;
}
