package org.modules.reactive.repository;

import org.modules.reactive.entity.AccountRole;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * The type UserRoleRepository.
 *
 * @author Jx-zou
 */
@Repository
public interface UserRoleRepository extends R2dbcRepository<AccountRole, Long> {

    Flux<AccountRole> findAllByAid(Long aid);
    Flux<AccountRole> findAllByRid(Long rid);
}
