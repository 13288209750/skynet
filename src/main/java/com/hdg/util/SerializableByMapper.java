package com.hdg.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdg.exception.SerializableFormatException;
import com.hdg.exception.SerializableParseException;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by BlueBuff on 2017/7/15.
 */
public class SerializableByMapper implements ISerializableUtil{
    private ObjectMapper objectMapper;

    public SerializableByMapper(){

    }

    public SerializableByMapper(ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
    }

    @Override
    public <T> String formatByObj(T obj) throws SerializableFormatException {
        String str=null;
        try {
            str=objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SerializableFormatException(e.getMessage(),e);
        }
        return str;
    }

    @Override
    public <T> T parseToObj(String string, Class<T> cls) throws SerializableParseException {
        T t=null;
        try {
            t=objectMapper.readValue(string,cls);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializableParseException(e.getMessage(),e);
        }
        return t;
    }

    @Override
    public <T> String formatByGather(Collection<T> collection) throws SerializableFormatException {
        String string=null;
        try {
            string=objectMapper.writeValueAsString(collection);
        } catch (JsonProcessingException e) {
            throw new SerializableFormatException(e.getMessage(),e);
        }
        return string;
    }

    @Override
    public <E> Collection<E> parseToGather(String string, Class<?> cls) throws SerializableParseException {
        Collection<E> collection= null;
        try {
            collection = (Collection<E>) objectMapper.readValue(string,cls);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializableParseException(e.getMessage(),e);
        }
        return collection;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public SerializableByMapper setSerializationInclusion(Include incl){
        if(incl!=null){
            this.objectMapper.setSerializationInclusion(incl);
        }
        return this;
    }
}
