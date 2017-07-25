package com.hdg.util.other;

import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by BlueBuff on 2017/7/15.
 */
public class PrintUtil {

    public static <K,V> void println(Map<K,V> map){
        isEmpty(map,Map.class);
        System.out.println("#######---开始遍历集合---########");
        int index=0;
        Set<Map.Entry<K,V>> entrySet=map.entrySet();
        K key=null;
        V value=null;
        for(Map.Entry<K,V> entry:entrySet){
            key=entry.getKey();
            value=entry.getValue();
            System.out.println("index:"+(++index)+"\t 当前元素值为key:"+key+"\tvalue:"+value);
        }
        System.out.println("#集合大小:"+index);
        System.out.println("#######---集合遍历完毕---########");
    }

    public static <E> void println(Collection<E> collection){
        isEmpty(collection,Collection.class);
        System.out.println("#######---开始遍历集合---########");
        int index=0;
        Iterator<E> iterator=collection.iterator();
        while (iterator.hasNext()){
            E e=iterator.next();
            System.out.println("index:"+(++index)+"\t 当前元素值为:"+e);
        }
        System.out.println("#集合大小:"+index);
        System.out.println("#######---集合遍历完毕---########");
    }

    private static void isEmpty(Object obj,Class<?> cls){
        if(obj==null){
            System.out.println("###########################");
            System.out.println("--------集合不能为null-------");
            System.out.println("###########################");
            return;
        }
        if(cls==Map.class){
            Map map=(Map)obj;
            if(map.isEmpty()){
                System.out.println("###########################");
                System.out.println("-------集合中没有任何元素------");
                System.out.println("###########################");
            }
        }else if(cls==Collection.class){
            Collection collection=(Collection) obj;
            if(collection.isEmpty()){
                System.out.println("###########################");
                System.out.println("-------集合中没有任何元素------");
                System.out.println("###########################");
            }
        }
    }
}
