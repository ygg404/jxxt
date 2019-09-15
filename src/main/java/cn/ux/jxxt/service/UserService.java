package cn.ux.jxxt.service;

import cn.ux.jxxt.domain.PassWord;
import cn.ux.jxxt.domain.User;
import cn.ux.jxxt.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO register(User user) throws Exception;

    UserDTO getAccounts(int page, int per_page, String sortBy, boolean descending);

    UserDTO resetPassword(PassWord passWord);

    UserDTO updateUserData(User user);

    UserDTO getUserInfo(String userAccount);

    UserDTO deleteUser(String userAccount);

    UserDTO getUser(String userAccount);

    UserDTO getUserList();

    UserDTO getUserAccountList();

    List<Long> getRoleId(String rolename);

    Long getWorkId(String workname);

    UserDTO getUserForBusiness();
}
