package com.thoughtworks.dolphin.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by ybhan on 9/30/14.
 */
public class CacheUtil {

    private static CacheManager cacheManager ;

    private static Cache cache;

    static {
        cacheManager = CacheManager.create();
        cache = cacheManager.getCache("DEFAULT_CACHE");
    }

    private  CacheUtil(){}

    public static  Object get(String key){
        return cache.get(key);
    }

    public static void put(String key, Object val){

        if(cache.get(key)!= null) {
            cache.remove(key);
        }

        Element element = new Element(key,val);
        cache.put(element);
    }

    public static  void remove(String key){
        cache.remove(key);
    }

    public static  void clear()
    {
        cache.removeAll();
    }
}
