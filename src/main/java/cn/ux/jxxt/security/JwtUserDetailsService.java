package cn.ux.jxxt.security;

import cn.ux.jxxt.dao.UserDao;
import cn.ux.jxxt.domain.custom.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCustom user = userDao.queryUserByUserAccount(username);
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }

}
