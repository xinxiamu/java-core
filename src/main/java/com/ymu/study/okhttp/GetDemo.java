package com.ymu.study.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

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
}
