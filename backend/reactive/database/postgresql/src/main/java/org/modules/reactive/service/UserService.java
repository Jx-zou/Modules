package org.modules.reactive.service;

import org.modules.reactive.entity.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The type UserService.
 *
 * @author Jx-zou
 */
public interface UserService {

    Mono<Account> findById(Long id);
    Mono<Account> findByUsername(String username);
    Flux<Account> saveAll(List<Account> users);
    Mono<Account> save(Account user);
}
