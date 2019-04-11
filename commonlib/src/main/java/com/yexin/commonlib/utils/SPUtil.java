package com.yexin.commonlib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Set;

/**
 * author: zengven
 * date: 2017/6/7
 * Desc: SharedPreferences缓存工具类
 */
public class SPUtil implements Serializable {

    private static final String PREFERENCE_FILE_NAME = "oil_config";
    private SharedPreferences mPreferences = null;
    private static SPUtil sInstance = null;

    private SPUtil() {
        if (sInstance != null)
            throw new RuntimeException("已创建实例,不能重复创建!!!"); //防止暴利反射创建实例
    }

    public static SPUtil getInstance() {
        if (sInstance == null) {
            synchronized (SPUtil.class) {
                if (sInstance == null) {
                    sInstance = new SPUtil();
                }
            }
        }
        return sInstance;
    }

    public void Init(Context context) {
        mPreferences = context.getApplicationContext().getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
    }

    public boolean putBoolean(String key, boolean value) {
        checkNull();
        return mPreferences.edit().putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        checkNull();
        return mPreferences.getBoolean(key, defValue);
    }

    public boolean putString(String key, String value) {
        checkNull();
        return mPreferences.edit().putString(key, value).commit();
    }

    public String getString(String key, String defValue) {
        checkNull();
        return mPreferences.getString(key, defValue);
    }

    public boolean putInt(String key, int value) {
        checkNull();
        return mPreferences.edit().putInt(key, value).commit();
    }

    public int getInt(String key, int defValue) {
        checkNull();
        return mPreferences.getInt(key, defValue);
    }

    public boolean putFloat(String key, float value) {
        checkNull();
        return mPreferences.edit().putFloat(key, value).commit();
    }

    public float getFloat(String key, float defValue) {
        checkNull();
        return mPreferences.getFloat(key, defValue);
    }

    public boolean putLong(String key, long value) {
        checkNull();
        return mPreferences.edit().putLong(key, value).commit();
    }

    public long getLong(String key, long defValue) {
        checkNull();
        return mPreferences.getLong(key, defValue);
    }

    public boolean putStringSet(String key, Set<String> values) {
        checkNull();
        return mPreferences.edit().putStringSet(key, values).commit();
    }

    public Set<String> getStringSet(String key, Set<String> defValues) {
        checkNull();
        return mPreferences.getStringSet(key, defValues);
    }

    public boolean remove(String key) {
        checkNull();
        return mPreferences.edit().remove(key).commit();
    }

    public void remove(String... keys) {
        checkNull();
        if (keys.length != 0) {
            for (String key : keys) {
                boolean commit = mPreferences.edit().remove(key).commit();
            }
        }
    }

    public void clear() {
        checkNull();
        mPreferences.edit().clear().apply();
    }

    private void checkNull() {
        if (mPreferences == null)
            throw new IllegalStateException("you must call init method in application");
    }

    /**
     * 防止序列化破坏单例模式
     * 无论是实现Serializable接口，或是Externalizable接口，当从I/O流中读取对象时，readResolve()方法都会被调用到。
     * 实际上就是用readResolve()中返回的对象直接替换在反序列化过程中创建的对象。
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        return sInstance;
    }
}
