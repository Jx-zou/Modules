package org.modules.reactive.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modules.reactive.entity.Permission;
import org.modules.reactive.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The type PermissionRepositoryTest.
 *
 * @author Jx-zou
 */
@SpringBootTest
public class PermissionRepositoryTest {

    @Autowired
    private PermissionRepository permissionRepository;

    private final Permission p1 = new Permission();
    private final Permission p2 = new Permission();

    @BeforeEach
    public void init() {
        p1.setId(256158256207073280L);
        p1.setName("saveUser");
        p1.setPattern("/user");
        p1.setMethod("POST");

        p2.setName("getUser");
        p2.setPattern("/user/{id}");
        p2.setMethod("GET");
    }

    @Test
    public void saveTest() {
        (p1.getId() == null ?
                permissionRepository.save(p1) :
                permissionRepository.findById(p1.getId())
                        .flatMap(sourcePermission ->
                                permissionRepository.save(BeanUtils.merge(sourcePermission, p1))
                        )
        )
                .subscribe(System.out::println);
    }

    @Test
    public void saveAllTest() {
        List<Permission> plist = Arrays.asList(p1, p2);
        List<Long> ids = new ArrayList<>();
        for (Permission p : plist) {
            if (!p.isNew()) {
                ids.add(p.getId());
            }
        }

        (ids.isEmpty() ?
                permissionRepository.saveAll(plist)
                        .collectList() :
                permissionRepository.findAllById(ids)
                        .collectList()
                        .flatMap(targetPermissions ->
                                permissionRepository.saveAll(BeanUtils.mergeList(plist, targetPermissions, (t, s) ->
                                        t.getId() != null && t.getId().equals(s.getId())
                                )).collectList()
                        )
        )
                .subscribe(System.out::println);
    }
}
