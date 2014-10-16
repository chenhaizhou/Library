package com.thoughtworks.dolphin.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {

    private static Cache cache;

    private static CacheUtil instance;

    private CacheUtil() {
        if (cache == null) {
            CacheManager cacheManager = CacheManager.getInstance();
            cache = cacheManager.getCache("DEFAULT_CACHE");
        }
    }

    public static synchronized CacheUtil getInstance() {
        if (instance == null) {
            instance = new CacheUtil();
        }
        return instance;
    }

    public Object get(String key) {
        Element element = cache.get(key);
        if (element == null) {
            return null;
        }
        return cache.get(key).getObjectValue();
    }

    public void put(String key, Object val) {
        Element element = new Element(key, val);
        cache.put(element);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.removeAll();
    }
}
