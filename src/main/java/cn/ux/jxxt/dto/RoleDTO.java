package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.Role;
import cn.ux.jxxt.domain.custom.RoleCustom;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class RoleDTO extends BasicDTO {
    private Long id;
    @NotBlank(message = "name不能为空")
    private String name;
    private Role role;
    private List<RoleCustom> roles;
    private List<Long> permissionsId;
    private RoleCustom roleCustom;
    private List<Role> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<RoleCustom> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleCustom> roles) {
        this.roles = roles;
    }

    public List<Long> getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(List<Long> permissionsId) {
        this.permissionsId = permissionsId;
    }

    public RoleCustom getRoleCustom() {
        return roleCustom;
    }

    public void setRoleCustom(RoleCustom roleCustom) {
        this.roleCustom = roleCustom;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
