package com.macro.commonutils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author macro
 * @description MD5加密工具类
 * @date 2024/1/2 18:12
 * @github https://github.com/bugstackss
 */
public class MD5 {

    public static String encrypt(final String strSrc) {
        try {
            final char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            byte[] bytes = strSrc.getBytes();
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            final int j = bytes.length;
            final char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                final byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5加密出错！！+" + e);
        }
    }

    public static void main(final String[] args) {
        System.out.println(MD5.encrypt("111111"));
    }
}
