package org.modules.reactive.service;

import org.junit.jupiter.api.Test;
import org.modules.reactive.entity.Role;
import org.modules.reactive.entity.RolePermission;
import org.modules.reactive.repository.PermissionRepository;
import org.modules.reactive.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type RoleServiceTest.
 *
 * @author Jx-zou
 */
@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    @Test
    public void saveRoleAndPermissionTest() {
        Role role = new Role();
        role.setName("dba");

        RolePermission rp = new RolePermission();
        rp.setPid(1L);


    }
}
