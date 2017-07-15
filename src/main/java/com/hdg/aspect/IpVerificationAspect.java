package com.hdg.aspect;

import com.hdg.annotation.IpAspectAnnotation;
import com.hdg.entity.IpAddress;
import com.hdg.entity.Result;
import com.hdg.other.Constant;
import com.hdg.util.AspectSupportAdapter;
import com.hdg.util.AspectTemplateUtil;
import com.hdg.util.ConfigUtil;
import com.hdg.util.IpAddressUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by BlueBuff on 2017/7/15.
 */
@Aspect
@Component
@Order(0)
public class IpVerificationAspect {

    public IpVerificationAspect(){
        if(logger.isInfoEnabled()){
            logger.info("IP验证器正在初始化中...");
        }
    }

    @Resource(name = "ipAddress")
    private IpAddressUtil ipAddressUtil;
    @Autowired
    private HttpServletRequest request;

    //定义一个切面
    @Pointcut("@annotation(com.hdg.annotation.IpAspectAnnotation)")
    public void ipVerificationPoint() {
    }

    @Around("ipVerificationPoint()")
    public Object checkIp(ProceedingJoinPoint joinPoint) throws Throwable {
        String adminCfgName= ConfigUtil.getConfigInfo("ADMIN_NAME");
        String userName = request.getParameter(adminCfgName);
        if (StringUtils.isBlank(userName)) {
            String address = ipAddressUtil.getIpAddress(request);
            IpAddress ipAddress = new IpAddress();
            ipAddress.setAddress(address);
            //为空，验证IP
            if (!ipAddressUtil.isExist(ipAddress)) {
                //不存在当前Ip地址
                String description = AspectTemplateUtil.getPropertyByString(joinPoint, new AspectSupportAdapter() {
                    @Override
                    public String getProperty(Method method) {
                        return method.getAnnotation(IpAspectAnnotation.class).description();
                    }
                });
                if(org.apache.commons.lang.StringUtils.isBlank(description)){
                    //如果为空，默认为请求路径
                    description=request.getRequestURI();
                }
                if(logger.isWarnEnabled()){
                    logger.warn("非法IP访问,IP:[{}]\tdescription-->[{}]",address,description);
                }
                return new Result(Constant.REFUSE,"","服务器拒绝访问");
            }else {
                if(logger.isInfoEnabled()){
                    logger.info("IP-->[{}]",address);
                }
            }

        }
        //用户名不为空默认通过，或者IP验证通过
        Object[] args = joinPoint.getArgs();
        Object object = joinPoint.proceed(args);
        return object;
    }

    private static Logger logger= LoggerFactory.getLogger(IpVerificationAspect.class);
}
