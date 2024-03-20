package org.modules.reactive.entity.abstracts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * The type SnowflakeIdEntity.
 *
 * @author Jx-zou
 */
@Setter
@Getter
public abstract class SnowflakeIdEntity extends BaseEntity<Long> {

    @Id
    private Long id;

    @Override
    public boolean isNew() {
        return getId() != null;
    }
}
