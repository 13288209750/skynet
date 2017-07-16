package com.hdg.dao;

import com.hdg.entity.QueryResult;
import com.hdg.entity.User;
import com.hdg.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BlueBuff on 2017/7/10.
 */
@Repository("userDao")
public class UserDao extends BaseJdbcDao{


    public QueryResult<List<User>> selUser(User user) {
        StringBuffer stringBuffer=new StringBuffer("select * from t_user ");
        Map<String,Object> map=new HashMap<>();
        if(user!=null){
            if(user.getUserName()!=null){
                map.put("userName",user.getUserName());
            }
            if(user.getPassword()!=null){
                map.put("password",user.getPassword());
            }
        }
        Object[] obj=SqlUtil.appendQueryParamSqlSupport(stringBuffer,map,true);
        List<User> userList=jdbcTemplateRead.query(stringBuffer.toString(),obj, BeanPropertyRowMapper.newInstance(User.class));
        QueryResult queryResult=new QueryResult();
        queryResult.setCount(userList.size());
        queryResult.setData(userList);
        return queryResult;
    }

    public int modifyUser(User user){
        StringBuffer stringBuffer=new StringBuffer(" update t_user set updatetime = now()");
        Map<String,Object> updateMap=new HashMap<>();
        if(user.getUserName()!=null){
            updateMap.put("userName",user.getUserName());
        }
        if(user.getPassword()!=null){
            updateMap.put("password",user.getPassword());
        }
        if(user.getOnlineAddress()!=null){
            updateMap.put("onlineaddress",user.getOnlineAddress());
        }
        Object[] obj = SqlUtil.appendUpdateSqlSupport(stringBuffer,updateMap,false);
        stringBuffer.append(" where t_user.uid = ");
        stringBuffer.append(user.getUid());
        int flag=0;
        try {
            flag=jdbcTemplateWrite.update(stringBuffer.toString(),obj);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return flag;
    }
}
