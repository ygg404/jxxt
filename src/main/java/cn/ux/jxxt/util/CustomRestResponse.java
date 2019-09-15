package cn.ux.jxxt.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomRestResponse {

    public static void response(HttpServletRequest request, HttpServletResponse response, int status, Object body) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(body));
        response.flushBuffer();
    }
}
