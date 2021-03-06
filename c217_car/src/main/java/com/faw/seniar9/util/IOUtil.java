package com.faw.seniar9.util;

/**
 * Created by wyc on 2018/6/22.
 */

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by 陈丰尧 on 2017/2/2.
 */

public class IOUtil {
    public static void closeAll(Closeable... closeables){
        if(closeables == null){
            return;
        }
        for (Closeable closeable : closeables) {
            if(closeable!=null){
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}