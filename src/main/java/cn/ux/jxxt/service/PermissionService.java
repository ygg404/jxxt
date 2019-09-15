package cn.ux.jxxt.service;

import cn.ux.jxxt.dto.PermissionDTO;

public interface PermissionService {
    PermissionDTO getPermissionsUnselected(Long roleId);

    PermissionDTO getPermissions();
}
