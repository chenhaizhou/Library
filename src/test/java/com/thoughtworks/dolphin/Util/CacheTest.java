package com.thoughtworks.dolphin.util;

import net.sf.ehcache.Element;
import org.junit.*;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static junit.framework.Assert.*;
@Ignore
public class CacheTest {

    private static ApplicationContext context;

    @BeforeClass
    public static void setUpForClass() {
        context = new ClassPathXmlApplicationContext(new String[] {"file:src/test/resources/conf/spring.xml", "file:src/test/resources/conf/spring-mybatis.xml"});
    }

    @Test
    public void testCacheUtilStoreValue() throws Exception {
        String str = "abc";
        CacheUtil.getInstance().put("str", str);
        String value = (String) CacheUtil.getInstance().get("str");
        assertEquals(str, value);
    }

    @Test
    public void testClearCache() throws Exception {
        final String value1 = "123";
        final String value2 = "456";
        final String value3 = "789";
        CacheUtil.getInstance().put(value1, value1);
        CacheUtil.getInstance().put(value2, value2);
        CacheUtil.getInstance().put(value3, value3);

        CacheUtil.getInstance().clear();
        assertNull(CacheUtil.getInstance().get(value1));
        assertNull(CacheUtil.getInstance().get(value2));
        assertNull(CacheUtil.getInstance().get(value3));
    }

    @Test
    public void testRemoveCache() throws Exception {
        final String value1 = "123";
        CacheUtil.getInstance().put(value1, value1);
        CacheUtil.getInstance().remove(value1);
        assertNull(CacheUtil.getInstance().get(value1));
    }

    @AfterClass
    public static void destroyContext(){
        ((ClassPathXmlApplicationContext) context).close();
    }
}
