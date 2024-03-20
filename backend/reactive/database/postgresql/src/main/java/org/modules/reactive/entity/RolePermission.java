package org.modules.reactive.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modules.reactive.entity.abstracts.SnowflakeIdEntity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The type RolePermission.
 *
 * @author Jx-zou
 */
@Getter
@Setter
@ToString
@Table(name = "t_role_permission", schema = "auth")
public class RolePermission extends SnowflakeIdEntity {

    @Column
    private Long rid;
    @Column
    private Long pid;
    @Column
    private Boolean enabled;
}
