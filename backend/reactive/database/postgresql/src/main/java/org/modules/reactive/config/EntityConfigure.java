package org.modules.reactive.config;

import org.modules.reactive.entity.abstracts.SnowflakeIdEntity;
import org.modules.reactive.pojo.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import reactor.core.publisher.Mono;

/**
 * The type EntityConfigure.
 *
 * @author Jx-zou
 */
@EnableR2dbcAuditing
@Configuration
public class EntityConfigure {

    @Bean
    public ReactiveAuditorAware<Long> accountReactiveAuditorAware() {
        return () -> Mono.just(1L);
    }

    @Bean
    public BeforeConvertCallback<SnowflakeIdEntity> SnowflakeIdEntityBeforeSaveCallback() {
        return (SnowflakeIdEntity entity, SqlIdentifier table) -> {
            if (entity.isNew()) {
                entity.setId(new SnowflakeIdWorker().nextId());
            }
            return Mono.just(entity);
        };
    }
}
