package cn.ux.jxxt.service.impl;

import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.dao.RoleDao;
import cn.ux.jxxt.dao.UserDao;
import cn.ux.jxxt.dao.WorkGroupDao;
import cn.ux.jxxt.domain.PassWord;
import cn.ux.jxxt.domain.Role;
import cn.ux.jxxt.domain.User;
import cn.ux.jxxt.domain.WorkGroup;
import cn.ux.jxxt.domain.custom.UserCustom;
import cn.ux.jxxt.domain.custom.UserWrap;
import cn.ux.jxxt.dto.UserDTO;
import cn.ux.jxxt.service.UserService;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.PaginationUtil;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.util.UxContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private WorkGroupDao groupDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private LogDao logDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @Transactional
    public UserDTO register(User user) throws Exception {
//        UserDTO userDTO = new UserDTO();
//        String roleName = "";
//        StringBuffer typeSb = new StringBuffer();
//        StringBuffer groupSb = new StringBuffer();
//        Map<String, Object> typeParams = new HashMap<>();
//        Map<String, Object> groupParams = new HashMap<>();
//        if (userDao.queryUserByUserAccount(user.getUserAccount()) != null) {
//            userDTO.setError("账号名已存在，请重新输入");
//            return userDTO;
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRegisterDateTime(Timestamp.valueOf(sdf.format(new Date())));
//        userDao.addAccount(user);
//        if (user.getRoleIds() != null && user.getRoleIds().size() > 0) {
//            for (Long roleId : user.getRoleIds()) {
//                Role role = roleDao.findRoleById(roleId);
//                if (role == null) {
//                    userDTO.setError("查询不到id为" + roleId + "的角色。");
//                    return userDTO;
//                }
//                Map<String, Object> paramsMap = new HashMap<>();
//                paramsMap.put("userId", user.getId());
//                paramsMap.put("roleId", roleId);
//                userDao.addUserRole(paramsMap);
//                typeSb.append(role.getName()).append(",");
//            }
//        }
//        if (user.getGroupIds() != null && user.getGroupIds().size() > 0) {
//            for (Long groupId : user.getGroupIds()) {
//                WorkGroup workGroup = groupDao.queryGroupById(groupId);
//                if (workGroup == null) {
//                    userDTO.setError("查询不到id为------" + groupId + "------的工作组。");
//                    return userDTO;
//                }
//                Map<String, Object> groupMap = new HashMap<>();
//                groupMap.put("userId", user.getId());
//                groupMap.put("groupId", groupId);
//                userDao.addUserGroup(groupMap);
//                groupSb.append(workGroup.getgName()).append(",");
//            }
//        }
//        groupParams.put("userAccount", user.getUserAccount());
//        typeParams.put("userAccount", user.getUserAccount());
//        if (typeSb.length() > 0) {
//            roleName = typeSb.deleteCharAt(typeSb.length() - 1).toString();
//            typeParams.put("typeName", roleName);
//        }
//
//        if (groupSb.length() > 0) {
//            groupParams.put("groupName", groupSb.deleteCharAt(groupSb.length() - 1).toString());
//        }
//        userDao.setUserType(typeParams);
//        userDao.setUserGroup(groupParams);
//        userDTO.setSuccess("注册成功");
//        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "注册账号 : " + user.getUserAccount()));
//        return userDTO;
        UserDTO userDTO = new UserDTO();
        String roleName = "";
        StringBuffer typeSb = new StringBuffer();
        StringBuffer groupSb = new StringBuffer();
        Map<String, Object> typeParams = new HashMap<>();
        Map<String, Object> groupParams = new HashMap<>();
        if (userDao.queryUserByUserAccount(user.getUserAccount()) != null) {
            userDTO.setError("账号名已存在，请重新输入");
            return userDTO;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegisterDateTime(Timestamp.valueOf(sdf.format(new Date())));
        userDao.addAccount(user);
        if (user.getRoleIds() != null && user.getRoleIds().size() > 0) {
            for (Long roleId : user.getRoleIds()) {
                Role role = roleDao.findRoleById(roleId);
                if (role == null) {
                    userDTO.setError("查询不到id为" + roleId + "的角色。");
                    return userDTO;
                }
                Map<String, Object> paramsMap = new HashMap<>();
                paramsMap.put("userId", user.getId());
                paramsMap.put("roleId", roleId);
                userDao.addUserRole(paramsMap);
                typeSb.append(role.getName()).append(",");
            }
        }
        if (user.getGroupId() != null) {
            WorkGroup workGroup = groupDao.queryGroupById(user.getGroupId());
            if (workGroup == null) {
                userDTO.setError("查询不到id为------" + user.getGroupId() + "------的工作组。");
                return userDTO;
            }
            Map<String, Object> groupMap = new HashMap<>();
            groupMap.put("userId", user.getId());
            groupMap.put("groupId", user.getGroupId());
            userDao.addUserGroup(groupMap);
            groupSb.append(workGroup.getgName()).append(",");
        }
        groupParams.put("userAccount", user.getUserAccount());
        typeParams.put("userAccount", user.getUserAccount());
        if (typeSb.length() > 0) {
            roleName = typeSb.deleteCharAt(typeSb.length() - 1).toString();
            typeParams.put("typeName", roleName);
        }

        if (groupSb.length() > 0) {
            groupParams.put("groupName", groupSb.deleteCharAt(groupSb.length() - 1).toString());
        }
        userDao.setUserType(typeParams);
        userDao.setUserGroup(groupParams);
        userDTO.setSuccess("注册成功");
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "注册账号 : " + user.getUserAccount()));
        return userDTO;
    }

    /**
     * 获取用户数据(分页)
     *
     * @param page
     * @param per_page
     * @param sortBy
     * @param descending
     * @return
     */
    @Override
    public UserDTO getAccounts(int page, int per_page, String sortBy, boolean descending) {
        UserDTO returnDTO = new UserDTO();
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("per_page", per_page);
        paramsMap.put("offset", (page - 1) * per_page);
        paramsMap.put("sortBy", sortBy);
        paramsMap.put("desc", descending);
        Long total = userDao.getUserCounts(paramsMap);
        List<UserWrap> users = userDao.queryUsers(paramsMap);
        returnDTO.setAccountPagination(PaginationUtil.paginate(page, per_page, total, users));
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "查看所有用户"));
        return returnDTO;
    }

    /**
     * 获取用户角色对应的Id
     * @param rolename
     * @return
     */
    @Override
    public List<Long> getRoleId(String rolename) {
        List<Long> list = new ArrayList<>();
        String[] StrArray = rolename.split(",");
        for (String s : StrArray){
            list.add(userDao.getRoleId(s));
        }
        return list;
    }

    /**
     * 获取用户角色对应的Id
     * @param workname
     * @return
     */
    @Override
    public Long getWorkId(String workname) {
        Long l = userDao.getWorkId(workname);
        return l;
    }

    /**
     * 修改密码
     *
     * @return
     */
    @Override
    public UserDTO resetPassword(PassWord passWord) {
        UserDTO returnDTO = new UserDTO();
        String now = sdf.format(new Date());
        if (!passwordEncoder.matches(passWord.getOldPassWord(), userDao.queryUserByUserAccount(passWord.getUserAccount()).getPassword())) {
            returnDTO.setError("旧密码错误");
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("userAccount", passWord.getUserAccount());
            params.put("password", passwordEncoder.encode(passWord.getNewPassWord()));
            params.put("lastPasswordResetDateTime", now);
            userDao.resetPassWord(params);
            returnDTO.setSuccess("修改密码成功");
        }
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "修改密码"));
        return returnDTO;
    }

    @Override
    public UserDTO updateUserData(User user) {
        UserDTO returnDTO = new UserDTO();
        String roleName = "";
        String groupName = "";
        StringBuffer typeSb = new StringBuffer();
        StringBuffer groupSb = new StringBuffer();
        Map<String, Object> userParams = new HashMap<>();
        //验证账号是否存在
        User userData = userDao.queryUserByUserAccount(user.getUserAccount());
        if (userData == null) {
            returnDTO.setError("查无此账号");
            return returnDTO;
        }
        //用验证是否需要修改密码
        if (!TextUtils.isEmpty(user.getNewPassword())) {
            user.setPassword(passwordEncoder.encode(user.getNewPassword()));
            user.setLastPasswordResetDateTime(Timestamp.valueOf(sdf.format(new Date())));
        }

        if(user.getRoleIds().size() > 0 && user.getRoleIds()!=null) {
            userDao.deleteRole(userData.getId());
            for (Long roleId : user.getRoleIds()) {             //添加用户角色信息
                Role role = roleDao.findRoleById(roleId);
                Map<String, Object> paramsMap = new HashMap<>();
                paramsMap.put("userId", userData.getId());
                paramsMap.put("roleId", roleId);
                userDao.addUserRole(paramsMap);
                typeSb.append(role.getName()).append(",");
            }
        }
        if (user.getGroupId() != null) {           //更新用户所在组数据
            userDao.deleteUserGroup(userData.getId());
            WorkGroup workGroup = groupDao.queryGroupById(user.getGroupId());
            Map<String, Object> groupMap = new HashMap<>();
            groupMap.put("userId", userData.getId());
            groupMap.put("groupId", user.getGroupId());
            userDao.addUserGroup(groupMap);
            groupSb.append(workGroup.getgName()).append(",");
        }
        if(typeSb.length() > 1) {
            roleName = typeSb.deleteCharAt(typeSb.length() - 1).toString();
        }
        if(groupSb.length() > 1) {
            groupName = groupSb.deleteCharAt(groupSb.length() - 1).toString();
        }
        userParams.put("userAccount", user.getUserAccount());
        userParams.put("username", user.getUsername());
        userParams.put("password", user.getPassword());
        userParams.put("roleName", roleName);
        userParams.put("groupName", groupName);
        userParams.put("lastPasswordResetDateTime", user.getLastPasswordResetDateTime());
        userDao.resetUserData(userParams);        //更新账户信息
        returnDTO.setSuccess("修改成功");
        logDao.addLog(LogUtil.addLog(UxContent.getUserName() + "修改账号信息成功 : " + user.getUserAccount()));
        return returnDTO;
    }

    @Override
    public UserDTO getUserInfo(String userAccount) {
        UserDTO returnDTO = new UserDTO();
        UserCustom user = userDao.queryUserByUserAccount(userAccount);
        if (user == null) {
            returnDTO.setError("查无此账号");
            return returnDTO;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("userAccount", userAccount);
        UserWrap userWrap = userDao.getUserInfo(params);
        userWrap.setGroupId(userDao.getGroups(user.getId()));
        userWrap.setRoleId(userDao.queryRolesNoPermission(user.getId()));
        returnDTO.setUserWrap(userWrap);
        return returnDTO;
    }

    @Override
    public UserDTO deleteUser(String userAccount) {
        UserDTO returnDTO = new UserDTO();
        if(userDao.queryUserByUserAccount(userAccount) == null){
            returnDTO.setError("查无此账号");
            return returnDTO;
        }
        userDao.deleteUser(userAccount);
        returnDTO.setSuccess("删除用户成功");
        return returnDTO;
    }

    @Override
    public UserDTO getUser(String userAccount) {
        UserDTO returnDTO = new UserDTO();
        if(userDao.getUserByUserAccount(userAccount) == null){
            returnDTO.setError("查无此账号");
            return returnDTO;
        }
        returnDTO.setUserData(userDao.getUserByUserAccount(userAccount));
        return returnDTO;
    }

    @Override
    public UserDTO getUserList() {
        UserDTO returnDTO = new UserDTO();
        returnDTO.setUserWraps(userDao.getUser());
        return returnDTO;
    }

    @Override
    public UserDTO getUserAccountList() {
        UserDTO returnDTO = new UserDTO();
        List<String> userAccounts = userDao.getChargeAccount();
        List<UserWrap> userList = new ArrayList<>();
        for(String userAccount : userAccounts){
            userList.add(userDao.getUserByAccounts(userAccount));
        }
        returnDTO.setUserWraps(userList);
        return returnDTO;
    }

    @Override
    public UserDTO getUserForBusiness() {
        UserDTO returnDTO = new UserDTO();
        returnDTO.setUserWraps(userDao.getUserForBusiness());
        return returnDTO;
    }
}
