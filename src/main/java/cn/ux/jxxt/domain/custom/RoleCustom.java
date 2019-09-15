package cn.ux.jxxt.domain.custom;

import cn.ux.jxxt.domain.Permission;
import cn.ux.jxxt.domain.Role;

import java.util.List;

public class RoleCustom extends Role {
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

}
