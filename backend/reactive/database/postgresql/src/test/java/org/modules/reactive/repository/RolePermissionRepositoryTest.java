package org.modules.reactive.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * The type RolePermissionRepositoryTest.
 *
 * @author Jx-zou
 */
@SpringBootTest
public class RolePermissionRepositoryTest {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Test
    public void testBatchFindAll() {
        rolePermissionRepository.findAllByRidIn(Arrays.asList(1L, 2L))
                .collectList()
                .subscribe(System.out::println);
    }
}
