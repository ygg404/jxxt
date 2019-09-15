package cn.ux.jxxt.security;

import cn.ux.jxxt.domain.Permission;
import cn.ux.jxxt.domain.custom.RoleCustom;
import cn.ux.jxxt.domain.custom.UserCustom;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {
    public static JwtUser create(UserCustom user) {
        return new JwtUser(user.getId(),
                user.getUsername(),
                user.getUserAccount(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getLastPasswordResetDateTime(),
                user.getType(),
                user.getStatus());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleCustom> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (RoleCustom role : roles) {
            for (Permission permission : role.getPermissions()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName_code()));
            }
        }
        return grantedAuthorities;
    }
}
