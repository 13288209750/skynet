package com.hdg.util.serializable.impl;

import com.hdg.exception.SerializableFormatException;
import com.hdg.exception.SerializableParseException;
import com.hdg.util.serializable.ISerializableUtil;
import com.hdg.util.other.StreamUtil;

import java.io.*;
import java.util.Collection;

/**
 * Created by BlueBuff on 2017/7/15.
 */
public class SerializableByStream implements ISerializableUtil {

    private static final String TEMP_ENCODING = "ISO-8859-1";
    private static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public <T> String formatByObj(T obj) throws SerializableFormatException{
        String result=null;
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream oos=null;
        try {
            oos=new ObjectOutputStream(bos);
            oos.writeObject(obj);
            //以ISO-8859-1编码成字符串
            byte[] data=bos.toByteArray();
            //将字符串进行编码（不编码也可以，只是不编码会有很多无法打印的字符串）
            result=new String(data,TEMP_ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializableFormatException(e.getMessage(),e);
        }finally {
            try {
                StreamUtil.closeStream(bos,oos);
            } catch (IOException e) {
                e.printStackTrace();
                throw new SerializableFormatException(e.getMessage(),e);
            }
        }
        return result;
    }

    @Override
    public <T> T parseToObj(String string, Class<T> cls) throws SerializableParseException {
        T t=null;
        ObjectInputStream ois=null;
        ByteArrayInputStream bis=null;
        try {
            bis=new ByteArrayInputStream(string.getBytes(TEMP_ENCODING));
            ois=new ObjectInputStream(bis);
            t=cls.cast(ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializableParseException(e.getMessage(),e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SerializableParseException(e.getMessage(),e);
        } finally {
            try {
                StreamUtil.closeStream(ois,bis);
            } catch (IOException e) {
                e.printStackTrace();
                throw new SerializableParseException(e.getMessage(),e);
            }
        }
        return t;
    }

    @Override
    public <T> String formatByGather(Collection<T> collection) throws SerializableFormatException {
        return  formatByObj(collection);
    }

    @Override
    public <E> Collection<E> parseToGather(String string, Class<?> cls) throws SerializableParseException {
        return (Collection<E>) parseToObj(string,cls);
    }
}
