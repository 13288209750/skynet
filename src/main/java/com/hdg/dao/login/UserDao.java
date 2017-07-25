package com.hdg.dao.login;

import com.hdg.entity.login.User;

import java.util.List;

/**
 * @Description
 * @Author BlueBuff
 * @Email 1010060164@qq.com
 * @CreatedDate 2017年07月25日 星期二 21时42分.
 */
public interface UserDao {
    List<User> getUserByUser(User user);
    int modifyUser(User user);
}
