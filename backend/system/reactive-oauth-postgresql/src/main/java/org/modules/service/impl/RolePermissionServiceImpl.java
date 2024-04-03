package org.modules.service.impl;

import org.modules.domain.bo.PermissionBO;
import org.modules.reactive.entity.RolePermission;
import org.modules.reactive.repository.RolePermissionRepository;
import org.modules.service.PermissionBOService;
import org.modules.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * The type RolePermissionServiceImpl.
 *
 * @author Jx-zou
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionBOService permissionBOService;

    @Autowired
    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository, PermissionBOService permissionBOService) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionBOService = permissionBOService;
    }

    @Override
    public Flux<PermissionBO> findPermissionByRid(Long rid) {
        return rolePermissionRepository.findAllByRid(rid)
                .collectList()
                .flatMapMany(rolePermissionList -> {
                    List<Long> pidList = new ArrayList<>();
                    for (RolePermission rolePermission : rolePermissionList) {
                        pidList.add(rolePermission.getPid());
                    }
                    return permissionBOService.findAllById(pidList);
                });
    }

    @Override
    public Mono<RolePermission> save(RolePermission rolePermission) {
        return permissionBOService.existsById(rolePermission.getPid())
                .flatMap(existed -> existed ? rolePermissionRepository.save(rolePermission) : Mono.empty());
    }

    @Override
    public Flux<RolePermission> saveAll(List<RolePermission> rolePermissionList) {
        List<Long> pidList = new ArrayList<>();
        for (RolePermission rolePermission : rolePermissionList) {
            pidList.add(rolePermission.getPid());
        }
        return permissionBOService.existsAllByIdIn(pidList)
                .flatMapMany(existed -> existed ? rolePermissionRepository.saveAll(rolePermissionList) : Flux.empty());
    }
}
