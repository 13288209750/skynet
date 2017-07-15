package com.hdg.dao.redis;

import com.hdg.exception.SerializableFormatException;
import com.hdg.exception.SerializableParseException;
import com.hdg.util.ISerializableUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by BlueBuff on 2017/7/15.
 */
@Repository
public class RedisTemplate {

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Resource(name = "serializableByMapper")
    private ISerializableUtil serializable;


    public <T> T get(String key,Class<T> cls){
        T t=null;
        ShardedJedis jedis = shardedJedisPool.getResource();
        String str=jedis.get(key);
        try {
            t=serializable.parseToObj(str,cls);
        } catch (SerializableParseException e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }

    public <T> boolean set(String key, T t) {
        String value = null;
        ShardedJedis jedis = null;
        try {
            value = serializable.formatByObj(t);
            jedis = shardedJedisPool.getResource();
            jedis.set(key,value);
        } catch (SerializableFormatException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return true;
    }

    public <E> boolean lpush(String key, Collection<E> collection){
        if(key==null||collection==null||collection.isEmpty()){
            return false;
        }
        ShardedJedis jedis = shardedJedisPool.getResource();
        Iterator<E> iterator = collection.iterator();
        String[] values=new String[collection.size()];
        String value=null;
        int index=0;
        while (iterator.hasNext()){
            E e=iterator.next();
            try {
                value=serializable.formatByObj(e);
            } catch (SerializableFormatException e1) {
                e1.printStackTrace();
            }
            values[index++]=value;
        }
        jedis.lpush(key,values);
        return true;
    }

    public <E> Collection<E> lRange(String key,int start,int end,Class<?> cls){
        ShardedJedis jedis = shardedJedisPool.getResource();
        List<String> list=jedis.lrange(key,start,end);
        Collection<E> collection=new ArrayList<E>();
        for (int i=0,len=list.size();i<len;i++){
            String value=list.get(i);
            E e= null;
            try {
                e = (E) serializable.parseToObj(value,cls);
                collection.add(e);
            } catch (SerializableParseException e1) {
                e1.printStackTrace();
            }
            collection.add(e);
        }
        return collection;
    }

    public <E> Collection<E> lRangeAll(String key,Class<?> cls){
        return lRange(key,0,-1,cls);
    }

    public <K,V> Map<K,V> hmGet(String key,Class<K> clsK,Class<V> clsV){
        ShardedJedis jedis=shardedJedisPool.getResource();
        Map<String,String> map=jedis.hgetAll(key);
        Set<Map.Entry<String,String>> set= map.entrySet();
        String keyVal=null;
        String valueVal=null;
        Map<K,V> tempMap=new HashMap<K,V>();
        for(Map.Entry<String,String> entry:set){
            keyVal=entry.getKey();
            valueVal=entry.getValue();
            K k=null;
            V v=null;
            try {
                k=serializable.parseToObj(keyVal,clsK);
                v=serializable.parseToObj(valueVal,clsV);
                tempMap.put(k,v);
            } catch (SerializableParseException e) {
                e.printStackTrace();
            }
        }
        return tempMap;
    }


    public <K,V> boolean hmSet(String key,Map<K,V> map){
        if(map==null||key==null){
            return false;
        }
        ShardedJedis jedis = null;
        jedis=shardedJedisPool.getResource();
        Map<String,String> tempMap=new HashMap<String, String>(map.size());
        Set<Map.Entry<K, V>> entry=  map.entrySet();
        String field=null;
        String value=null;
        for(Map.Entry<K,V> kvEntry:entry){
            K k=kvEntry.getKey();
            V v=kvEntry.getValue();
            try {
                field=serializable.formatByObj(k);
                value=serializable.formatByObj(v);
                tempMap.put(field,value);
            } catch (SerializableFormatException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (!tempMap.isEmpty()){
            jedis.hmset(key,tempMap);
        }else {
            return false;
        }
        return true;
    }
}
