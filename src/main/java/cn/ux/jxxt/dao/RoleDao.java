package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.Role;
import cn.ux.jxxt.domain.custom.RoleCustom;
import cn.ux.jxxt.dto.RoleDTO;

import java.util.List;
import java.util.Map;

public interface RoleDao {
    Role findRoleByName(String name);

    Role findRoleById(Long roleId);

    RoleCustom findRoleWithPermissionsById(Long roleId);

    List<RoleCustom> queryRoles();

    int addRole(RoleDTO roleDTO);

    int addPermissionForRole(Map<String, Object> paramsMap);

    int removePermissionFromRole(Map<String, Object> paramsMap);

    int deleteRole(Long roleId);

    List<RoleCustom> findRolesUnselected(Long userId);

    List<Role> getRoles();

    int deleteRoleUser(Long roleId);
}
