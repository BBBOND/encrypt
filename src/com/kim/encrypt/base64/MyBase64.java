package com.kim.encrypt.base64;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by jwy8645 on 15-12-2.
 */
public class MyBase64 {

    private static String src = "jwy8645";

    public static void main(String[] args) {
        jdkBase64();
        commonsCodesBase64();
        bouncyCastleBase64();
    }

    public static void jdkBase64() {
        System.out.println("jdkBase64");
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(src.getBytes());
            System.out.println("encode:" + encode);

            BASE64Decoder decoder = new BASE64Decoder();
            String decode = new String(decoder.decodeBuffer(encode));
            System.out.println("decode:" + decode);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void commonsCodesBase64() {
        System.out.println("commonsCodesBase64");
        byte[] encode = Base64.encodeBase64(src.getBytes());
        System.out.println("encode:" + new String(encode));

        byte[] decode = Base64.decodeBase64(encode);
        System.out.println("decode:" + new String(decode));

        System.out.println();
    }

    public static void bouncyCastleBase64() {
        System.out.println("bouncyCastleBase64");
        byte[] encode = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        System.out.println("encode:" + new String(encode));

        byte[] decode = org.bouncycastle.util.encoders.Base64.decode(encode);
        System.out.println("decode:" + new String(decode));

        System.out.println();
    }
}
