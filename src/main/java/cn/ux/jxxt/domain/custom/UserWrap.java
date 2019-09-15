package cn.ux.jxxt.domain.custom;

import cn.ux.jxxt.domain.WorkGroup;

import java.util.List;

public class UserWrap {
    private Long id;
    private String userName;
    private String role;
    private String group;
    private String lastLoginTime;
    private String userAccount;
    private List<RoleCustom> roleId;
    private List<WorkGroup> groupId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public List<RoleCustom> getRoleId() {
        return roleId;
    }

    public void setRoleId(List<RoleCustom> roleId) {
        this.roleId = roleId;
    }

    public List<WorkGroup> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<WorkGroup> groupId) {
        this.groupId = groupId;
    }
}
