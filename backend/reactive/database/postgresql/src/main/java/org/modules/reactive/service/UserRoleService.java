package org.modules.reactive.service;

import org.modules.reactive.entity.AccountRole;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * The type UserRoleService.
 *
 * @author Jx-zou
 */
public interface UserRoleService {

    Flux<AccountRole> findAll();
    Flux<AccountRole> findAllByUid(Long uid);
    Flux<AccountRole> findAllByRid(Long rid);
    Flux<AccountRole> updateBatch(List<AccountRole> userRole);
    Flux<AccountRole> addBatch(List<AccountRole> userRoles);
}
