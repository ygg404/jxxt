package cn.ux.jxxt.web;

import cn.ux.jxxt.dto.RoleDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.service.RoleService;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private GeneralMessage message;

    @Autowired
    private GeneralMessage generalMessage;

    /**
     * 添加角色
     * @param roleDTO
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/roles/")
    public ResponseEntity<?> addRole(@Valid @RequestBody RoleDTO roleDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("添加角色失败", bindingResult);
        }
        RoleDTO returnDTO = roleService.addRole(roleDTO);
        if (returnDTO.getRoleCustom() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getRoleCustom());
        } else {
            generalMessage.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(generalMessage);
        }
    }

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping(value = "/get/roles/")
    @CrossOrigin
    public ResponseEntity<?> getRoles() {
        RoleDTO returnDTO = roleService.getRoles();
        return ResponseEntity.ok(returnDTO.getRoles());
    }

    /**
     * 查找该用户未拥有的角色身份
     * @param userId
     * @return
     */
    @GetMapping(value = "/roles/",params = {"not_in"})
    public ResponseEntity<?> getRolesUnselected(@RequestParam("not_in") Long userId) {
        RoleDTO returnDTO = roleService.getRolesUnselected(userId);
        if (returnDTO.getError() == null){
            return ResponseEntity.ok(returnDTO.getRoles());
        }else{
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 角色添加权限
     * @param roleId
     * @param roleDTO
     * @return
     */
    @PostMapping(value = "/roles/{roleId}/permissions/")
    public ResponseEntity<?> addPermissionForRole(@PathVariable("roleId") Long roleId,@RequestBody RoleDTO roleDTO){
        RoleDTO returnDTO = roleService.addPermissionsForRole(roleId,roleDTO.getPermissionsId());
        if(returnDTO.getError() == null){
            return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO.getRoleCustom());
        }else{
            generalMessage.setMessage(returnDTO.getError());
            return ResponseEntity.ok(generalMessage);
        }
    }

    /**
     * 移除角色权限
     * @param roleId
     * @param permissionId
     * @return
     */
    @DeleteMapping(value = "/roles/{roleId}/permissions/{permissionId}")
    public ResponseEntity<?> removePermissionFromRole(@PathVariable("roleId") Long roleId, @PathVariable("permissionId") Long permissionId){
        RoleDTO returnDTO = roleService.removePermissionFromRole(roleId,permissionId);
        if(returnDTO.getError() == null){
            return ResponseEntity.ok().body(returnDTO.getRoleCustom());
        }else{
            generalMessage.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(generalMessage);
        }
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @DeleteMapping(value = "/roles/{roleId}/")
    public ResponseEntity<?> deleteRole(@PathVariable("roleId") Long roleId){
        RoleDTO returnDTO = roleService.deleteRole(roleId);
        if(returnDTO.getError() == null){
            generalMessage.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(generalMessage);
        }else{
            generalMessage.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(generalMessage);
        }
    }

    /**
     * 获取不带权限的角色数据
     * @return
     */
    @GetMapping(value = "/roles/")
    public ResponseEntity<?> getRoleNoPermission(){
        RoleDTO returnDTO = roleService.getRoleNoPermission();
        return ResponseEntity.ok(returnDTO.getRoleList());
    }
}
