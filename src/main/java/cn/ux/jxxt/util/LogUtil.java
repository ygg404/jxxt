package cn.ux.jxxt.util;

import cn.ux.jxxt.domain.Log;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 添加操作记录
 */
public class LogUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Log addLog(String content){
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录进数据库
        Log log = new Log();
        log.setOperationTime(Timestamp.valueOf(sdf.format(new Date())));
        log.setRequestIp(IPUtils.getIpAddr(request));
        log.setUserName(UxContent.userName);
        log.setUserContent(content);
        return log;
    }
}
