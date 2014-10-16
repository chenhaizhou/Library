package com.thoughtworks.dolphin.util;

import org.junit.*;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mybatis.xml", "/conf/spring.xml"})
public class CacheTest {

    @Test
    public void shouldStoreAndGetCacheValue() throws Exception {
        String str = "abc";
        CacheUtil.getInstance().put("str", str);
        String value = (String) CacheUtil.getInstance().get("str");
        assertEquals(str, value);
    }

    @Test
    public void shouldRemoveCacheValue() throws Exception {
        final String value1 = "123";
        CacheUtil.getInstance().put(value1, value1);
        CacheUtil.getInstance().remove(value1);
        assertNull(CacheUtil.getInstance().get(value1));
    }

    @Test
    public void shouldClearCacheValues() throws Exception {
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

}
