package study.netty.io.demo1.hg;

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
        String hex = Integer.toHexString(wCRCin);
        return hex.toUpperCase();
    }
}
