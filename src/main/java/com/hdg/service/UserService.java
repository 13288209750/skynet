package com.hdg.service;

import com.hdg.dao.UserDao;
import com.hdg.entity.ExecuteResult;
import com.hdg.entity.QueryResult;
import com.hdg.entity.User;
import com.hdg.other.Constant;
import com.hdg.util.ConfigUtil;
import com.hdg.util.IpAddressUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by BlueBuff on 2017/7/10.
 */
@Service
public class UserService {

    private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Resource(name = "ipAddress")
    private IpAddressUtil ipAddressUtil;

    public ExecuteResult<User> getUser(User user, HttpServletRequest request, String code) {
        HttpSession session = request.getSession(true);
        ExecuteResult<User> executeResult = new ExecuteResult<User>(Constant.WITHOUT, Constant.DEFAULT_MSG);
        String patchca = (String) session.getAttribute(ConfigUtil.getConfigInfo("PHOTO_CODE"));
        boolean codePass=false;
        Object showCode=session.getAttribute(ConfigUtil.getConfigInfo("SHOW_CODE"));
        //检查用户名和密码是否为空
        if (user.getUserName() == null || user.getPassword() == null) {
            executeResult.setMsg("用户名和密码不能为空");
            return executeResult;
        }
        //校验验证码
        if(showCode!=null){
            if (StringUtils.isNotBlank(patchca) && !patchca.equals(code)) {
                executeResult.setMsg("验证码不正确");
                executeResult.setCode(101);
                return executeResult;
            }else {
                codePass=true;
            }
        }
        //创建临时的用户，用于查询用户名是否存在
        User temp = new User();
        temp.setUserName(user.getUserName());
        List<User> users = userDao.selUser(temp);
        if (users !=null || !users.isEmpty() ) {
            //如果用户名存在，检查密码是否正确
            temp = users.get(0);
            if (temp.getUserName().equals(user.getUserName()) && temp.getPassword().equals(user.getPassword())) {
                //密码正确
                executeResult.setCode(Constant.SUCCESS);
                executeResult.setMsg("success");
                String address = ipAddressUtil.getIpAddress(request);
                String onlineAddress=temp.getOnlineAddress();
                if (!codePass && StringUtils.isBlank(onlineAddress)) {
                    executeResult.setCode(100);
                    executeResult.setMsg("由于您是首次登录，请输入验证码");
                    session.setAttribute(ConfigUtil.getConfigInfo("SHOW_CODE"),true);
                }else if(!codePass && StringUtils.isNotBlank(onlineAddress) && !(onlineAddress.trim().equals(address))){
                    executeResult.setCode(100);
                    executeResult.setMsg("由于您本次登录地点与上次不一样，请输入验证码");
                    session.setAttribute(ConfigUtil.getConfigInfo("SHOW_CODE"),true);
                }else {
                    if(codePass){
                        //输入了验证码的原因就是地址不同,用户密码正确且验证码通过了验证就把当次的地址保存到数据库
                        User modifyUser=new User();
                        modifyUser.setUid(user.getUid());
                        modifyUser.setOnlineAddress(address);
                        int flag=userDao.modifyUser(modifyUser);
                        if(flag <= 0){
                            if(logger.isWarnEnabled()){
                                logger.warn("用户IP地址修改失败");
                            }
                        }else {
                            if(logger.isWarnEnabled()){
                                logger.warn("用户IP地址修改成功");
                            }
                        }
                        //移除属性
                        session.removeAttribute(ConfigUtil.getConfigInfo("SHOW_CODE"));
                    }
                    session.setAttribute(ConfigUtil.getConfigInfo("ADMIN_NAME"), temp);
                    executeResult.setObj(user);
                }
            } else {
                //密码错误
                executeResult.setMsg("密码错误");
            }
        } else {
            //用户名不存在
            executeResult.setMsg("用户名不存在");
        }
        return executeResult;
    }
}
