package cn.ux.jxxt.dao;

import cn.ux.jxxt.domain.User;
import cn.ux.jxxt.domain.UserGroup;
import cn.ux.jxxt.domain.UserRole;
import cn.ux.jxxt.domain.WorkGroup;
import cn.ux.jxxt.domain.custom.RoleCustom;
import cn.ux.jxxt.domain.custom.UserCustom;
import cn.ux.jxxt.domain.custom.UserData;
import cn.ux.jxxt.domain.custom.UserWrap;

import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * 根据id搜索用户
     * @param userId
     * @return
     */
    public UserCustom queryUserById(Long userId);

    /**
     * 根据用户名搜索用户
     * @param username
     * @return
     */
    public UserCustom queryUserByUsername(String username);

    /**
     * 根据用户账号搜索用户
     * @param userAccount
     * @return
     */
    public UserCustom queryUserByUserAccount(String userAccount);

    /**
     * 获取单个用户数据
     * @param userAccount
     * @return
     */
    public UserData getUserByUserAccount(String userAccount);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int addAccount(User user);

    /**
     * 添加用户角色
     * @param paramsMap
     * @return
     */
    public int addUserRole(Map<String, Object> paramsMap);

    /**
     * 添加用户工作组
     * @param paramsMap
     * @return
     */
    public int addUserGroup(Map<String, Object> paramsMap);

    /**
     * 获取用户总数
     * @param paramsMap
     * @return
     */
    public Long getUserCounts(Map<String, Object> paramsMap);

    /**
     * 获取分页用户数据
     * @param paramsMap
     * @return
     */
    public List<UserWrap> queryUsers(Map<String, Object> paramsMap);

    /**
     * 修改密码
     * @param paramsMap
     * @return
     */
    public int resetPassWord(Map<String, Object> paramsMap);

    /**
     * 更新登录时间
     * @param params
     * @return
     */
    public int updateLastTime(Map<String,Object> params);

    /**
     * 添加用户所属角色名称
     * @param params
     * @return
     */
    public int setUserType(Map<String,Object> params);

    /**
     * 添加用户所属作业组名称
     * @param params
     * @return
     */
    public int setUserGroup(Map<String,Object> params);

    /**
     * 更新用户数据
     * @param params
     * @return
     */
    public int resetUserData(Map<String,Object> params);

    /**
     * 获取用户信息
     * @param params
     * @return
     */
    public UserWrap getUserInfo(Map<String,Object> params);

    /**
     * 获取用户所在组
     * @param userId
     * @return
     */
    public List<WorkGroup> getGroups(Long userId);

    /**
     * 获取不带权限的角色数据
     * @param id
     * @return
     */
    public List<RoleCustom> queryRolesNoPermission(Long id);

    /**
     * 修改用户角色
     * @param params
     * @return
     */
    public int updateUserRole(Map<String,Object> params);

    /**
     * 修改用户所在组
     * @param params
     * @return
     */
    public int updateUserGroup(Map<String,Object> params);

    public UserRole getUserRoleById(Long roleId);

    public UserGroup getUserGroupById(Long groupId);

    public int deleteGroup(Long userId);

    public int deleteRole(Long userId);

    public int deleteUser(String userAccount);

    public int deleteUserGroup(Long userId);

    public List<UserWrap>getUser();

    /**
     * 获取项目负责人账号
     * @return
     */
    public List<String> getChargeAccount();

    public UserWrap getUserByAccounts(String userAccount);

    public Long getRoleId(String rolename);

    public Long getWorkId(String workname);

    public List<UserWrap> getUserForBusiness();     //获取业务负责人
}
