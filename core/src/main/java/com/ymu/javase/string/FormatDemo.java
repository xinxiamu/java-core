package com.ymu.javase.string;

import java.text.MessageFormat;

/**
 * 文本格式化。
 */
public class FormatDemo {

    public static void main(String[] args) {
//        String path = String.format("/v1/oauth2/access_token?appId=%s&secret=%s&grantType=client_credentials", "ab", "dddd");
//        System.out.println(path);

        String path = MessageFormat.format("/v1/oauth2/access_token?appId={0}&secret={1}&grantType=client_credentials", "ab", "dddd");
        System.out.println(path);
    }
}
