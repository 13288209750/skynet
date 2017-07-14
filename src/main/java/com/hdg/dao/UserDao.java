package com.hdg.dao;

import com.hdg.entity.QueryResult;
import com.hdg.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BlueBuff on 2017/7/10.
 */
@Repository
public class UserDao extends BaseJdbcDao{

    public QueryResult<List<User>> selUser(User user) {
        String sql="select * from t_user t where t.userName =? and t.`password`=?";
        Object[] obj=new Object[]{user.getUserName(),user.getPassword()};
        List<User> userList=jdbcTemplateRead.query(sql,obj, BeanPropertyRowMapper.newInstance(User.class));
        QueryResult queryResult=new QueryResult();
        queryResult.setCount(userList.size());
        queryResult.setData(userList);
        return queryResult;
    }
}
