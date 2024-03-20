package org.modules.reactive.config;

import org.modules.reactive.entity.abstracts.SnowflakeIdEntity;
import org.modules.reactive.pojo.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

/**
 * The type EntityAuditingConfigure.
 *
 * @author Jx-zou
 */
@EnableR2dbcAuditing
@Configuration
public class EntityConfigure {

//    @Bean
//    ReactiveAuditorAware<String> auditorAware() {
//        return () -> Mono.just("ADMIN");
//    }

    @Order(999)
    @Bean
    BeforeConvertCallback<SnowflakeIdEntity> snowflakeIDEntityBeforeSaveCallback() {
        return (@NonNull SnowflakeIdEntity entity, @NonNull SqlIdentifier table) -> {
            if (entity.isNew()) {
                entity.setId(new SnowflakeIdWorker().nextId());
            }
            return Mono.just(entity);
        };
    }
}
