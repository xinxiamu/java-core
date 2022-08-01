package com.ymu.study.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GetDemo {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        String url = "";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String respStr = response.body().string();
            System.out.println(respStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //下载文件
    @Test
    public void getFile() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
//                .url("http://8.129.38.64:4369/xrlj-20210113/2021-02-21/5d88537a-11c9-4b45-a08b-a8f3135ecf53.pdf")
                .url("https://file3.hlt-factoring.com/data/hlt/2021-01-15/3bea7a88f73c48e5a17daaeaa113c678.pdf")
                .get()
                .build();
        try (Response response = client.newCall(request).execute();InputStream in = response.body().byteStream();FileOutputStream out = new FileOutputStream("F:\\cc.pdf");) {

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
