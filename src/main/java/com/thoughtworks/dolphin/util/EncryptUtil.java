package com.thoughtworks.dolphin.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lzwu on 10/20/14.
 */
public class EncryptUtil {

    private static byte[] md5(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            return md.digest();

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String encrypt(String text) {
        try {
            byte[] buffer = text.getBytes("utf-8");
            byte[] encrypted = md5(buffer);
            return new String(Hex.encodeHex(encrypted)).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
