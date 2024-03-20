package org.modules.reactive.repository;

import org.modules.reactive.entity.RolePermission;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * The type UserRoleRepository.
 *
 * @author Jx-zou
 */
@Repository
public interface RolePermissionRepository extends R2dbcRepository<RolePermission, Long> {

    Flux<RolePermission> findAllByRid(Long rid);
    Flux<RolePermission> findAllByPid(Long pid);
}
