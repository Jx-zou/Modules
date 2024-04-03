package org.modules.reactive.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modules.reactive.entity.abstracts.BaseEntity;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The type UserRole.
 *
 * @author Jx-zou
 */
@Getter
@Setter
@ToString
@Table(name = "t_account_role", schema = "auth")
public class AccountRole extends BaseEntity<Long> {

    @Column
    private Long aid;
    @Column
    private Long rid;
    @Column
    private Boolean enabled;
}
