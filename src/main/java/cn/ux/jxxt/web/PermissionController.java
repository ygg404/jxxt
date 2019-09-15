package cn.ux.jxxt.web;

import cn.ux.jxxt.dto.PermissionDTO;
import cn.ux.jxxt.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping(value = "/permissions/", params = {"not_in"})
    public ResponseEntity<?> getPermissionsUnselected(@RequestParam("not_in") Long roleId) {
        PermissionDTO returnDTO = permissionService.getPermissionsUnselected(roleId);
        return ResponseEntity.ok(returnDTO.getPermissions());
    }

    @GetMapping(value = "/permissions/")
    public ResponseEntity<?> getPermissions() {
        PermissionDTO returnDTO = permissionService.getPermissions();
        return ResponseEntity.ok(returnDTO.getPermissions());
    }
}
