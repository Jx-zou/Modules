package org.modules.service;

import org.modules.domain.bo.PermissionBO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The type PermissionBOService.
 *
 * @author Jx-zou
 */
public interface PermissionBOService {

    Flux<PermissionBO> findAll();
    Flux<PermissionBO> findAllById(List<Long> ids);
    Mono<PermissionBO> findById(Long id);
    Mono<PermissionBO> findByName(String name);
    Mono<PermissionBO> save(PermissionBO permissionBO);
    Flux<PermissionBO> saveAll(List<PermissionBO> permissionBOList);
    Mono<Boolean> existsById(Long id);
    Mono<Boolean> existsAllByIdIn(List<Long> ids);
    Mono<Void> delete(Long id);
    Mono<Void> deleteAll(List<Long> ids);
}
