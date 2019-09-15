package cn.ux.jxxt.web;

import cn.ux.jxxt.domain.PassWord;
import cn.ux.jxxt.domain.User;
import cn.ux.jxxt.dto.UserDTO;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.security.JwtUser;
import cn.ux.jxxt.service.UserService;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.vo.GeneralMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(value = "用户操作")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GeneralMessage message;

    /**
     * 注册
     *
     * @param user
     * @param bindingResult
     * @return
     * @throws Exception
     */

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/accounts/")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("注册失败", bindingResult);
        }

        UserDTO returnDTO = null;
        returnDTO = userService.register(user);
        if (returnDTO.getSuccess() != null) {
            message.setMessage(returnDTO.getSuccess());
            return ResponseEntity.ok(message);
        } else {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        }
    }

    /**
     * 获取所有账户
     *
     * @param page       页数
     * @param per_page   数量
     * @param sortBy     根据什么排序
     * @param descending 是否倒序
     * @return
     */
    @GetMapping(value = "/getAccounts/", params = {"page", "rowsPerPage", "sortBy", "descending"})
    public ResponseEntity<?> getAllAccounts(@RequestParam("page") int page,
                                            @RequestParam("rowsPerPage") int per_page,
                                            @RequestParam("sortBy") String sortBy,
                                            @RequestParam("descending") boolean descending) {
        UserDTO returnDTO = userService.getAccounts(page, per_page, sortBy, descending);
        if (returnDTO.getAccountPagination() == null) {
            message.setMessage(returnDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            return ResponseEntity.ok(returnDTO.getAccountPagination());
        }
    }

    /**
     * 获取用户对应权限
     *
     * @return
     */
    @GetMapping(value = "/users/permissions/")
    public ResponseEntity<List<String>> getUserPermissions() {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> permissions = new ArrayList<>();
        for (GrantedAuthority authority : user.getAuthorities()) {
            permissions.add(authority.getAuthority());
        }
        return ResponseEntity.ok().body(permissions);
    }

    /**
     * 获取用户角色对应的Id
     *
     * @return
     */
    @GetMapping(value = "/getRoleId/",params = {"rolename"})
    public ResponseEntity<List<Long>> getRoleId(@RequestParam("rolename") String rolename) {
        List<Long> RoleId = userService.getRoleId(rolename);
        return ResponseEntity.ok().body(RoleId);
    }

    /**
     * 获取用户工作组对应的Id
     *
     * @return
     */
    @GetMapping(value = "/getWorkId/",params = {"workname"})
    public ResponseEntity<Long> getWorkId(@RequestParam("workname") String workname) {
        Long RoleId = userService.getWorkId(workname);
        return ResponseEntity.ok().body(RoleId);
    }

    /**
     * 修改密码
     *
     * @param passWord
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/users/")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody PassWord passWord, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("修改密码失败", bindingResult);
        }
        UserDTO userDTO = userService.resetPassword(passWord);
        if (!TextUtils.isEmpty(userDTO.getError())) {
            message.setMessage(userDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(userDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    @PostMapping(value = "/user/")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("更新出错", bindingResult);
        }
        UserDTO userDTO = userService.updateUserData(user);
        if (!TextUtils.isEmpty(userDTO.getError())) {
            message.setMessage(userDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setMessage(userDTO.getSuccess());
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 获取单个用户信息
     *
     * @param userAccount
     * @return
     */
    @PostMapping(value = "/getUser/{userAccount}/")
    public ResponseEntity<?> getUserData(@PathVariable("userAccount") String userAccount) {

        UserDTO userDTO = userService.getUserInfo(userAccount);
        if (!TextUtils.isEmpty(userDTO.getError())) {
            message.setMessage(userDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO.getUserWrap());
        }
    }

    /**
     * 删除用户
     *
     * @param userAccount
     * @return
     */
    @DeleteMapping(value = "/user/", params = "userAccount")
    public ResponseEntity<?> deleteUser(@RequestParam("userAccount") String userAccount) {
        UserDTO userDTO = userService.deleteUser(userAccount);
        if (!TextUtils.isEmpty(userDTO.getError())) {
            message.setMessage(userDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }

    /**
     * 根据用户账号获取用户信息
     * @param userAccount
     * @return
     */
    @GetMapping(value = "/user/{userAccount}/")
    public ResponseEntity<?> getUseByUserAccount(@PathVariable("userAccount") String userAccount) {
        UserDTO userDTO = userService.getUser(userAccount);
        if (!TextUtils.isEmpty(userDTO.getError())) {
            message.setMessage(userDTO.getError());
            return ResponseEntity.badRequest().body(message);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO.getUserData());
        }
    }

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping(value = "/user/")
    public ResponseEntity<?> getUserList(){
        UserDTO userDTO = userService.getUserList();
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO.getUserWraps());
    }

    @GetMapping(value = "/user/charge/")
    public ResponseEntity<?> getChargeUser(){
        UserDTO userDTO = userService.getUserAccountList();
        return  ResponseEntity.status(HttpStatus.CREATED).body(userDTO.getUserWraps());
    }

    /**
     * 获取所有业务负责人信息
     * @return
     */
    @GetMapping(value = "/user/business/")
    public ResponseEntity<?> getUserForBusinessList(){
        UserDTO userDTO = userService.getUserForBusiness();
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO.getUserWraps());
    }
}
