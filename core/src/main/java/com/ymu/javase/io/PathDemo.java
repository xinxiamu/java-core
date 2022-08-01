package com.ymu.javase.io;

import cn.hutool.core.io.file.FileNameUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class PathDemo {

    public static void main(String[] args) throws URISyntaxException {
        String path = "http://192.168.0.3:9020/xrlj-20200620/2021-06-29/f18e763f-94e1-4c21-9e00-8ed0948fdf4c.docx?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIOSFODNN7EXAMPLE%2F20210629%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210629T113950Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=eaefe6fee9bcf4ac90eba2acb5fb1239f4dd790508c5faedc0800c03a898b8c3";


        System.out.println(FileNameUtil.getPrefix(path)); // 文件名称前缀
//        System.out.println(FileNameUtil.getSuffix(path));
//        System.out.println(FileNameUtil.getName(path));
        System.out.println(FileNameUtil.mainName(path));

        System.out.println(FileNameUtil.extName(path));
    }
}
