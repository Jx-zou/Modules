package org.modules.reactive.service;

import org.modules.reactive.entity.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The type RoleService.
 *
 * @author Jx-zou
 */
public interface RoleService {

    Mono<Role> findById(Long id);
    Mono<Role> findByName(String name);
    Mono<Role> save(Role role);
    Flux<Role> saveAll(List<Role> roles);
    Flux<Role> findAll();
}
