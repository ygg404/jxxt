package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.PermissionDao;
import cn.ux.jxxt.dto.PermissionDTO;
import cn.ux.jxxt.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PermissionDTO getPermissionsUnselected(Long roleId) {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setPermissions(permissionDao.queryUnselectedPermissions(roleId));
        return permissionDTO;
    }

    @Override
    public PermissionDTO getPermissions() {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setPermissions(permissionDao.findPermissions());
        return permissionDTO;
    }
}
