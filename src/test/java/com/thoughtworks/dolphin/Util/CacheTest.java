package com.thoughtworks.dolphin.util;

import org.junit.Before;
import org.junit.Test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by ybhan on 10/8/14.
 */
public class CacheTest {

    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"conf/spring.xml"});

    }

    @Test
    public void cache_Can_Generate(){

        Cache cache = cacheManager.getCache("DEFAULT_CACHE");
        assertNotNull(cache);

    }

}
