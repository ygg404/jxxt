package cn.ux.jxxt.service;

import cn.ux.jxxt.dao.RoleDao;
import cn.ux.jxxt.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO addRole(RoleDTO roleDTO);

    RoleDTO getRoles();

    RoleDTO addPermissionsForRole(Long roleId, List<Long> permissionsId);

    RoleDTO removePermissionFromRole(Long roleId, Long permissionId);

    RoleDTO deleteRole(Long roleId);

    RoleDTO getRolesUnselected(Long userId);

    RoleDTO getRoleNoPermission();
}
