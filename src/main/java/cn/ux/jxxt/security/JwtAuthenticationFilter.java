package cn.ux.jxxt.security;

import cn.ux.jxxt.util.CustomRestResponse;
import cn.ux.jxxt.vo.GeneralMessage;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private GeneralMessage generalMessage;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(tokenHeader);

        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(token);
            } catch (ExpiredJwtException e) {
                logger.error("token expired");
                generalMessage.setMessage("token expired");
                CustomRestResponse.response(request, response, 401, generalMessage);
                return;
            } catch (Exception e) {
                logger.error(e.getMessage());
                generalMessage.setMessage("server error");
                CustomRestResponse.response(request, response, 500, generalMessage);
                return;
            }
        } else if (authHeader == null) {
            logger.error("Authorization header not found");
            generalMessage.setMessage("Authorization header not found");
            CustomRestResponse.response(request, response, 422, generalMessage);
            return;
        } else {
            logger.error("Authorization header error");
            generalMessage.setMessage("Authorization header error");
            CustomRestResponse.response(request, response, 422, generalMessage);
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            try {
                userDetails = this.userDetailsService.loadUserByUsername(username);
            } catch (UsernameNotFoundException ex) {
                logger.error(ex.getMessage());
                generalMessage.setMessage(ex.getMessage());
                CustomRestResponse.response(request, response, 401, generalMessage);
                return;
            }

            if (jwtTokenUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> registration(JwtAuthenticationFilter filter) {
        FilterRegistrationBean<JwtAuthenticationFilter> registration = new FilterRegistrationBean<JwtAuthenticationFilter>(filter);
        registration.setEnabled(false);
        return registration;
    }

}
