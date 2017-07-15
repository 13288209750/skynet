package com.hdg.exception;

/**
 * Created by BlueBuff on 2017/7/15.
 */
public class SerializableFormatException extends Exception {

    public SerializableFormatException(String msg){
        super(msg);
    }

    public SerializableFormatException(String msg,Throwable rootCause){
        super(msg,rootCause);
    }
}
