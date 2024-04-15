package com.ymu.javase.securet;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * 生成密钥辅助类。
 *
 * @author xinxiamu
 */
public final class KeyTools {

    private KeyTools() {
    }

    /**
     * 对称加密算法生成密钥。每次都不一样。
     *
     * @param encodeType 加密类型。{@link}
     * @param keyLenght  秘钥长度
     * @return 返回十六进制密钥
     * @throws Exception
     */
    public static String generateKey(String encodeType, int keyLenght) {
        try {
            //获得密钥
            KeyGenerator keyGenerator = KeyGenerator.getInstance(encodeType);
            if (keyLenght != 0) {
                keyGenerator.init(keyLenght);//长度
            }
            SecretKey security = keyGenerator.generateKey();
            byte[] key = security.getEncoded();
            return Hex.encodeHexString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成盐。
     *
     * @return
     */
    public static String generateSalt(int numBytes) {
        if (numBytes <= 0) {
            throw new IllegalArgumentException("numBytes argument must be a positive integer (1 or larger)");
        } else {
            SecureRandom secureRandom = new SecureRandom();
            byte[] bytes = new byte[numBytes];
            secureRandom.nextBytes(bytes);
            return Hex.encodeHexString(bytes);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateSalt(16));

        System.out.println(generateKey("AES", 128));
    }
}
