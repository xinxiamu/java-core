package com.ymu.javase.securet;

/**
 * CRC16循环冗余校验码
 */
public final class CRCUtils {

    public static String crc16CCITT(byte[] data) {
        int wCRCin = 0;
        for(int i = 0; i < data.length; ++i) {
            wCRCin ^= data[i] & 255;

            for(int j = 0; j < 8; ++j) {
                if ((wCRCin & 1) != 0) {
                    wCRCin >>= 1;
                    wCRCin ^= 33800;
                } else {
                    wCRCin >>= 1;
                }
            }
        }

        System.out.println(wCRCin);
        String hex = Integer.toHexString(wCRCin);
        return hex.toUpperCase();
    }

    public static void main(String[] args) {
        String data = "pro,m4,45.5,22.5,22.5";
//        String data = "pro,m8,1";
        System.out.println(crc16CCITT(data.getBytes()));

    }

}
