package com.hdg.util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by BlueBuff on 2017/7/14.
 */
public interface IRedisTransitionUtil {

    <T> void storageByObj(T t);

    <T> void storageByGather(Collection<T> collection);

    <T> void storageByMap(Map<String,T> map);

}
