package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.Permission;

import java.util.List;

public interface PermissionDao {
    List<Permission> queryUnselectedPermissions(Long roleId);

    Permission findPermissionById(Long permissionId);

    List<Permission> findPermissions();
}
