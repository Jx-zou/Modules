package org.modules.domain.bo;

import lombok.Getter;
import lombok.Setter;
import org.modules.reactive.entity.Permission;
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

    public PermissionBO(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String getAuthority() {
        return permission.getName();
    }
}
