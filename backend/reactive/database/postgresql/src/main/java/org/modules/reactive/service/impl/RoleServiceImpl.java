package org.modules.reactive.service.impl;

import org.modules.reactive.entity.Role;
import org.modules.reactive.repository.RoleRepository;
import org.modules.reactive.service.RoleService;
import org.modules.reactive.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The type RoleServiceImpl.
 *
 * @author Jx-zou
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Flux<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Mono<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Mono<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }


    @Transactional
    @Override
    public Mono<Role> save(Role role) {
        return role.getId() == null ?
                roleRepository.save(role) :
                roleRepository.findById(role.getId())
                        .flatMap(targetRole -> roleRepository.save(BeanUtils.merge(role, targetRole)));
    }

    @Transactional
    @Override
    public Flux<Role> saveAll(List<Role> roles) {
        return roleRepository.saveAll(roles);
    }
}
