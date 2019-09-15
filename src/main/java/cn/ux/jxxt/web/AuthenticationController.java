package cn.ux.jxxt.web;

import cn.ux.jxxt.dao.LogDao;
import cn.ux.jxxt.dao.UserDao;
import cn.ux.jxxt.domain.Token;
import cn.ux.jxxt.domain.User;
import cn.ux.jxxt.exception.InvalidRequestException;
import cn.ux.jxxt.security.JwtTokenUtil;
import cn.ux.jxxt.util.LogUtil;
import cn.ux.jxxt.util.TextUtils;
import cn.ux.jxxt.util.UxContent;
import cn.ux.jxxt.vo.GeneralMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 用户注册
 * @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
 */
@RestController
public class AuthenticationController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private GeneralMessage generalMessage;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LogDao logDao;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/auth/")
    public ResponseEntity<?> authenticate(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //返回异常校验信息
            throw new InvalidRequestException("authenticate failed", bindingResult);
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserAccount(), user.getPassword()));
        } catch (AuthenticationException e) {
            if(!(TextUtils.isEmpty(e.getMessage()))){
                logger.error(e.getMessage());
                generalMessage.setMessage("密码错误");
            }else{
                generalMessage.setMessage("此账号未注册");
            }
            return ResponseEntity.badRequest().body(generalMessage);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserAccount());
        String generatedtoken = jwtTokenUtil.generateToken(userDetails);
        Token token = new Token(generatedtoken);
        User loginUser = userDao.queryUserByUserAccount(user.getUserAccount());
        if(!TextUtils.isEmpty(UxContent.getUserName())){
            UxContent.setUserName("");
        }
        UxContent.setUserName(loginUser.getUsername());
        logDao.addLog(LogUtil.addLog(loginUser.getUsername() + "登录"));
        Map<String ,Object> params = new HashMap<>();
        params.put("lastTime", Timestamp.valueOf(sdf.format(new Date())));
        params.put("userAccount",user.getUserAccount());
        userDao.updateLastTime(params);
        return ResponseEntity.ok(token);
    }
}

