package com.hdg.controller;

import com.hdg.entity.ExecuteResult;
import com.hdg.entity.Result;
import com.hdg.entity.User;
import com.hdg.other.Constant;
import com.hdg.service.UserService;
import com.hdg.util.ConfigUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        ExecuteResult<User> executeResult=userService.getUser(user,request,code);
        String url=(String) request.getSession().getAttribute("temp_url");
        if(StringUtils.isNotBlank(url) && executeResult.getCode()==Constant.SUCCESS){
            executeResult.setCode(201);
            if(logger.isInfoEnabled()){
                logger.info("url{}",url);
            }
            executeResult.setMsg(url);
        }
        return new Result(executeResult.getCode(),null,executeResult.getMsg());
    }
}
