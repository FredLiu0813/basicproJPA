package com.arts.basic.pro.aop;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class ApiLogAspect {

    @Pointcut("execution(public * com.arts.basic.pro.controller.pc.*.*(..))")
    public void apiLog() {
    }

    /**
     * 在切点之前织入
     */
    @Before("apiLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }

        log.info("========================================== Start ==========================================");
        if (request != null) {
            log.info("URL            : {}", request.getRequestURL().toString());
        }
        if (request != null) {
            log.info("HTTP Method    : {}", request.getMethod());
        }
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        if (request != null) {
            log.info("IP             : {}", request.getRemoteAddr());
        }
        // 打印请求入参
        log.info("Request Args   : {}", JSONUtil.toJsonStr(joinPoint.getArgs()));
    }

    /**
     * 在切点之后织入
     */
    @After("apiLog()")
    public void doAfter() throws Throwable {
        log.info("=========================================== End ===========================================");
        // 每个请求之间空一行
        log.info("");
    }

    /**
     * 环绕
     */
    @Around("apiLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();

        // 打印出参
        //logger.info("Response Args  : {}", new Gson().toJson(result));
        // 执行耗时
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }
}
