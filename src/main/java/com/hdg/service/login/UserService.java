package com.hdg.service.login;

import com.hdg.entity.result.ExecuteResult;
import com.hdg.entity.login.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author BlueBuff
 * @Email 1010060164@qq.com
 * @CreatedDate 2017年07月25日 星期二 21时56分.
 */
public interface UserService {
    ExecuteResult<User> getUser(User user, HttpServletRequest request, String code);
}
