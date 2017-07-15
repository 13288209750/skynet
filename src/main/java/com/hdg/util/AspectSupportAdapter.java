package com.hdg.util;

import java.lang.reflect.Method;

/**
 * 切面IAspectSupport接口-适配器
 * Created by huangxiaojun on 2017/6/16.
 */
public abstract class AspectSupportAdapter implements IAspectSupport{

    @Override
    public <T> T getProperty(Method method) {
        return null;
    }

    @Override
    public String[] getPropertyArrays(Method method) {
        return new String[0];
    }

}
