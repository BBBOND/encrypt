package com.kim.encrypt.aes;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

/**
 * Created by jwy8645 on 15-12-2.
 */
public class MyAES {

    private static String str = "jwy8645";

    public static void main(String[] args) {
        jdkAES(str);
        bcAES(str);
    }

    public static void jdkAES(String src) {
        System.out.println("jdkAES");
        try {
            //key的生成
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            keyGenerator.init(256);
//            keyGenerator.init(128);
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            //key的转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("encrypt:" + Base64.encodeBase64String(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println("decrypt:" + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void bcAES(String src) {
        System.out.println("bcAES");
        try {
            Security.addProvider(new BouncyCastleProvider());

            //key的生成
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
//            keyGenerator.init(256);
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            //key的转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("encrypt:" + Base64.encodeBase64String(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println("decrypt:" + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
