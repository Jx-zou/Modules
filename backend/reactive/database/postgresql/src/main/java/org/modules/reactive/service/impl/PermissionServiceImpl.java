package org.modules.reactive.service.impl;

import org.modules.reactive.repository.PermissionRepository;
import org.modules.reactive.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * The type PermissionServiceImpl.
 *
 * @author Jx-zou
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
}
