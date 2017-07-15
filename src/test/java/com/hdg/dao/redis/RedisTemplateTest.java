package com.hdg.dao.redis;

import com.hdg.entity.User;
import com.hdg.test.BaseTest;
import com.hdg.util.PrintUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by BlurBuff on 2017/7/15.
 */
public class RedisTemplateTest extends BaseTest{

    @Autowired
    private RedisTemplate redisTemplate;

    @Before
    public void before(){
        User user=new User();
        user.setUserName("黄孝君");
        user.setPassword("123");
        user.setStatus(true);
        user.setSex("男");
        user.setPhone(123456L);
        user.setUid(1);
        boolean flag1=redisTemplate.set("test:user",user);
        System.out.println("flag1-->"+flag1);
        /**####################################################**/
        User user1=new User();
        User user2=new User();
        User user3=new User();
        User user4=new User();
        User user5=new User();

        user1.setUserName("张三");
        user2.setUserName("李四");
        user3.setUserName("王五");
        user4.setUserName("赵云");
        user5.setUserName("马云");

        List<User> list=new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);

        Set<User> userSet=new HashSet<User>();
        userSet.add(user1);
        userSet.add(user2);
        userSet.add(user3);
        userSet.add(user4);
        userSet.add(user5);

        Map<String,User> map=new HashMap<String, User>();
        map.put("user1",user1);
        map.put("user2",user2);
        map.put("user3",user3);
        map.put("user4",user4);
        map.put("user5",user5);

        boolean flag2=redisTemplate.lpush("test:list",list);
        boolean flag3=redisTemplate.lpush("test:set",userSet);
        boolean flag4=redisTemplate.hmSet("test:map",map);

        System.out.println("flag2-->"+flag2);
        System.out.println("flag3-->"+flag3);
        System.out.println("flag4-->"+flag4);
    }

    @Test
    public void testSet(){
        User user=redisTemplate.get("test:user",User.class);
        System.out.println("user-->"+user);
        /**#######################################################**/
        Collection<User> collection=redisTemplate.lRangeAll("test:set",User.class);
        PrintUtil.println(collection);
        /**#########################################################**/
        Map<String,User> map=redisTemplate.hmGet("test:map",String.class,User.class);
        PrintUtil.println(map);
    }
}
