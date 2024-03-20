package org.modules.reactive.entity.abstracts;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The type BaseEntity.
 *
 * @author Jx-zou
 */
public abstract class BaseEntity<ID> implements Auditable<ID, ID, LocalDateTime> {

    @CreatedBy
    private ID createBy;
    @LastModifiedBy
    private ID updateBy;
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;

    @NonNull
    @Override
    public Optional<ID> getCreatedBy() {
        return Optional.ofNullable(createBy);
    }

    @Override
    public void setCreatedBy(@NonNull ID createdBy) {
        this.createBy = createdBy;
    }

    @NonNull
    @Override
    public Optional<LocalDateTime> getCreatedDate() {
        return Optional.ofNullable(createTime);
    }

    @Override
    public void setCreatedDate(@NonNull LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @NonNull
    @Override
    public Optional<ID> getLastModifiedBy() {
        return Optional.ofNullable(updateBy);
    }

    @Override
    public void setLastModifiedBy(@NonNull ID updateBy) {
        this.updateBy = updateBy;
    }

    @NonNull
    @Override
    public Optional<LocalDateTime> getLastModifiedDate() {
        return Optional.ofNullable(updateTime);
    }

    @Override
    public void setLastModifiedDate(@NonNull LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
