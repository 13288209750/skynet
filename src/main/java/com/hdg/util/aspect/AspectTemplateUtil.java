package com.hdg.util.aspect;

import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;

/**
 * 提供模板，提高代码的利用率
 * Created by huangxiaojun on 2017/6/16.
 */
public class AspectTemplateUtil {

    /**
     * 以字符串的形式获得属性
     * @param joinPoint
     * @param aspectSupport
     * @return
     * @throws Exception
     */
    public  static <T> T getPropertyByString(JoinPoint joinPoint, IAspectSupport aspectSupport)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        T result=null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    result = aspectSupport.getProperty(method);
                    break;
                }
            }
        }
        return result;
    }
}
