package com.hdg.util;

import java.lang.reflect.Method;

/**
 * Created by huangxiaojun on 2017/6/16.
 */
public interface IAspectSupport {

    <T> T getProperty(Method method);

    String[] getPropertyArrays(Method method);
}
