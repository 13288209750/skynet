package com.hdg.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by BlueBuff on 2017/7/15.
 */
public class StreamUtil {

    public static void closeStream(Closeable ...closeables) throws IOException {
        if(closeables!=null){
            for(Closeable c:closeables){
                c.close();
            }
        }
    }
}
