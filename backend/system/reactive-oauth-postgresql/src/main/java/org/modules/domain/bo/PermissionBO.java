package org.modules.domain.bo;

import lombok.Getter;
import lombok.Setter;
import org.modules.reactive.entity.Permission;
import org.modules.reactive.entity.Resource;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * The type PermissionBO.
 *
 * @author Jx-zou
 */
@Setter
@Getter
public class PermissionBO implements GrantedAuthority {

    private Permission permission;
    private List<Resource> resources;

    public PermissionBO(Permission permission) {
        this.permission = permission;
    }

    public PermissionBO(Permission permission, List<Resource> resources) {
        this.permission = permission;
        this.resources = resources;
    }

    @Override
    public String getAuthority() {
        return permission.getName();
    }
}
