package org.modules.service;

import org.modules.domain.bo.RoleBO;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The type RoleBOService.
 *
 * @author Jx-zou
 */
public interface RoleBOService {

    Mono<RoleBO> findById(Long id);
    Mono<RoleBO> findByName(String name);
    Flux<RoleBO> findAll();
    Flux<RoleBO> findAllById(List<Long> ids);
    Mono<RoleBO> save(RoleBO roleBO);
    Flux<RoleBO> saveAll(List<RoleBO> roleBOList);
}
