package com.thoughtworks.dolphin.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {

    private static Cache cache;

    static {
        CacheManager cacheManager = CacheManager.getInstance();
        cache = cacheManager.getCache("DEFAULT_CACHE");
    }

    private CacheUtil() {
    }

    public static Object get(String key) {
        Element element = cache.get(key);
        if (element == null) {
            return null;
        }
        return cache.get(key).getObjectValue();
    }

    public static void put(String key, Object val) {

        if (cache.get(key) != null) {
            cache.remove(key);
        }

        Element element = new Element(key, val);
        cache.put(element);
    }

    public static void remove(String key) {
        cache.remove(key);
    }

    public static void clear() {
        cache.removeAll();
    }
}
