package com.hdg.util;

import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;

/**
 * Created by BlueBuff on 2017/7/14.
 */
public interface ISerializableUtil {

    <T> String formatByObj(T obj);

    <T> T parseToObj(String string,Class<T> cls);

    <T> String formatByGather(Collection<T> collection);

    <E> Collection<E> parseToGather(String string,Class<T> cls);
}
