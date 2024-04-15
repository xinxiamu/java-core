package com.ymu.javase.io;

import cn.hutool.core.io.file.FileNameUtil;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {

    public static void localPath() {
        String pathStr = "C:\\Users\\HG11\\.hgbio\\hgbio-server-local\\hgbio\\37X1BZ10000003\\202308";
        try {
            Path p = Paths.get(pathStr);
            Path fileName = p.getFileName();
            String lastPart = fileName.toString();

            System.out.println("最后的字符串: " + lastPart);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException {
        /*String path = "http://192.168.0.3:9020/xrlj-20200620/2021-06-29/f18e763f-94e1-4c21-9e00-8ed0948fdf4c.docx?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIOSFODNN7EXAMPLE%2F20210629%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210629T113950Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=eaefe6fee9bcf4ac90eba2acb5fb1239f4dd790508c5faedc0800c03a898b8c3";*/

        String urlPath = "http://192.168.3.100:9000/hgbio/local-sync/20240125104647/3%23202401.byc";
        System.out.println(FileNameUtil.getName(urlPath));


        String[] ss = urlPath.split("/");
        System.out.println(ss[ss.length - 2]);


        String decodedUrl = URLDecoder.decode(urlPath, String.valueOf(StandardCharsets.UTF_8));
        System.out.println(decodedUrl);

        System.out.println(FileNameUtil.getPrefix(decodedUrl)); // 文件名称前缀
        System.out.println(FileNameUtil.getSuffix(decodedUrl));
        System.out.println(FileNameUtil.getName(decodedUrl));
        System.out.println(FileNameUtil.mainName(decodedUrl));
        System.out.println(FileNameUtil.extName(decodedUrl));
    }
}
