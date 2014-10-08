package com.thoughtworks.dolphin.Util;

import com.thoughtworks.dolphin.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by ybhan on 10/8/14.
 */
public class CacheTest {

    @Test
    public void Cache_Can_Generate(){

        CacheManager cacheManager = new CacheManager("./ehcache.xml");

        assertNotNull(cacheManager);

        System.out.println("Caches: " + Arrays.toString( cacheManager.getCacheNames()));

        Cache cache = cacheManager.getCache("DEFAULT_CACHE");

        assertNotNull(cache);
    }

}
