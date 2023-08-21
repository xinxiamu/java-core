package com.ymu.javase.thread.executor.download;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//并发下载，以最耗时那个为最多事件。并同步返回。
public class DownloadFilesConcurrent {

    public static void main(String[] args) {
        Instant startTime = Instant.now();

        List<String> fileUrls = getListOfUrls(); // 从你的 List<String> 中获取文件 URL 列表

        int numThreads = Runtime.getRuntime().availableProcessors(); // 根据可用的处理器核心数确定线程数量
        ExecutorService executor = Executors.newFixedThreadPool(numThreads); // 创建固定数量的线程池

        CompletionService<Boolean> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < fileUrls.size(); i++) {
            int finalI = i;
            completionService.submit(() -> downloadFile(1000)); // 提交下载任务到线程池
        }
        /*for (String fileUrl : fileUrls) {
            completionService.submit(() -> downloadFile(fileUrl)); // 提交下载任务到线程池
        }*/

        try {
            for (String fileUrl : fileUrls) {
                Future<Boolean> result = completionService.take(); // 等待任意一个下载任务完成
                boolean downloadSuccess = result.get(); // 获取下载结果
                // 处理下载结果，根据需要进行操作
                System.out.println(">>>>>>i: " + fileUrl + " >>>" + downloadSuccess);
            }

            Instant endTime = Instant.now();
            logExecutionTime(startTime, endTime);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // 关闭线程池
        }
    }

    private static void logExecutionTime(Instant startTime, Instant endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long seconds = duration.getSeconds();
        long millis = duration.toMillisPart();

        System.out.printf("Code execution started at: %s%n", startTime);
        System.out.printf("Code execution finished at: %s%n", startTime);
        System.out.printf("Total execution time: %s%n seconds, %s%n milliseconds", seconds, millis);
    }

    private static boolean downloadFile(int t) {
        try {
            /*URL url = new URL(fileUrl);
            String fileName = getFileNameFromUrl(fileUrl);
            try (InputStream inputStream = url.openStream();
                 FileOutputStream outputStream = new FileOutputStream(fileName)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true; // 下载成功*/

            Thread.sleep(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 下载失败
        }
    }

    /*private static String getFileNameFromUrl(String fileUrl) {
        // 从文件 URL 中提取文件名，根据需要自行实现
        // 例如，可以使用正则表达式或字符串处理方法获取文件名
        // 返回文件名字符串
    }*/

    private static List<String> getListOfUrls() {
        // 返回你的 List<String> 文件 URL 列表
        ArrayList<String> files = new ArrayList<>();
        files.add("a");
        files.add("b");
        files.add("d");
        return files;
    }
}
