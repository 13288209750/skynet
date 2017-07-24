package com.hdg.dao;

import com.hdg.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * 这里的@MapperScan就是Mapper扫描器中所需要的配置，会自动生成代理对象。
 * 注意，接口中的方法名称要和对应的MyBatis映射文件中的语句的id值一样，因为生成的
 * 动态代理，会根据这个匹配相应的Sql语句执行。另外就是方法的参数和返回值也需要注
 * 意。接口中的方法如何定义，对应的MyBatis映射文件就应该进行相应的定义。
 * 最后，标注中的userDao是用来作为Spring的Bean的id(或name)进行使用的，方便我
 * 们在Service层进行注入使用。
 *
 * Created by BlueBuff on 2017/7/10.
 */
@Repository
public class UserDao{

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<User> selUser(User user){
        return sqlSessionTemplate.selectList(this.getClass().getName()+".selUser",user);
    }

    public int modifyUser(User user){
        return sqlSessionTemplate.update(this.getClass().getName()+".modifyUser",user);
    }
}
