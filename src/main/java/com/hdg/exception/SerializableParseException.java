package com.hdg.exception;

/**
 * Created by BlueBuff on 2017/7/15.
 */
public class SerializableParseException extends Exception{

    public SerializableParseException(String msg){
        super(msg);
    }

    public SerializableParseException(String msg,Throwable rootCause){
        super(msg,rootCause);
    }

}
