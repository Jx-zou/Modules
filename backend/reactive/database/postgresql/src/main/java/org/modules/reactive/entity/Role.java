package org.modules.reactive.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modules.reactive.enmus.BaseStatus;
import org.modules.reactive.entity.abstracts.SnowflakeIdEntity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The type Role.
 *
 * @author Jx-zou
 */
@Getter
@Setter
@ToString
@Table(name = "t_role", schema = "auth")
public class Role extends SnowflakeIdEntity {

    @Column
    private String name;
    @Column
    private Integer status = BaseStatus.NORMAL.getStatus();
    @Column
    private Boolean defaulted = false;
    @Column
    private Boolean enabled = true;
    @Column
    private String comments;
}
