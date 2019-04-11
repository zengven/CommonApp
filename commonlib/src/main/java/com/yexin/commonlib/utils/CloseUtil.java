package com.yexin.commonlib.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * author: zengven
 * date: 2018/7/26 11:07
 * desc: 关闭工具类
 */
public class CloseUtil {

    /**
     * close closeable object
     *
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable == null)
            return;
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * close closeable objects
     *
     * @param closeables
     */

    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
