package com.tc.map;

/**
 * @Auther: tianchao
 * @Date: 2020/1/31 22:41
 * @Description:
 */
public interface TcMap<K,V> {
    V put(K key, V value);
    V get(K key);
    int size();
    interface Entry<K,V>{
        K getKey();
        V getValue();
    }
}
