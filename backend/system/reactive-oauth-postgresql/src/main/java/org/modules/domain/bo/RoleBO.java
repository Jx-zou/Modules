package org.modules.domain.bo;

import lombok.Getter;
import lombok.Setter;
import org.modules.reactive.entity.Role;

import java.util.List;

/**
 * The type RoleBO.
 *
 * @author Jx-zou
 */
@Getter
@Setter
public class RoleBO {

    private Role role;
    private List<PermissionBO> permissionBOList;

    public RoleBO(Role role) {
        this.role = role;
    }

    public RoleBO(Role role, List<PermissionBO> permissionBOList) {
        this.role = role;
        this.permissionBOList = permissionBOList;
    }
}
