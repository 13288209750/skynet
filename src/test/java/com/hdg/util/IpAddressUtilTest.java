package com.hdg.util;

import com.hdg.entity.basic.IpAddress;
import com.hdg.test.BaseTest;
import org.junit.Test;

/**
 * Created by BlueBuff on 2017/7/15.
 */
public class IpAddressUtilTest extends BaseTest{

    @Test
    public void test1(){
        IpAddress ipAddress1=new IpAddress();
        ipAddress1.setAddress("192.168.1.1");
        ipAddress1.setHostName("abc");
        IpAddress ipAddress2=new IpAddress();
        ipAddress2.setHostName("qwe");
        ipAddress2.setAddress("192.168.1.1");
        System.out.println(ipAddress1.equals(ipAddress2));
    }
}
