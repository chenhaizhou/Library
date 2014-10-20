package com.thoughtworks.dolphin.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lzwu on 10/20/14.
 */
public class EncryptUtil {

    private static byte[] encryptForType (String type, byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(type);
        md.update(bytes);
        return md.digest();
    }

    public static String encrypt(String type, String text) {
        try {
            byte[] buffer = text.getBytes("utf-8");
            byte[] encrypted = encryptForType(type, buffer);
            return new String(Hex.encodeHex(encrypted)).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String encrypt(String text) {
        return encrypt("MD5", text);
    }
}
