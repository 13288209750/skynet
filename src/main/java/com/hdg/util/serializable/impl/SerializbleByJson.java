package com.hdg.util.serializable.impl;

import com.hdg.util.serializable.ISerializableUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Collection;

/**
 * Created by BlueBuff on 2017/7/14.
 */
public class SerializbleByJson implements ISerializableUtil {

    @Override
    public <T> String formatByObj(T obj) {
        JSONObject jsonObject=JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    @Override
    public <T> T parseToObj(String string,Class<T> cls) {
        JSONObject jsonObject=JSONObject.fromObject(string);
        T t= (T) JSONObject.toBean(jsonObject,cls);
        return t;
    }

    @Override
    public <E> String formatByGather(Collection<E> collection) {
        JSONArray jsonArray=JSONArray.fromObject(collection);
        return jsonArray.toString();
    }

    @Override
    public <E> Collection<E> parseToGather(String string, Class<?> cls) {
        JSONArray jsonArray=JSONArray.fromObject(string);
        Collection<E> collection=JSONArray.toCollection(jsonArray,cls);
        return collection;
    }
}
