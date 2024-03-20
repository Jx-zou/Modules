package org.modules.reactive.repository;

import org.modules.reactive.entity.Account;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * The type UserRepository.
 *
 * @author Jx-zou
 */
@Repository
public interface AccountRepository extends R2dbcRepository<Account, Long> {

    Mono<Account> findByUsername(String username);
}
