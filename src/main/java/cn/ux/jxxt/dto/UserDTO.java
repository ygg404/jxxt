package cn.ux.jxxt.dto;

import cn.ux.jxxt.domain.custom.UserCustom;
import cn.ux.jxxt.domain.custom.UserData;
import cn.ux.jxxt.domain.custom.UserWrap;
import cn.ux.jxxt.vo.Pagination;
import cn.ux.jxxt.vo.ServiceListItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class  UserDTO extends BasicDTO {
    private Pagination<UserWrap> AccountPagination;
    private Pagination<ServiceListItem> ServicePagination;
    private Long id;
    private String username;
    @NotBlank(message = "userAccount不能为空")
    private String userAccount;
    @NotBlank(message = "password不能为空")
    @JsonIgnore
    @NotNull(message="roleId不能为空")
    private Long roleId;
    private String password;
    private Boolean enabled;
    private String status;
    private UserDetails userDetails;
    private String avatarUri;
    private List<UserCustom> userCustoms;
    @NotNull(message = "rolesId不能为空")
    private List<Long> rolesId;
    private UserCustom userCustom;
    private UserWrap userWrap;
    private List<UserWrap> userWraps;
    private UserData userData;
    private List<String> permissions;

    public Pagination<UserWrap> getAccountPagination() {
        return AccountPagination;
    }

    public void setAccountPagination(Pagination<UserWrap> accountPagination) {
        AccountPagination = accountPagination;
    }

    public Pagination<ServiceListItem> getServicePagination() {
        return ServicePagination;
    }

    public void setServicePagination(Pagination<ServiceListItem> servicePagination) {
        ServicePagination = servicePagination;
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

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }


    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    public List<UserCustom> getUserCustoms() {
        return userCustoms;
    }

    public void setUserCustoms(List<UserCustom> userCustoms) {
        this.userCustoms = userCustoms;
    }

    public List<Long> getRolesId() {
        return rolesId;
    }

    public void setRolesId(List<Long> rolesId) {
        this.rolesId = rolesId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserCustom getUserCustom() {
        return userCustom;
    }

    public void setUserCustom(UserCustom userCustom) {
        this.userCustom = userCustom;
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

    public UserWrap getUserWrap() {
        return userWrap;
    }

    public void setUserWrap(UserWrap userWrap) {
        this.userWrap = userWrap;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<UserWrap> getUserWraps() {
        return userWraps;
    }

    public void setUserWraps(List<UserWrap> userWraps) {
        this.userWraps = userWraps;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
