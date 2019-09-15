package cn.ux.jxxt.security;

import cn.ux.jxxt.util.CustomRestResponse;
import cn.ux.jxxt.vo.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private GeneralMessage generalMesasge;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
            throws IOException, ServletException {
        generalMesasge.setMessage("没有权限进行此操作");
        CustomRestResponse.response(request, response, 403, generalMesasge);
    }

}
