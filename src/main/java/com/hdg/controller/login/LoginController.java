package com.hdg.controller.login;

import com.hdg.entity.result.ExecuteResult;
import com.hdg.entity.result.Result;
import com.hdg.entity.login.User;
import com.hdg.service.login.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by BlueBuff on 2017/7/8.
 */
@Controller
@RequestMapping(value = {"/login"})
public class LoginController {

    private static final Logger logger= LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/tohome")
    @ResponseBody
    public Result toLogin(User user,String code, HttpServletRequest request){
        if(logger.isInfoEnabled()){
            logger.info("进入登录{}",user);
        }
        ExecuteResult<User> executeResult= userService.getUser(user,request,code);
        return new Result(executeResult.getCode(),null,executeResult.getMsg());
    }
}
