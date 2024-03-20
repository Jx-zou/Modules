package org.modules.reactive.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modules.reactive.entity.abstracts.SnowflakeIdEntity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The type PermissionResource.
 *
 * @author Jx-zou
 */
@Getter
@Setter
@ToString
@Table(name = "t_perm_resource", schema = "auth")
public class PermissionResource extends SnowflakeIdEntity {

    @Column
    private Long pid;
    @Column
    private Long rid;
    @Column
    private Boolean enabled;
}
