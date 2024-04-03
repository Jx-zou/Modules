package org.modules.reactive.repository;

import org.modules.reactive.entity.RolePermission;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * The type UserRoleRepository.
 *
 * @author Jx-zou
 */
@Repository
public interface RolePermissionRepository extends R2dbcRepository<RolePermission, Long> {

    Flux<RolePermission> findAllByRid(Long rid);
    Flux<RolePermission> findAllByRidIn(List<Long> ids);
    Flux<RolePermission> findAllByPid(Long pid);
}
