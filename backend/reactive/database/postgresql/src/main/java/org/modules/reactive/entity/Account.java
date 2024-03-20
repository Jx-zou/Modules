package org.modules.reactive.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modules.reactive.entity.abstracts.SnowflakeIdEntity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The type User.
 *
 * @author Jx-zou
 */
@Getter
@Setter
@ToString
@Table(name = "t_account", schema = "auth")
public class Account extends SnowflakeIdEntity {

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private Boolean accountNonExpired;
    @Column
    private Boolean accountNonLocked;
    @Column
    private Boolean credentialsNonExpired;
    @Column
    private Boolean enabled;
}
