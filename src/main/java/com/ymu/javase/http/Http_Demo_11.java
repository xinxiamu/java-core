package com.ymu.javase.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//java 11
//http://openjdk.java.net/groups/net/httpclient/intro.html
public class Http_Demo_11 {

    public static void main(String[] args) {


    }

    /**
     * GET内容，String显示。
     */
    public void test1()  {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.baidu.com"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

    }

}
