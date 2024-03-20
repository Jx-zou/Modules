package org.modules.service.impl;

import org.modules.domain.bo.UserBO;
import org.modules.reactive.repository.AccountRepository;
import org.modules.reactive.service.PermissionService;
import org.modules.service.UserBOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * The type UserServiceImpl.
 *
 * @author Jx-zou
 */
@Service
public class UserBOServiceImpl implements UserBOService {

    private final AccountRepository accountRepository;
    private final PermissionService permissionService;

    @Autowired
    public UserBOServiceImpl(AccountRepository accountRepository, PermissionService permissionService) {
        this.accountRepository = accountRepository;
        this.permissionService = permissionService;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return accountRepository.findByUsername(username)
                .map(UserBO::new)
                .map(userBO -> (UserDetails)userBO)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("该用户未存在")));
    }
}
