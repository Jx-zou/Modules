package org.modules.service.impl;

import org.modules.domain.bo.UserBO;
import org.modules.reactive.repository.AccountRepository;
import org.modules.reactive.repository.UserRoleRepository;
import org.modules.service.RoleBOService;
import org.modules.service.UserBOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserRoleRepository userRoleRepository;
    private final RoleBOService roleBOService;

    @Autowired
    public UserBOServiceImpl(AccountRepository accountRepository, UserRoleRepository userRoleRepository, RoleBOService roleBOService) {
        this.accountRepository = accountRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleBOService = roleBOService;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return accountRepository.findByUsername(username)
                .flatMap(account ->
                        userRoleRepository.findAllByAid(account.getId())
                                .flatMap(accountRole -> roleBOService.findById(accountRole.getRid()))
                                .collectList()
                                .map(roleBOList -> new UserBO(account, roleBOList)));
    }

    @Override
    public Mono<UserBO> findById(Long id) {
        return accountRepository.findById(id)
                .flatMap(account ->
                        userRoleRepository.findAllByAid(account.getId())
                                .flatMap(accountRole -> roleBOService.findById(accountRole.getRid()))
                                .collectList()
                                .map(roleBOList -> new UserBO(account, roleBOList)));
    }
}
