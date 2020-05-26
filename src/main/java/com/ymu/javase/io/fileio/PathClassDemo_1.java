package com.ymu.javase.io.fileio;

import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

//Path类
public class PathClassDemo_1 {

    @Test
    public void creatPath() {
        Path p1 = Paths.get("/tmp/foo");
        Path p3 = Paths.get(URI.create("file:///Users/joe/FileTest.java"));

        //标准用法
        Path p4 = FileSystems.getDefault().getPath("/users/sally");

        //在用户目录下创建logs目录，然后在logs目录下创建文件foo.log
        //绝对路径
        Path p5 = Paths.get(System.getProperty("user.home"),"logs", "foo.log");
        //>>>p5:C:\Users\xinxiamu\logs\foo.log
        System.out.println(">>>p5:" + p5.toString());
    }

    @Test
    public void retrieveInfoAboutPath() {
        // Microsoft Windows syntax  windows系统下
        Path path = Paths.get("C:\\home\\joe\\foo");

        // Solaris syntax  Linux系统下
//        Path path = Paths.get("/home/joe/foo");

        System.out.format("toString: %s%n", path.toString());
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        System.out.format("subpath(0,2): %s%n", path.subpath(0,2));
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n", path.getRoot());
    }
}
