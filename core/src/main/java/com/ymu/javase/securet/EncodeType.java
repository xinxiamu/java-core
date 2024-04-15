package com.ymu.javase.securet;

public enum EncodeType {
    BASE64("base64"),
    BASE64_URL("base64_url"),
    MD5("md5"),
    SHA1("sha1"),
    SHA256("sha256"),
    SHA512("sha512"),
    AES("aes"),
    DES("des"),
    RSA("rsa"),
    HMAC("hmac"),
    CRC16("crc16"),
    CRC32("crc32"),
    ;

    private final String type;

    public static EncodeType getEncodeType(String type) {
        for (EncodeType encodeType : EncodeType.values()) {
            if (encodeType.type.equals(type)) {
                return encodeType;
            }
        }
        return null;
    }

    EncodeType(String type) {
        this.type = type;
    }
}
