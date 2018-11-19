package com.xch.study.redis.service;

import java.util.*;

/**
 * 
 * Redis公共接口
 * 
 * @author wei.sun
 * @version 1.0
 * @since 2016-1-20
 */
public interface RedisCommonService<K, V>
{

    /**
     * 保存对象
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param value
     */
    void saveObject(K key, V value);

    /**
     * 保存对象并设置有效期(秒)
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param value
     * @param seconds
     */
    void saveObject(K key, V value, long seconds);

    /**
     * 根据key查询对象
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @return
     */
    V queryObject(K key);


    void saveMap(K key, Map<String,Object> map);
    Object queryMap(K key, V item);











    /**
     * 向Set中增加元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
    Long addElementToSet(K key, V... values);

    /**
     * 从Set中获取元素
     *
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @return
     */
    Set<V> queryElementsFromSet(K key);

    /**
     * 判断Set中是否存在某元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param o
     * @return
     */
    Boolean existsElementFromSet(K key, Object o);



    /**
     * 向List最左边插入元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param value
     * @return
     */
    Long leftPushElementToList(K key, V value);

    /**
     * 
     * 向List最左边插入所有元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
    Long leftPushAllElementsToList(K key, V... values);

    /**
     * 向List最左边插入所有元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param values
     * @return
     */
    Long leftPushAllElementsToList(K key, Collection<V> values);

    /**
     * 
     * 向List最右边插入元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param value
     * @return
     */
    Long rightPushElementToList(K key, V value);

    /**
     * 向List最右边插入所有元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
    Long rightPushAllElementsToList(K key, V... values);

    /**
     * 向List最右边插入所有元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param values
     * @return
     */
    Long rightPushAllElementsToList(K key, Collection<V> values);

    /**
     * 保存List中的元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param index
     * @param value
     */
    void saveElementToList(K key, long index, V value);

    /**
     * 
     * 从List中删除元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param i
     * @param value
     * @return
     */
    Long removeElementFromList(K key, long i, Object value);

    /**
     * 
     * 从List中获取元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param index
     * @return
     */
    V getElementFromList(K key, long index);

    /**
     * 从List中获取元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @return
     */
    List<V> queryElementsFromList(K key);

    /**
     * 移除List最左边的元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @return
     */
    V leftPopElementFromList(K key);

    /**
     * 移除List最左边的元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param seconds
     * @return
     */
    V leftPopElementFromList(K key, long seconds);

    /**
     * 移除List最右边的元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @return
     */
    V rightPopElementFromList(K key);

    /**
     * 移除List最右边的元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param seconds
     * @return
     */
    V rightPopElementFromList(K key, long seconds);

    /**
     * 获取List元素数量
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @return
     */
    Long getElementSizeFromList(K key);

    /**
     * 向Map中添加元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param hashKey
     * @param value
     */
    void putElementToMap(K key, K hashKey, V value);

    /**
     * 从Map中获取元素
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param hashKey
     * @return
     */
    V getElementFromMap(K key, K hashKey);

    /**
     * 删除对象
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     */
    void delete(K key);

    /**
     * 删除对象集合
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param keys
     */
    void delete(Collection<K> keys);

    /**
     * 设置有效期(秒)
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param seconds
     * @return
     */
    boolean expire(K key, long seconds);

    /**
     * 设置有效期到某个日期
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @param date
     * @return
     */
    boolean expireAt(K key, Date date);

    /**
     * 判断key是否存在
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param key
     * @return
     */
    boolean exists(K key);

    /**
     * 根据格式匹配满足的key
     * 
     * @since 2016-1-20
     * @author wei.sun
     * @param pattern
     * @return
     */
    Set<K> keys(K pattern);

    /**
     * 值增长
     * 
     * @since 2016-3-15
     * @author wei.sun
     * @param key
     * @param delta
     * @return
     */
    Long increment(K key, long delta);
    
    
    void addElementToZSet(K key, double score, V member);
    
    /**
     * 实现命令：ZRANGE key start stop，返回有序集 key中，指定区间内的成员。
     * @param key
     * @param start
     * @param stop
     * @return
     */
    Set<V> queryElementsFromZSetBetween(K key, double start, double stop);
    
    void removeElementFromZSet(K key, V... values);
}
