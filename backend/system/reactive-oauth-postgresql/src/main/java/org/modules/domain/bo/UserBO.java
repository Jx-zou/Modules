package org.modules.domain.bo;

import lombok.Getter;
import lombok.Setter;
import org.modules.reactive.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * The type UserBO.
 *
 * @author Jx-zou
 */
@Getter
@Setter
public class UserBO implements UserDetails {

    public UserBO(Account account) {
        this.account = account;
    }

    public UserBO(Account account, List<RoleBO> roleBOList) {
        this.account = account;
        this.roleBOList = roleBOList;
    }

    private Account account;
    private List<RoleBO> roleBOList;

    @Override
    public List<PermissionBO> getAuthorities() {
        List<PermissionBO> permissionBOList = new ArrayList<>();
        for (RoleBO roleBO : this.roleBOList) {
            permissionBOList.addAll(roleBO.getPermissionBOList());
        }
        return permissionBOList;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
