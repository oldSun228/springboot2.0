package com.xch.study.redis.service.impl;

import com.xch.study.redis.service.RedisCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis公共类
 *
 * @author wei.sun
 * @version 1.0
 * @since 2016-1-20
 */
@Service("redisCommonService")
public class RedisCommonServiceImpl<K, V> implements RedisCommonService<K, V> {

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    public RedisCommonServiceImpl() {
    }

    public void del(K key) {
        redisTemplate.delete(key);
    }

    public void del(Collection<K> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public void saveObject(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void saveObject(K key, V value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public V queryObject(K key) {
        V value = redisTemplate.opsForValue().get(key);
        return value;
    }

    @Override
    public void saveMap(K key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public Object queryMap(K key, V item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long addElementToSet(K key, V... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Boolean existsElementFromSet(K key, Object o) {
        return redisTemplate.opsForSet().isMember(key, o);
    }

    @Override
    public Set<V> queryElementsFromSet(K key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 压栈
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long leftPushElementToList(K key, V value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long leftPushAllElementsToList(K key, V... values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public Long leftPushAllElementsToList(K key, Collection<V> values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public Long rightPushElementToList(K key, V value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long rightPushAllElementsToList(K key, V... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    @Override
    public Long rightPushAllElementsToList(K key, Collection<V> values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    @Override
    public void saveElementToList(K key, long index, V value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    @Override
    public Long removeElementFromList(K key, long i, Object value) {
        return redisTemplate.opsForList().remove(key, i, value);
    }

    @Override
    public List<V> queryElementsFromList(K key) {
        ListOperations<K, V> listOps = redisTemplate.opsForList();
        long start = 0;
        long end = listOps.size(key) - 1;
        return listOps.range(key, start, end);
    }

    /**
     * 范围检索
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<V> queryRangeElementsFromList(K key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public V getElementFromList(K key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public V leftPopElementFromList(K key) {

        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public V leftPopElementFromList(K key, long seconds) {
        return redisTemplate.opsForList().leftPop(key, seconds,
                TimeUnit.SECONDS);
    }

    @Override
    public V rightPopElementFromList(K key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public V rightPopElementFromList(K key, long seconds) {
        return redisTemplate.opsForList().rightPop(key, seconds,
                TimeUnit.SECONDS);
    }

    @Override
    public Long getElementSizeFromList(K key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public void putElementToMap(K key, K hashKey, V value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public V getElementFromMap(K key, K hashKey) {
        return (V) redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public void delete(K key) {
        redisTemplate.delete(key);
    }

    @Override
    public void delete(Collection<K> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public boolean expire(K key, long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean expireAt(K key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    @Override
    public boolean exists(K key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Set<K> keys(K pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public Long increment(K key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }
    //SortedSet（有序集合）

    /**
     * 实现命令：ZADD key score member，将一个 member元素及其 score值加入到有序集 key当中。
     *
     * @param key
     * @param score
     * @param member
     */
    public void addElementToZSet(K key, double score, V member) {
        redisTemplate.opsForZSet().add(key, member, score);
    }

    /**
     * 实现命令：ZRANGE key start stop，返回有序集 key中，指定区间内的成员。
     *
     * @param key
     * @param start
     * @param stop
     * @return
     */
    public Set<V> queryElementsFromZSetBetween(K key, double start, double stop) {
        return redisTemplate.opsForZSet().range(key, (long) start, (long) stop);
    }

    public void removeElementFromZSet(K key, V... values) {
        redisTemplate.opsForZSet().remove(key, values);
    }

}
