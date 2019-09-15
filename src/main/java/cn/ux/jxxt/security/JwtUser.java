package cn.ux.jxxt.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;

public class JwtUser implements UserDetails {
    private static final long serialVersionUID = -411443602166724522L;

    private Long id;
    private String username;
    private String password;
    private String useraccount;
    private Collection<? extends GrantedAuthority> authorities;
    private Timestamp lastPasswordResetDateTime;
    private String type;
    private String status;

    public JwtUser(Long id, String username,String useraccount, String password, Collection<? extends GrantedAuthority> authorities,
                   Timestamp lastPasswordResetDateTime, String type, String status) {
        super();
        this.id = id;
        this.username = useraccount;
        this.useraccount = username;
        this.password = password;
        this.authorities = authorities;
        this.lastPasswordResetDateTime = lastPasswordResetDateTime;
        this.type = type;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getUseraccount(){return this.useraccount;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getLastPasswordResetDateTime() {
        return lastPasswordResetDateTime;
    }

    public void setLastPasswordResetDateTime(Timestamp lastPasswordResetDateTime) {
        this.lastPasswordResetDateTime = lastPasswordResetDateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
