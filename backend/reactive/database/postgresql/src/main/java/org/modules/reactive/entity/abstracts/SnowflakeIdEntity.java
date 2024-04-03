package org.modules.reactive.entity.abstracts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

/**
 * The type SnowflakeIdEntity.
 *
 * @author Jx-zou
 */
@Setter
@Getter
@ToString
public abstract class SnowflakeIdEntity extends BaseEntity<Long> implements Persistable<Long> {

    @Id
    private Long id;

    @Override
    public boolean isNew() {
        return getId() == null;
    }
}
