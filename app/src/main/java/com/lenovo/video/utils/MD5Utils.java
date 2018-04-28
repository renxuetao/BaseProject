package com.lenovo.video.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 47250 on 2017/12/7.
 */
public class MD5Utils {
    /**
     * MD5小写
     *
     * @param str
     * @return
     */
    public static String md5Upper(String str) {
        return md5Password(str).toUpperCase();
    }

    /**
     * MD5 加密
     */
    public static String md5Password(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }

    public static boolean passwordMatche(String str) {
        boolean s1 = str.matches("^.*[a-zA-Z]+.*$");
        boolean number = str.matches("^.*[0-9]+.*$");
        boolean s2 = str.matches("^.*[/^/$/.//,;:'!@#%&/*/|/?/+/(/)/[/]/{/}]+.*$");
        boolean size = str.matches("^.{8,20}$");
        return (s1 & number & size) | (s1 & s2 & size) | (number & s2 & size);
    }
}
