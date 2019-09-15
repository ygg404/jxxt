package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.Permission;

import java.util.List;

public class PermissionDTO extends BasicDTO {
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
