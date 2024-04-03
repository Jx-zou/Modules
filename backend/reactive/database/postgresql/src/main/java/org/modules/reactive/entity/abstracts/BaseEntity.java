package org.modules.reactive.entity.abstracts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * The type BaseEntity.
 *
 * @author Jx-zou
 */
@Getter
@Setter
@ToString
public abstract class BaseEntity<ID> {

    @CreatedBy
    private ID createBy;
    @LastModifiedBy
    private ID updateBy;
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;
}
