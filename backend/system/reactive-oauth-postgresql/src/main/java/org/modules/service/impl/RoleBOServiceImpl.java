package org.modules.service.impl;

import org.modules.domain.bo.PermissionBO;
import org.modules.domain.bo.RoleBO;
import org.modules.reactive.entity.Role;
import org.modules.reactive.entity.RolePermission;
import org.modules.reactive.repository.RoleRepository;
import org.modules.service.RoleBOService;
import org.modules.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type RoleBOServiceImpl.
 *
 * @author Jx-zou
 */
@Service
public class RoleBOServiceImpl implements RoleBOService {

    private final RoleRepository roleRepository;
    private final RolePermissionService rolePermissionService;

    @Autowired
    public RoleBOServiceImpl(RoleRepository roleRepository, RolePermissionService rolePermissionService) {
        this.roleRepository = roleRepository;
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    public Mono<RoleBO> findById(Long rid) {
        return roleRepository.findById(rid)
                .flatMap(role -> rolePermissionService.findPermissionByRid(rid)
                        .collectList()
                        .map(permissionBOList -> new RoleBO(role, permissionBOList))
                );
    }

    @Override
    public Mono<RoleBO> findByName(String name) {
        return roleRepository.findByName(name)
                .flatMap(role ->
                        rolePermissionService.findPermissionByRid(role.getId())
                                .collectList()
                                .map(permissionBOList -> new RoleBO(role, permissionBOList))
                );
    }

    @Override
    public Flux<RoleBO> findAll() {
        return roleRepository.findAll()
                .flatMap(role ->
                        rolePermissionService.findPermissionByRid(role.getId())
                                .collectList()
                                .map(permissionBOList -> new RoleBO(role, permissionBOList))
                );
    }

    @Override
    public Flux<RoleBO> findAllById(List<Long> ids) {
        return roleRepository.findAllById(ids)
                .flatMap(role ->
                        rolePermissionService.findPermissionByRid(role.getId())
                                .collectList()
                                .map(permissionBOList -> new RoleBO(role, permissionBOList))
                );
    }

    @Transactional
    @Override
    public Mono<RoleBO> save(RoleBO roleBO) {
        return roleRepository.save(roleBO.getRole())
                        .flatMap(targetRole -> {
                            List<RolePermission> rolePermissionList = new ArrayList<>();
                            for (PermissionBO permissionBO : roleBO.getPermissionBOList()) {
                                rolePermissionList.add(new RolePermission(targetRole.getId(), permissionBO.getPermission().getId()));
                            }
                            return rolePermissionService.saveAll(rolePermissionList)
                                    .collectList()
                                    .switchIfEmpty(Mono.empty())
                                    .thenReturn(roleBO);
                        });
    }

    @Transactional
    @Override
    public Flux<RoleBO> saveAll(List<RoleBO> roleBOList) {
        List<Role> roleList = new ArrayList<>();
        for (RoleBO roleBO : roleBOList) {
            roleList.add(roleBO.getRole());
        }

        List<RolePermission> rolePermissionList = new ArrayList<>();
        for (RoleBO roleBO : roleBOList) {
            for (PermissionBO permissionBO : roleBO.getPermissionBOList()) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRid(roleBO.getRole().getId());
                rolePermission.setPid(permissionBO.getPermission().getId());
                rolePermissionList.add(rolePermission);
            }
        }

        return roleRepository.saveAll(roleList)
                .collectList()
                .flatMapMany(sourceRoleList ->
                        rolePermissionService.saveAll(rolePermissionList)
                                .switchIfEmpty(Flux.empty())
                                .collectList()
                                .thenMany(Flux.fromIterable(roleBOList))
                );
    }


}
