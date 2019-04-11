package com.yexin.commonlib.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author: zengven
 * date: 2019/3/4 15:14
 * desc: 反射工具类
 */
public class ReflectUtil {

    /**
     * create object by default constructor
     *
     * @param className
     * @return
     */
    public static Object createObject(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return createObject(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * create object by default constructor
     *
     * @param clazz
     * @return
     */
    public static Object createObject(Class<?> clazz) {
        Class[] parameterTypes = new Class[]{};
        Object[] initargs = new Object[]{};
        return createObject(clazz, parameterTypes, initargs);
    }

    /**
     * create object by one parameters constructor
     *
     * @param clazz
     * @param parameterType
     * @param initarg
     * @return
     */
    public static Object createObject(Class<?> clazz, Class<?> parameterType, Object initarg) {
        Class[] parameterTypes = new Class[]{parameterType};
        Object[] initargs = new Object[]{initarg};
        return createObject(clazz, parameterTypes, initargs);
    }

    /**
     * create object by one parameters constructor
     *
     * @param className
     * @param parameterType
     * @param initarg
     * @return
     */
    public static Object createObject(String className, Class<?> parameterType, Object initarg) {
        try {
            Class<?> aClass = Class.forName(className);
            return createObject(aClass, parameterType, initarg);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * create object by multiple parameters constructor
     *
     * @param clazz
     * @param parameterTypes
     * @param initargs
     * @return
     */
    public static Object createObject(Class<?> clazz, Class<?>[] parameterTypes, Object[] initargs) {
        try {
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(parameterTypes);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(initargs);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * create object by multiple parameters constructor
     *
     * @param className
     * @param parameterTypes
     * @param initargs
     * @return
     */
    public static Object createObject(String className, Class<?>[] parameterTypes, Object[] initargs) {
        try {
            Class<?> aClass = Class.forName(className);
            return createObject(aClass, parameterTypes, initargs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * invoke instance method
     *
     * @param obj
     * @param methodName
     * @param parameterTypes
     * @param parameterArgs
     * @return
     */
    public static Object invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object[] parameterArgs) {
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(obj, parameterArgs);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * invoke instance method
     *
     * @param obj
     * @param methodName
     * @param parameterType
     * @param parameterArg
     * @return
     */
    public static Object invokeMethod(Object obj, String methodName, Class<?> parameterType, Object parameterArg) {
        Class<?>[] parameterTypes = {parameterType};
        Object[] parameterArgs = {parameterArg};
        return invokeMethod(obj, methodName, parameterTypes, parameterArgs);
    }

    /**
     * invoke instance method
     *
     * @param obj
     * @param methodName
     * @return
     */
    public static Object invokeMethod(Object obj, String methodName) {
        Class<?>[] parameterTypes = {};
        Object[] parameterArgs = {};
        return invokeMethod(obj, methodName, parameterTypes, parameterArgs);
    }

    /**
     * invoke static mathod
     *
     * @param className
     * @param methodName
     * @return
     */
    public static Object invokeStaticMethod(String className, String methodName) {
        Class[] parameterTypes = {};
        Object[] parameterArgs = {};
        return invokeStaticMethod(className, methodName, parameterTypes, parameterArgs);
    }

    /**
     * invoke static mathod
     *
     * @param className
     * @param methodName
     * @param parameterType
     * @param parameterArg
     * @return
     */
    public static Object invokeStaticMethod(String className, String methodName, Class<?> parameterType, Object parameterArg) {
        Class[] parameterTypes = {parameterType};
        Object[] parameterArgs = {parameterArg};
        return invokeStaticMethod(className, methodName, parameterTypes, parameterArgs);
    }

    /**
     * invoke static mathod
     *
     * @param className
     * @param methodName
     * @param parameterTypes
     * @param parameterArgs
     * @return
     */
    public static Object invokeStaticMethod(String className, String methodName, Class<?>[] parameterTypes, Object[] parameterArgs) {
        try {
            Class<?> aClass = Class.forName(className);
            return invokeStaticMethod(aClass, methodName, parameterTypes, parameterArgs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * invoke static mathod
     *
     * @param clazz
     * @param methodName
     * @return
     */
    public static Object invokeStaticMethod(Class<?> clazz, String methodName) {
        Class[] parameterTypes = {};
        Object[] parameterArgs = {};
        return invokeStaticMethod(clazz, methodName, parameterTypes, parameterArgs);
    }

    /**
     * invoke static mathod
     *
     * @param clazz
     * @param methodName
     * @param parameterType
     * @param parameterArg
     * @return
     */
    public static Object invokeStaticMethod(Class<?> clazz, String methodName, Class<?> parameterType, Object parameterArg) {
        Class[] parameterTypes = {parameterType};
        Object[] parameterArgs = {parameterArg};
        return invokeStaticMethod(clazz, methodName, parameterTypes, parameterArgs);
    }

    /**
     * invoke static mathod
     *
     * @param clazz
     * @param methodName
     * @param parameterTypes
     * @param parameterArgs
     * @return
     */
    public static Object invokeStaticMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Object[] parameterArgs) {
        try {
            Method declaredMethod = clazz.getDeclaredMethod(methodName, parameterTypes);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, parameterArgs);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get field object
     *
     * @param obj
     * @param filedName
     * @return
     */
    public static Object getFieldObject(Object obj, String filedName) {
        return getFieldObject(obj.getClass(), obj, filedName);
    }

    /**
     * get field object
     *
     * @param className
     * @param obj
     * @param filedName
     * @return
     */
    public static Object getFieldObject(String className, Object obj, String filedName) {
        try {
            Class obj_class = Class.forName(className);
            return getFieldObject(obj_class, obj, filedName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get field object
     *
     * @param clazz
     * @param obj
     * @param filedName
     * @return
     */
    public static Object getFieldObject(Class clazz, Object obj, String filedName) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * set field object
     *
     * @param obj
     * @param filedName
     * @param filedVaule
     */
    public static void setFieldObject(Object obj, String filedName, Object filedVaule) {
        setFieldObject(obj.getClass(), obj, filedName, filedVaule);
    }

    /**
     * set field object
     *
     * @param clazz
     * @param obj
     * @param filedName
     * @param filedVaule
     */
    public static void setFieldObject(Class clazz, Object obj, String filedName, Object filedVaule) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(obj, filedVaule);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * set field object
     *
     * @param className
     * @param obj
     * @param filedName
     * @param filedVaule
     */
    public static void setFieldObject(String className, Object obj, String filedName, Object filedVaule) {
        try {
            Class obj_class = Class.forName(className);
            setFieldObject(obj_class, obj, filedName, filedVaule);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get static field object
     *
     * @param className
     * @param filedName
     * @return
     */
    public static Object getStaticFieldObject(String className, String filedName) {
        return getFieldObject(className, null, filedName);
    }

    /**
     * get static field object
     *
     * @param clazz
     * @param filedName
     * @return
     */
    public static Object getStaticFieldObject(Class clazz, String filedName) {
        return getFieldObject(clazz, null, filedName);
    }

    /**
     * set static field object
     *
     * @param classname
     * @param filedName
     * @param filedVaule
     */
    public static void setStaticFieldObject(String classname, String filedName, Object filedVaule) {
        setFieldObject(classname, null, filedName, filedVaule);
    }

    /**
     * set static field object
     *
     * @param clazz
     * @param filedName
     * @param filedVaule
     */
    public static void setStaticFieldObject(Class clazz, String filedName, Object filedVaule) {
        setFieldObject(clazz, null, filedName, filedVaule);
    }
}
