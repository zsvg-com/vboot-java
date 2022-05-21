package vboot.common.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class CacheDao<T> {

    private RedisTemplate<String, T> redisTemplate;

    @Autowired
    public CacheDao(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public T getCache(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean setCache(String key, T value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean setCache(String key, T value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                setCache(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object getHashCache(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> getHashCache(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean setHashCache(String key, Map<String, T> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    /**
//     * HashSet 并设置时间
//     *
//     * @param key  键
//     * @param map  对应多个键值
//     * @param time 时间(秒)
//     * @return true成功 false失败
//     */
//    public boolean setHashCache(String key, Map<String, T> map, long time) {
//        try {
//            redisTemplate.opsForHash().putAll(key, map);
//            if (time > 0) {
//                setExpireTime(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean setHashCache(String key, String item, T value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    /**
//     * 向一张hash表中放入数据,如果不存在将创建
//     *
//     * @param key     键
//     * @param hashKey 项
//     * @param value   值
//     * @param time    时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
//     * @return true 成功 false失败
//     */
//    public boolean setHashCache(String key, String hashKey, T value, long time) {
//        try {
//            redisTemplate.opsForHash().put(key, hashKey, value);
//            if (time > 0) {
//                setExpireTime(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}