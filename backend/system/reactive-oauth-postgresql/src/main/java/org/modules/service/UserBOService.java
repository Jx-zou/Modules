package org.modules.service;

import org.modules.domain.bo.UserBO;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Mono;

/**
 * The type UserBOService.
 *
 * @author Jx-zou
 */
public interface UserBOService extends ReactiveUserDetailsService {
    Mono<UserBO> findById(Long id);
}
