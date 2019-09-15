package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.dao.PermissionDao;
import cn.ux.jxxt.dao.RoleDao;
import cn.ux.jxxt.dao.UserDao;
import cn.ux.jxxt.domain.Permission;
import cn.ux.jxxt.domain.Role;
import cn.ux.jxxt.domain.custom.UserCustom;
import cn.ux.jxxt.dto.RoleDTO;
import cn.ux.jxxt.service.RoleService;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.UxContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private LogDao logDao;

    /**
     * 添加角色
     * @param roleDTO
     * @return
     */
    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        RoleDTO returnDTO = new RoleDTO();
        Role role = roleDao.findRoleByName(roleDTO.getName());
        if (role != null) {
            roleDTO.setError("角色名已存在");
            return roleDTO;
        }
        roleDao.addRole(roleDTO);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("roleId", roleDTO.getId());
        for (Long permissionId : roleDTO.getPermissionsId()) {
            Permission permission = permissionDao.findPermissionById(permissionId);
            if (permission == null) {
                roleDTO.setError("查询不到id为" + permissionId + "的权限。");
                return roleDTO;
            }
            paramsMap.put("permissionId", permissionId);
            roleDao.addPermissionForRole(paramsMap);
        }
        returnDTO.setRoleCustom(roleDao.findRoleWithPermissionsById(roleDTO.getId()));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "添加角色，角色名为 : " + roleDTO.getName()));
        return returnDTO;
    }

    /**
     * 获取角色列表
     * @return
     */
    @Override
    public RoleDTO getRoles() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoles(roleDao.queryRoles());
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看所有角色"));
        return roleDTO;
    }

    /**
     * 角色添加权限
     * @param roleId
     * @param permissionsId
     * @return
     */
    @Override
    public RoleDTO addPermissionsForRole(Long roleId, List<Long> permissionsId) {
        RoleDTO roleDTO = new RoleDTO();
        Role role = roleDao.findRoleById(roleId);
        if (role == null) {
            roleDTO.setError("查询不到id为" + roleId + "的角色。");
            return roleDTO;
        }

        Map<String, Object> paramsMap = new HashMap<>();
        for (Long permissionId : permissionsId) {
            Permission permission = permissionDao.findPermissionById(permissionId);
            if (permission == null) {
                roleDTO.setError("查询不到id为" + permissionId + "的权限。");
                return roleDTO;
            }
            paramsMap.put("roleId", roleId);
            paramsMap.put("permissionId", permissionId);
            roleDao.addPermissionForRole(paramsMap);
        }
        roleDTO.setRoleCustom(roleDao.findRoleWithPermissionsById(roleId));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "给角色 : " + role.getName() +"添加权限"));
        return roleDTO;
    }

    /**
     * 移除角色某个权限
     * @param roleId
     * @param permissionId
     * @return
     */
    @Override
    public RoleDTO removePermissionFromRole(Long roleId, Long permissionId) {
        RoleDTO roleDTO = new RoleDTO();
        Role role = roleDao.findRoleById(roleId);
        if (role == null) {
            roleDTO.setError("查询不到id为" + roleId + "的角色。");
            return roleDTO;
        }

        Permission permission = permissionDao.findPermissionById(permissionId);
        if (permission == null) {
            roleDTO.setError("查询不到id为" + permissionId + "的权限。");
            return roleDTO;
        }
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("roleId", roleId);
        paramsMap.put("permissionId", permissionId);
        roleDao.removePermissionFromRole(paramsMap);
        roleDTO.setRoleCustom(roleDao.findRoleWithPermissionsById(roleId));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "移除角色 : " + role.getName() +"的权限"));
        return roleDTO;
    }

    /**
     * 根据角色id删除角色
     * @param roleId
     * @return
     */
    @Override
    public RoleDTO deleteRole(Long roleId) {
        RoleDTO roleDTO = new RoleDTO();
        Role role = roleDao.findRoleById(roleId);
        if(role == null){
            roleDTO.setError("查询不到id为" + roleId + "的角色。");
            return roleDTO;
        }
        roleDao.deleteRole(roleId);
        roleDao.deleteRoleUser(roleId);
        roleDTO.setSuccess("成功删除角色");
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "删除角色 : " + role.getName()));
        return roleDTO;
    }

    @Override
    public RoleDTO getRolesUnselected(Long userId) {
        RoleDTO roleDTO = new RoleDTO();
        UserCustom user = userDao.queryUserById(userId);
        if (user == null) {
            roleDTO.setError("查找不到该用户");
            return roleDTO;
        }
        roleDTO.setRoles(roleDao.findRolesUnselected(userId));
        return roleDTO;
    }

    @Override
    public RoleDTO getRoleNoPermission() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleList(roleDao.getRoles());
        return roleDTO;
    }
}
