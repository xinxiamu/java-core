package com.ymu.javase.thread.executor.download;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Test1 {

    public static void main(String[] args) throws MalformedURLException {
        String encodedUrl = "http://192.168.0.106:9000/hgbio/202305/20230511124249/1%23230516/P01.jpg";
        URL url = new URL(encodedUrl);
        String path = url.getPath();

        String decodedUrl = URLDecoder.decode(path, StandardCharsets.UTF_8);

        String[] ps = decodedUrl.split("/");

        String userHome = System.getProperty("user.home");

        // 创建基础目录
        String baseDirectory = userHome + File.separator + "Downloads";
        File currentDirectory = new File(baseDirectory);

        for (int i = 0; i < ps.length; i++) {
            String segment = ps[i];
            if ("".equals(segment) || i == ps.length - 1) {
                continue;
            }
            currentDirectory = new File(currentDirectory, segment);
            if (!currentDirectory.exists()) {
                currentDirectory.mkdir();
            }
        }

        System.out.println("Created Directory: " + currentDirectory.getAbsolutePath());

        File newFile = new File(currentDirectory, ps[ps.length - 1]);
        try {
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + newFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
