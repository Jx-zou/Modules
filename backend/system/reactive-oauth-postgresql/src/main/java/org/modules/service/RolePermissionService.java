package org.modules.service;

import org.modules.domain.bo.PermissionBO;
import org.modules.reactive.entity.RolePermission;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The type RolePermissionService.
 *
 * @author Jx-zou
 */
public interface RolePermissionService {
    Flux<PermissionBO> findPermissionByRid(Long rid);

    Mono<RolePermission> save(RolePermission rolePermission);

    Flux<RolePermission> saveAll(List<RolePermission> rolePermissionList);
}
