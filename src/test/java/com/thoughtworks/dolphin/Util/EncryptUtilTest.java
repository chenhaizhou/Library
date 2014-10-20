package com.thoughtworks.dolphin.util;

import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by lzwu on 10/20/14.
 */
public class EncryptUtilTest {

    @Test
    public void shouldEncryptPasswordTo32Length() throws Exception {

        String text = "admin";
        String encryptedText = EncryptUtil.encrypt(text);
        assertEquals(32, encryptedText.length());

        text = "222";
        encryptedText = EncryptUtil.encrypt(text);
        assertEquals(32, encryptedText.length());
    }

    @Test
    public void shouldEncryptToUpperCase() throws Exception {
        String text = "admin";
        String encryptedText = EncryptUtil.encrypt(text);
        String uppderCase = encryptedText.toUpperCase();

        assertEquals(uppderCase, encryptedText);
    }

    @Test
    public void shouldEncryptToDifferentType() throws Exception {
        String text = "admin";
        String shaText = EncryptUtil.encrypt("SHA", text);
        String md5Text = EncryptUtil.encrypt("MD5", text);
        assertTrue(!shaText.equals(md5Text));
        assertTrue(!text.equals(shaText));
        assertTrue(!text.equals(md5Text));
    }

    @Test
    public void shouldReturnNullForWrongType() throws Exception {
        String text = "admin";
        String encryptedText = EncryptUtil.encrypt("ABC", text);
        assertNull(encryptedText);
    }
}
