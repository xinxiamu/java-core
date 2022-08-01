package com.ymu.study.zip4j;

import net.lingala.zip4j.ZipFile;
import org.junit.Test;

import java.io.IOException;

public class Zip4jDemo {

    public static void main(String[] args) {
        String name = "F:\\a\\供应商上传附件\\1、陈云-身份证.pdf";
        try {
            ZipFile zip = new ZipFile("F:\\bb.zip");
            zip.addFile(new String(name.getBytes("ISO-8859-1"), "GBK"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws IOException {
    }
}
