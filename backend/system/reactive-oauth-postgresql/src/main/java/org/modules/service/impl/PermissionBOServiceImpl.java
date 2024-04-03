package org.modules.service.impl;

import org.modules.domain.bo.PermissionBO;
import org.modules.reactive.entity.Permission;
import org.modules.reactive.repository.PermissionRepository;
import org.modules.reactive.util.BeanUtils;
import org.modules.service.PermissionBOService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * The type PermissionBOServiceImpl.
 *
 * @author Jx-zou
 */
@Service
public class PermissionBOServiceImpl implements PermissionBOService {

    private final PermissionRepository permissionRepository;

    public PermissionBOServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Flux<PermissionBO> findAll() {
        return permissionRepository.findAll().map(PermissionBO::new);
    }

    @Override
    public Flux<PermissionBO> findAllById(List<Long> ids) {
        return permissionRepository.findAllById(ids).map(PermissionBO::new);
    }

    @Override
    public Mono<PermissionBO> findById(Long id) {
        return permissionRepository.findById(id).map(PermissionBO::new);
    }

    @Override
    public Mono<PermissionBO> findByName(String name) {
        return permissionRepository.findByName(name).map(PermissionBO::new);
    }

    @Transactional
    @Override
    public Mono<PermissionBO> save(PermissionBO permissionBO) {
        Permission permission = permissionBO.getPermission();
        return permission.getId() == null ?
                permissionRepository.save(permission)
                        .map(PermissionBO::new) :
                permissionRepository.findById(permission.getId())
                        .flatMap(targetPermission ->
                                permissionRepository.save(BeanUtils.merge(permission, targetPermission))
                                        .map(PermissionBO::new)
                        );
    }

    @Transactional
    @Override
    public Flux<PermissionBO> saveAll(List<PermissionBO> permissionBOList) {
        List<Permission> permissionList = new ArrayList<>();
        List<Long> pidList = new ArrayList<>();
        for (PermissionBO permissionBO : permissionBOList) {
            Permission permission = permissionBO.getPermission();
            permissionList.add(permission);
            if (!permission.isNew()) {
                pidList.add(permission.getId());
            }
        }
        return pidList.isEmpty() ?
                permissionRepository.saveAll(permissionList)
                        .map(PermissionBO::new) :
                permissionRepository.findAllById(pidList)
                        .collectList()
                        .flatMapMany(targetPermissionList ->
                                permissionRepository.saveAll(
                                        BeanUtils.mergeList(permissionList, targetPermissionList,
                                                (s, t) -> s.getId() != null && s.getId().equals(t.getId()))))
                        .map(PermissionBO::new);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return permissionRepository.existsById(id);
    }

    @Override
    public Mono<Boolean> existsAllByIdIn(List<Long> ids) {
        return permissionRepository.existsAllByIdIn(ids);
    }

    @Transactional
    @Override
    public Mono<Void> delete(Long id) {
        return permissionRepository.existsById(id)
                .flatMap(existed -> existed ? permissionRepository.deleteById(id) : Mono.empty());
    }

    @Transactional
    @Override
    public Mono<Void> deleteAll(List<Long> ids) {
        return permissionRepository.existsAllByIdIn(ids)
                .flatMap(existed -> existed ? permissionRepository.deleteAllById(ids) : Mono.empty());
    }
}
