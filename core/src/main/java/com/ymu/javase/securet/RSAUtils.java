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
        // generateKey();

        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJvVcKgSW9Z5qcIKmIRvGL6AVMpmY4uLReNECCCTU1WI44knyaD1GLST5ovXnBoQxarowuxmBjOIH5OwpI5ma2s8ddK9h21a2zeineMF0JI9PLtPIH3+G53MTm9b/lEbSZntLDsH1rY+JwmGos9NnjhsgekApumR+msu2hXJwtRlAgMBAAECgYAc6f19MerYYLqexliGOhbdQfJ5m4Xc3nqZMIZRhQRxWnTdfgRrWQ68crTUPa05KOOEUqvYMRX1ecxiGuRth5Nmh/sMxl027YZA2n+VUwUA4n9d6beaR8AGTwBssxwpvOJ0NXqbgzK0Xmv9hrYnd2WIRMGj4ieNNwcsFUASk0+6AQJBANee+ccWV/40jcpoarw1aYUJHZ2VBv4pTfkCGKTCDCuP5qmIwV/tt24wpNQYCjBZdUyKdThObgK0AH+xV0TcI88CQQC5BDiYlt3ky2nUKFY2MExEUpjJWC5Bae5/np5jgxKOdUxcQMJ1whlmL73nWUrkZ80DdpjBXjjlE9MuOsTZEC2LAkBuHX0YBDwp0TL8RHkF5w0daLtcJ6hrkjKE0WI+ee73LEUzHWkMEX2U43i57BEAAh+aWDdm6v7ZmI2MqMeVngsXAkB+3GcWMbQ6ckPBZlse0uUon94FiQJGdiVDunZuU2yA4+sVAo6UAiA6UhgLrs1MaeRIMKSzSjEG2h4ef8uD7rTbAkBzx0k1VzQLw0gEFlPMaXWF3fUQTLBM7zW9H2H6Cgyy74HrRF+ZUZcit6ex7atBnavj3Untr65RJUNNWvsZHQ1J";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCb1XCoElvWeanCCpiEbxi+gFTKZmOLi0XjRAggk1NViOOJJ8mg9Ri0k+aL15waEMWq6MLsZgYziB+TsKSOZmtrPHXSvYdtWts3op3jBdCSPTy7TyB9/hudzE5vW/5RG0mZ7Sw7B9a2PicJhqLPTZ44bIHpAKbpkfprLtoVycLUZQIDAQAB";

        String src = "我是一名程序猿";

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
