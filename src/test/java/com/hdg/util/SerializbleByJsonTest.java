package com.hdg.util;

import com.hdg.entity.login.User;
import com.hdg.exception.SerializableFormatException;
import com.hdg.exception.SerializableParseException;
import com.hdg.test.BaseTest;
import com.hdg.util.serializable.impl.SerializbleByJson;
import com.hdg.util.serializable.ISerializableUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by BlueBuff on 2017/7/14.
 */
public class SerializbleByJsonTest extends BaseTest {

    private String string;

    private String stringArray;

    @Before
    public void before(){
        ISerializableUtil iSerializableUtil=new SerializbleByJson();
        User user=new User();
        user.setUserName("黄孝君");
        user.setPassword("123");
        user.setPhone(123456L);
        user.setSex("男");
        user.setStatus(true);
        user.setUid(1);
        try {
            string=iSerializableUtil.formatByObj(user);
        } catch (SerializableFormatException e) {
            e.printStackTrace();
        }
        /**集合**/
        User user1=new User();
        user1.setUserName("张三");
        User user2=new User();
        user2.setUserName("李四");
        User user3=new User();
        user3.setUserName("王五");
        User user4=new User();
        user4.setUserName("赵云");
        User user5=new User();
        user5.setUserName("马云");
        List<User> users=new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        try {
            stringArray=iSerializableUtil.formatByGather(users);
        } catch (SerializableFormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        System.out.println("string-->"+string);
        ISerializableUtil iSerializableUtil=new SerializbleByJson();
        User user= null;
        try {
            user = iSerializableUtil.parseToObj(string,User.class);
        } catch (SerializableParseException e) {
            e.printStackTrace();
        }
        System.out.println("user-->"+user);
        /**解析集合**/
        System.out.println("stringArray-->"+stringArray);
        Collection<User> users= null;
        try {
            users = iSerializableUtil.parseToGather(stringArray,User.class);
        } catch (SerializableParseException e) {
            e.printStackTrace();
        }
        System.out.println("users-->"+users);
    }
}
