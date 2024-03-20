package org.modules.reactive.service.impl;

import org.modules.reactive.entity.AccountRole;
import org.modules.reactive.repository.UserRoleRepository;
import org.modules.reactive.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * The type UserRoleServiceImpl.
 *
 * @author Jx-zou
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Flux<AccountRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public Flux<AccountRole> findAllByUid(Long aid) {
        return userRoleRepository.findAllByAid(aid);
    }

    @Override
    public Flux<AccountRole> findAllByRid(Long rid) {
        return userRoleRepository.findAllByRid(rid);
    }

    @Override
    public Flux<AccountRole> updateBatch(List<AccountRole> userRoles) {
        return userRoleRepository.saveAll(
                userRoleRepository.findAllById(
                        userRoles.stream().map(AccountRole::getId).toList()
                ).map(userRole -> {
                    for (AccountRole paramUserRole : userRoles) {
                        if (paramUserRole.getId() != null && paramUserRole.getId().equals(userRole.getId())) {
                            userRole.setAid(paramUserRole.getAid());
                            userRole.setRid(paramUserRole.getRid());
                        }
                    }
                    return userRole;
                })

        );
    }

    @Override
    public Flux<AccountRole> addBatch(List<AccountRole> userRoles) {
        return userRoleRepository.saveAll(userRoles);
    }
}
