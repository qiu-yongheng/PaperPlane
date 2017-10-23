package com.qyh.litemvp.cache;

/**
 * @author 邱永恒
 * @time 2017/10/23  16:19
 * @desc 缓存接口
 */

public interface ICache {
    /**
     * 添加缓存
     * @param key
     * @param value
     */
    void put(String key, Object value);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 判断是否包含key对应的缓存
     * @param key
     * @return
     */
    boolean contains(String key);

    /**
     * 移除key对应的缓存
     * @param key
     */
    void remove(String key);

    /**
     * 清除所有缓存
     */
    void clear();
}
