package com.kim.encrypt.elgamal;


import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;

/**
 * 只能公钥加密
 * 只能BC实现
 * <p>
 * 基于离散对数问题
 * Created by 伟阳 on 2015/12/4.
 */
public class MyElGamal {
    private static String str = "jwy8645";

    public static void main(String[] args) {
        bcElGamal(str);
    }

    public static void bcElGamal(String src) {
        try {
            Security.addProvider(new BouncyCastleProvider());

            //密钥初始化
            AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("ElGamal");
            algorithmParameterGenerator.init(256);
            AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
            DHParameterSpec dhParameterSpec = (DHParameterSpec) algorithmParameters.getParameterSpec(DHParameterSpec.class);
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ElGamal");
            keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey elGamalPublicKey = keyPair.getPublic();
            PrivateKey elGamalPrivateKey = keyPair.getPrivate();
            System.out.println("Public Key:" + Base64.encodeBase64String(elGamalPublicKey.getEncoded()));
            System.out.println("Private Key:" + Base64.encodeBase64String(elGamalPrivateKey.getEncoded()));

            /**
             * 对于：“Illegal key size or default parameters”异常，
             * 是因为美国的出口限制，Sun通过权限文件（local_policy.jar、US_export_policy.jar）做了相应限制。
             * 因此存在一些问题.
             *
             * 使用java6 或 java7 即可
             */

            //公钥加密、私钥解密
            Cipher cipher = Cipher.getInstance("ElGamal");
            cipher.init(Cipher.ENCRYPT_MODE, elGamalPublicKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("公钥加密、私钥解密------加密:" + Base64.encodeBase64String(result));

            cipher = Cipher.getInstance("ElGamal");
            cipher.init(Cipher.DECRYPT_MODE, elGamalPrivateKey);
            result = cipher.doFinal(result);
            System.out.println("公钥加密、私钥解密------解密:" + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}