package com.ymu.javase.securet;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;

/**
 * 对称加密算法-AES,DES的替代者。
 *
 * @author xinxiamu
 */
public class AESUtils {

    public static void main(String[] args) throws Exception {
        String keyStr = "77f8b39fc553eb760e6eaabbcd177db5";
        /*String encodeStr = jdkAESEncode(keyStr, "我是中国人");
        System.out.println(encodeStr);
        System.out.println(jdkAESDecode(keyStr, encodeStr));*/

        Instant startTime = Instant.now();
        //加密文件
        encryptFile("C:\\Users\\HG11\\.hgbio\\20231213231840_5e449ec6447e815a2a2fe8f61523ef2b.zip", "C:\\Users\\HG11\\Desktop\\20231213231840_5e449ec6447e815a2a2fe8f61523ef2b-java.byc", keyStr);
        //decryptFile("K:\\20231213231840_5e449ec6447e815a2a2fe8f61523ef2b.byc", "K:\\202312132318404444444444444.zip", keyStr);
        Instant endTime = Instant.now();
        logExecutionTime(startTime, endTime);
    }

    private static void logExecutionTime(Instant startTime, Instant endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long seconds = duration.getSeconds();
        long millis = duration.toMillis();

        System.out.printf("Code execution started at: %s%n", startTime);
        System.out.printf("Code execution finished at: %s%n", endTime);
        System.out.printf("Total execution time: %s%n seconds, %s%n milliseconds", seconds, millis);
    }

    /**
     * 生成AES密钥。
     *
     * @return 返回AES密钥
     */
    public static Key generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // 使用128位密钥
        return keyGenerator.generateKey();
    }

    /**
     * AES加密。
     *
     * @param hexKeyStr 十六进制密钥。
     * @param str       要加密的字符串。
     * @return 返回加密后的十六进制字符串
     */
    public static String jdkAESEncode(String hexKeyStr, String str) {
        try {
            // key转换
            Key key = new SecretKeySpec(Hex.decodeHex(hexKeyStr.toCharArray()),
                    "AES");

            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(str.getBytes());
            return Hex.encodeHexString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * AES解密。
     *
     * @param hexKeyStr 十六进制密钥。
     * @param ecodeStr  要解密的十六进制字符串。
     * @return
     */
    public static String jdkAESDecode(String hexKeyStr, String encodeStr) {
        try {
            // key转换
            Key key = new SecretKeySpec(Hex.decodeHex(hexKeyStr.toCharArray()),
                    "AES");

            //解密。
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Hex.decodeHex(encodeStr.toCharArray())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void encryptFile(String inputFile, String outputFile, String hexKeyStr) {
        try (InputStream inputStream = new FileInputStream(inputFile);
             OutputStream outputStream = new FileOutputStream(outputFile)) {

            // 读取文件内容到字节数组
            byte[] inputBytes = new byte[(int) new File(inputFile).length()];
            inputStream.read(inputBytes);

            // 使用 AES 加密
            // key转换
            Key key = new SecretKeySpec(Hex.decodeHex(hexKeyStr.toCharArray()),
                    "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(inputBytes);

            // 对加密后的字节数组进行 Base64 编码
            byte[] encodedBytes = Base64.encodeBase64(encryptedBytes);

            // 写入输出文件
            outputStream.write(encodedBytes);

            System.out.println("文件加密成功。");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * 解密文件
	 *
	 * @param inputFile  待解密的文件路径
	 * @param outputFile 解密后的文件路径
	 * @param hexKeyStr        解密密钥
	 */
    public static void decryptFile(String inputFile, String outputFile, String hexKeyStr) {
        try (InputStream inputStream = new FileInputStream(inputFile);
             OutputStream outputStream = new FileOutputStream(outputFile)) {

            // 读取文件内容到字节数组
            byte[] inputBytes = new byte[(int) new File(inputFile).length()];
            inputStream.read(inputBytes);

            // 对 Base64 编码的字节数组进行解码
            byte[] decodedBytes = Base64.decodeBase64(inputBytes);

            // 使用 AES 解密
            // key转换
            Key key = new SecretKeySpec(Hex.decodeHex(hexKeyStr.toCharArray()),
                    "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            // 写入输出文件
            outputStream.write(decryptedBytes);

            System.out.println("文件解密成功。");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
