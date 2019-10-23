package com.ymu.javase.http;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

//http://openjdk.java.net/groups/net/httpclient/recipes.html
public class Http_example_11 {

    /**
     * 测试Get请求，同步。请求内容字符串显示。
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testSyncGet() throws IOException, InterruptedException {
        String uri = "https://www.baidu.com";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        //以字符串形式显示响应内容
        HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(resp.body());
    }

    /**
     * 同步，Get，请求内容输出到文件。
     */
    @Test
    public void testSyncGet1() throws IOException, InterruptedException {
        String uri = "https://www.baidu.com";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<Path> response =
                client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get("body.txt")));

         System.out.println("Response in file:" + response.body());

         /*client.send(HttpRequest.newBuilder().uri(URI.create("https://scf.xcsqjr.com/frame/static/image/login_bg2.png")).build(), HttpResponse.BodyHandlers.ofFile(Paths.get("a.jpg")));*/

         client.send(HttpRequest.newBuilder().uri(URI.create("https://file.xcsqjr.com/00/A7/rBKcJFyZujWAZrHsAAFBrNhZf4k496.pdf")).build(), HttpResponse.BodyHandlers.ofFile(Paths.get("a.pdf")));
    }

    /**
     * Get,异步访问，字符串内容。
     */
    public static CompletableFuture<String> testGetAsync() {
        String uri = "https://www.baidu.com";
        String uri1 = "https://scf.xcsqjr.com/frame/static/image/login_bg2.png";
        String uri2 = "https://file.xcsqjr.com/00/A7/rBKcJFyZujWAZrHsAAFBrNhZf4k496.pdf";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        return  client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);

    }

    public static void main(String[] args) {
        String respStr = testGetAsync().toString();
        System.out.println(respStr);
    }

}
