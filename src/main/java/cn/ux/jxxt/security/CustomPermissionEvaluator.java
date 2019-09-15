package cn.ux.jxxt.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        JwtUser user = (JwtUser) authentication.getPrincipal();
        Set<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority()).collect(Collectors.toSet());
        if (authorities.contains(permission)) {
            return true;
        } else {
            return false;
        }
    }

}
