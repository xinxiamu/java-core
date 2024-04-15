package com.ymu.javase.securet;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    public static void main(String[] args) {
//         generateKey();

        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKVT5uYyEmljAcvzn0oI32AmXENJcfei4rr71vF5FepCuXAlTgsjWnVfOE7jTkkHkDMZXN4QFZtRBi4wdPZJ4xc3xSRARP0Bbamhvhg8KjZCL7b9gzS4xRj5FpXxmPsk43FiRLMx2CJ7RIY5CaqctryN0w7EIcFSFVz/d901YOuxAgMBAAECgYARVz2Ow42+1A2qMMFyaH/Ve/CNdjSn/sO9TRD8fvDXg2wj4SXQcULZsMUdMSZS7qInWe+tsw4xHkyBp7983b8d0iaJpHTe6P4V76nK8KEgH7+x3G41XuBj1pczTr1sdVNEV0znb13id5H0orXhqXBT13rewI6WrIF29pRL9QuDKQJBAP9Zm5aSK1wK7kdzdTZHdm8T3gRkGjRy+ATRTjwFbcbFUPaVk2ywRDkJHz4e6llwMv1qvS+gUJd1kDA2+NqT7H8CQQClv6IsePgCmhzvfgIMXQX0ohTaboKt0SvK0a93lC2NzCH2stIl72p1A5s+KAmBZS1JP8p8/k2uVEshzw+nds/PAkEA+mfZWBMHSj+943up2gzCa7ND6XJw8DY+WhsXaWy5dk9RUj7kT5WbkYMMjFKs5gBNQI5H8Eh6P80eFOoTp+Wf6QJAO7Q+v5RHifBnkeEq7bcoaQllR5VdHy1kf+duND289wHcDhg12JJYH5RP/GDBo8mFOYhv8PxNkQkuExyZ//GtQwJBAONuXQCeT9BuJNib5QzYmlMoFtVIWdG5AGnjf5znC0W2uaFo8uNEz1ioDLAERZ+2y1bTWtKYjX8hptaYNjP613w=";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClU+bmMhJpYwHL859KCN9gJlxDSXH3ouK6+9bxeRXqQrlwJU4LI1p1XzhO405JB5AzGVzeEBWbUQYuMHT2SeMXN8UkQET9AW2pob4YPCo2Qi+2/YM0uMUY+RaV8Zj7JONxYkSzMdgie0SGOQmqnLa8jdMOxCHBUhVc/3fdNWDrsQIDAQAB";

        String src = "HX-0001-0005-2.key1111.byhg666666_2021";

        System.out.println("---------私钥加密，公钥解密");
        String rsaEncodeStr = jdkRSAEncodeByPriKey(privateKey, src);
        System.out.println(rsaEncodeStr);
        System.out.println(jdkRSADecodeByPubKey(publicKey, rsaEncodeStr));

        System.out.println("---------公钥加密，私钥解密");
        String rsaCodeStr = jdkRSAEncodeByPubKey(publicKey, src);
        System.out.println(rsaCodeStr);
        System.out.println(jdkRSADecodeByPriKey(privateKey, rsaCodeStr));

        //签名
        String signStr = jdkRSASign(privateKey, src);
        System.out.println("---签名：" + signStr);
        //验证
        boolean flg = jdkRSAVerify(publicKey, src, signStr);
        System.out.println("----验证结果：" + flg);

    }

    // -------------------- 公钥加密，私钥解密 ------------------//

    /**
     * 私钥加密
     *
     * @param privateKeyBase64
     *            生成的base64私钥
     * @param src
     *            要加密内容
     * @retursn 加密后内容以base64编码返回
     */
    public static String jdkRSAEncodeByPriKey(String privateKeyBase64,
                                              String src) {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                    Base64.decodeBase64(privateKeyBase64));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory
                    .generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(src.getBytes());
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 公钥解密。
     *
     * @param pubKeyBase64
     *            公钥。
     * @param encodeStrBase64
     *            加密后的内容(base64编码)
     * @return
     */
    public static String jdkRSADecodeByPubKey(String pubKeyBase64,
                                              String encodeStrBase64) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
                    Base64.decodeBase64(pubKeyBase64));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] result = cipher
                    .doFinal(Base64.decodeBase64(encodeStrBase64));
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // -------------------- 公钥加密，私钥解密 ------------------//

    /**
     * 公钥加密。
     *
     * @param pubKeyBase64
     * @param src
     * @return
     */
    public static String jdkRSAEncodeByPubKey(String pubKeyBase64, String src) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
                    Base64.decodeBase64(pubKeyBase64));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(src.getBytes());
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 私钥解密。
     *
     * @param priKeyBase64
     * @param encodeStrBase64
     * @return
     */
    public static String jdkRSADecodeByPriKey(String priKeyBase64,
                                              String encodeStrBase64) {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                    Base64.decodeBase64(priKeyBase64));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory
                    .generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] result = cipher
                    .doFinal(Base64.decodeBase64(encodeStrBase64));
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //-------------------- 数字签名：私钥签名，公钥验证 ---------------------------//

    /**
     * 私钥签名。MD5withRSA方式，其他的百度参考。
     * @param priKeyBase64 私钥，base64编码。
     * @param src	要签名的内容
     * @return	base64编码返回签名。
     */
    public static String jdkRSASign(String priKeyBase64,String src) {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(priKeyBase64));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(privateKey);
            signature.update(src.getBytes());
            byte[] result = signature.sign();
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 公钥验证。MD5withRSA方式，其他的百度参考。
     * @param pubKeyBase64	公钥，base64编码。
     * @param src	原签名内容
     * @param signBase64	签名。base64编码。
     * @return
     */
    public static Boolean jdkRSAVerify(String pubKeyBase64,String src,String signBase64) {
        boolean flg = false;
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(pubKeyBase64));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(publicKey);
            signature.update(src.getBytes());
            boolean bool = signature.verify(Base64.decodeBase64(signBase64));
            flg = bool;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flg;
    }

    // --------------------- 初始化密钥
    public static void generateKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator
                    .getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            byte[] pubKey = rsaPublicKey.getEncoded(); // 公钥
            byte[] priKey = rsaPrivateKey.getEncoded();// 私钥
            System.out.println("pubKey:" + Base64.encodeBase64String(pubKey));
            System.out.println("priKey:" + Base64.encodeBase64String(priKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
