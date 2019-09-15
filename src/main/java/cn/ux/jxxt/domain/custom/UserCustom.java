package cn.ux.jxxt.domain.custom;

import cn.ux.jxxt.domain.User;

import java.util.List;

public class UserCustom extends User {
    private List<RoleCustom> roles;

    public List<RoleCustom> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleCustom> roles) {
        this.roles = roles;
    }
}
