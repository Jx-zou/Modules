package org.modules.reactive.repository;

import org.modules.reactive.entity.Role;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * The type RoleRepository.
 *
 * @author Jx-zou
 */
@Repository
public interface RoleRepository extends R2dbcRepository<Role, Long> {

    Mono<Role> findByName(String name);
}
