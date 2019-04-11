package com.yexin.commonlib.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * author: zengven
 * date: 2018/4/3 11:30
 * desc: 集合相关工具类
 */
public class CollectionUtil {

    /**
     * 判断单链集合是否为空
     *
     * @param collection
     * @param <T>
     * @return Returns true if this list contains no elements.
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断单链集合是否有元素
     *
     * @param collection
     * @param <T>
     * @return Returns true if this list contains elements.
     */
    public static <T> boolean hasElements(Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 判断双链集合是否为空
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return {@code true} if this map contains no key-value mappings
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断双链集合是否有元素
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return {@code true} if this map contains key-value element
     */
    public static <K, V> boolean hasElements(Map<K, V> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 根据value获取key值
     *
     * @param map
     * @param value
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> K getKey(Map<K, V> map, V value) {
        K key = null;
        if (map == null)
            return key;
        Set<Map.Entry<K, V>> entrySet = map.entrySet();
        for (Map.Entry<K, V> entry : entrySet) {
            V v = entry.getValue();
            if ((v == value) || (v != null && v.equals(value))) {
                key = entry.getKey();
                return key;
            }
        }
        return key;
    }

    /**
     * 根据value移除当前元素
     *
     * @param map
     * @param value
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> remove(Map<K, V> map, V value) {
        if (map == null)
            return map;
        Set<Map.Entry<K, V>> entrySet = map.entrySet();
        Iterator<Map.Entry<K, V>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, V> next = iterator.next();
            V v = next.getValue();
            if ((v == value) || (v != null && v.equals(value))) {
                iterator.remove();
            }
        }
        return map;
    }

    /**
     * 根据value移除所有元素
     *
     * @param collection
     * @param value
     * @param <T>
     * @return
     */
    public static <T> Collection<T> remove(Collection<T> collection, T value) {
        if (collection == null)
            return collection;
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if ((next == value) || (next != null && next.equals(value))) {
                iterator.remove();
            }
        }
        return collection;
    }
}