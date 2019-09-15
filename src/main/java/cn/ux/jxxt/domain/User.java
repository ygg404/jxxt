package cn.ux.jxxt.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@ApiModel(description = "用户信息实体")
public class User {
    private Long id;
    @ApiModelProperty(value = "用户名称",name = "username",required = true)
    private String username;
    @NotBlank(message = "userAccount can not be null")
    @ApiModelProperty(value = "用户账号",name = "userAccount",required = true)
    private String userAccount;
    @ApiModelProperty(value = "用户密码",name = "password",required = true)
    private String password;
    @ApiModelProperty(value = "用户新密码，用于修改密码",name = "newPassword",required = true)
    private String newPassword;
    private Long roleId;
    @ApiModelProperty(value = "用户所属组id",name = "groupId",required = true)
    private Long groupId;
    @ApiModelProperty(value = "用户角色id",name = "roleIds",required = true)
    private List<Long> roleIds;
    private List<Long> groupIds;
    private String roleName;
    private String groupName;
    private Timestamp lastPasswordResetDateTime;
    private Timestamp registerDateTime;
    private Timestamp lastLoginTime;
    private Boolean enabled;
    private String status;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastPasswordResetDateTime() {
        return lastPasswordResetDateTime;
    }

    public void setLastPasswordResetDateTime(Timestamp lastPasswordResetDateTime) {
        this.lastPasswordResetDateTime = lastPasswordResetDateTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(Timestamp registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", lastPasswordResetDateTime="
                + lastPasswordResetDateTime + ", enabled=" + enabled + ", status=" + status + ", type=" + type + "]";
    }
}
