package org.modules.reactive.service.impl;

import org.modules.reactive.entity.Account;
import org.modules.reactive.repository.AccountRepository;
import org.modules.reactive.service.UserService;
import org.modules.reactive.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * The type UserServiceImpl.
 *
 * @author Jx-zou
 */
@Service
public class UserServiceImpl implements UserService {

    private final AccountRepository userRepository;

    @Autowired
    public UserServiceImpl(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<Account> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<Account> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public Flux<Account> saveAll(List<Account> users) {
        List<Long> ids = new ArrayList<>();
        for (Account user : users) {
            if (user.getId() != null) {
                ids.add(user.getId());
            }
        }
        return ids.isEmpty() ?
                userRepository.saveAll(users) :
                userRepository.findAllById(ids).flatMap(targetUser ->
                        userRepository.saveAll(users.stream().map(user ->
                                user.getId() != null && user.getId().equals(targetUser.getId())
                                        ? BeanUtils.merge(user, targetUser) : targetUser
                        ).toList())
                );
    }

    @Transactional
    @Override
    public Mono<Account> save(Account user) {
        return user.getId() == null ?
                userRepository.save(user) :
                userRepository.findById(user.getId())
                        .flatMap(targetUser -> userRepository.save(BeanUtils.merge(user, targetUser)));
    }
}
