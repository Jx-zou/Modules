package org.modules.reactive.repository;

import org.modules.reactive.entity.Permission;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * The type PermissionRepository.
 *
 * @author Jx-zou
 */
@Repository
public interface PermissionRepository extends R2dbcRepository<Permission, Long> {

    Mono<Permission> findByName(String name);
}
