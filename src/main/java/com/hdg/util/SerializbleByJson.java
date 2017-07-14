package com.hdg.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by BlueBuff on 2017/7/14.
 */
public class SerializbleByJson implements ISerializableUtil{

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
    public <E> Collection<E> parseToGather(String string, Class<T> cls) {
        JSONArray jsonArray=JSONArray.fromObject(string);
        Collection<E> collection=JSONArray.toCollection(jsonArray,cls);
        return collection;
    }
}
