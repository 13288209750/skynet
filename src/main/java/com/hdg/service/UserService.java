package com.hdg.service;

import com.hdg.dao.UserDao;
import com.hdg.entity.ExecuteResult;
import com.hdg.entity.QueryResult;
import com.hdg.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BlueBuff on 2017/7/10.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public ExecuteResult<User> getUser(User user) {
        ExecuteResult<User> executeResult=new ExecuteResult<User>(ExecuteResult.FAILED,ExecuteResult.DEFAULT_MSG);
        QueryResult<User> userQueryResult=userDao.selUser(user);
        if(userQueryResult.getCount()!=0){
            executeResult.setCode(ExecuteResult.SUCCESS);
            executeResult.setObj(userQueryResult.getDataList().get(0));
        }
        return executeResult;
    }
}
