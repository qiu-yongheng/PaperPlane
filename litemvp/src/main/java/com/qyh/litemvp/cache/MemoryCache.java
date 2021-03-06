package com.qyh.litemvp.cache;

import android.text.TextUtils;
import android.util.LruCache;

/**
 * @author 邱永恒
 * @time 2017/10/23  16:19
 * @desc 内存缓存
 */

public class MemoryCache implements ICache{
    private LruCache<String, Object> cache;
    private static MemoryCache instance;

    private MemoryCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        // 创建LruCache
        cache = new LruCache(cacheSize);
    }

    public static MemoryCache getInstance() {
        if (instance == null) {
            synchronized (MemoryCache.class) {
                if (instance == null) {
                    instance = new MemoryCache();
                }
            }
        }
        return instance;
    }

    /**
     * 添加缓存到LruCache
     * @param key
     * @param value
     */
    @Override
    public synchronized void put(String key, Object value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }

        if (cache.get(key) != null) {
            cache.remove(key);
        }
        cache.put(key, value);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    //    public synchronized <T> T get(String key, Class<T> clazz) {
    //        try {
    //            return (T) cache.get(key);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            ViseLog.e(e);
    //        }
    //        return null;
    //    }

    /**
     * 移除缓存
     * @param key
     */
    @Override
    public void remove(String key) {
        if (cache.get(key) != null) {
            cache.remove(key);
        }
    }

    /**
     * 是否包含对应缓存
     * @param key
     * @return
     */
    @Override
    public boolean contains(String key) {
        return cache.get(key) != null;
    }

    /**
     * 清空缓存
     */
    @Override
    public void clear() {
        cache.evictAll();
    }
}
