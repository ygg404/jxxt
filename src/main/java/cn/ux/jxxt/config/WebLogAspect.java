package cn.ux.jxxt.config;

import cn.ux.jxxt.util.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Component
@Aspect
public class WebLogAspect {
    private Logger looger = LoggerFactory.getLogger(this.getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * cn.ux.jxxt.web.*.*(..))")//.*.*代表所有子目录下的所有方法，最后括号里(..)的两个..代表所有参数
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        startTime.set(System.currentTimeMillis());

        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录下请求内容
        looger.info("请求地址 : " + request.getRequestURL().toString());
        looger.info("HTTP_METHOD : " + request.getMethod());
        looger.info("IP : " + IPUtils.getIpAddr(request));
        looger.info("CLASS_METHOD" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        looger.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret" ,pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable{
        //处理完请求，返回内容
        looger.info("返回值 : " + ret);
        looger.info("耗时 : " + (System.currentTimeMillis() - startTime.get()));
    }
}
