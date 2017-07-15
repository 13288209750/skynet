package com.hdg.util;

import com.hdg.exception.SerializableFormatException;
import com.hdg.exception.SerializableParseException;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;

/**
 * Created by BlueBuff on 2017/7/14.
 */
public interface ISerializableUtil {

    <T> String formatByObj(T obj) throws SerializableFormatException;

    <T> T parseToObj(String string,Class<T> cls) throws SerializableParseException;

    <T> String formatByGather(Collection<T> collection) throws SerializableFormatException;

    <E> Collection<E> parseToGather(String string,Class<?> cls) throws SerializableParseException;
}
