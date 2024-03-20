package org.modules.reactive.repository;

import org.modules.reactive.entity.PermissionResource;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * The type UserRoleRepository.
 *
 * @author Jx-zou
 */
@Repository
public interface PermissionResourceRepository extends R2dbcRepository<PermissionResource, Long> {

    Flux<PermissionResource> findAllByPid(Long pid);
    Flux<PermissionResource> findAllByRid(Long rid);
}
