package com.softeem.crm.annotation;

import com.softeem.crm.exceptions.NoAuthException;
import com.softeem.crm.exceptions.NoLoginException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect//切面
public class PermissionProxy {

    @Autowired
    private HttpSession session;

    //环绕通知
    //环绕通知的触发条件时方法上面加了@RequirePermission注解
    @Around(value = "@annotation(com.softeem.crm.annotation.RequirePermission)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        if (null == permissions || permissions.size() == 0) {
            throw new NoLoginException();
        }
        Object result = null;
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        RequirePermission requirePermission = methodSignature.getMethod().getDeclaredAnnotation(RequirePermission.class);
        if (!(permissions.contains(requirePermission.code()))) {
            throw new NoAuthException();
        }
        result = pjp.proceed();
        return result;
    }
}