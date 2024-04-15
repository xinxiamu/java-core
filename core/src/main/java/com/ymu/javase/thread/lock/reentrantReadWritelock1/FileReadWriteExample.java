package com.ymu.javase.thread.lock.reentrantReadWritelock1;

public class FileReadWriteExample {
    public static void main(String[] args) {
        FileCache fileCache = new FileCache();

        // 创建多个读线程
        Thread reader1 = new Thread(() -> {
            String content = fileCache.readFile("file1.txt");
            System.out.println("读取文件1内容-1: " + content);
        });
        Thread reader11 = new Thread(() -> {
            String content = fileCache.readFile("file1.txt");
            System.out.println("读取文件1内容-2: " + content);
        });

        Thread reader2 = new Thread(() -> {
            String content = fileCache.readFile("file2.txt");
            System.out.println("读取文件2内容-1: " + content);
        });
        Thread reader22 = new Thread(() -> {
            String content = fileCache.readFile("file2.txt");
            System.out.println("读取文件2内容-2: " + content);
        });

        // 创建多个写线程
        Thread writer1 = new Thread(() -> {
            fileCache.writeFile("file1.txt", "文件1内容-1");
            System.out.println("写入1到file1.txt");
        });

        Thread writer2 = new Thread(() -> {
            fileCache.writeFile("file2.txt", "文件2内容-2222");
            System.out.println("写入2222 file2.txt");
        });

        // 启动线程
        reader1.start();
        reader11.start();
        reader2.start();
        reader22.start();
        writer1.start();
        writer2.start();

        try {
            // 等待所有线程执行完毕
            reader1.join();
            reader2.join();
            writer1.join();
            writer2.join();
            reader11.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 打印最终缓存状态
        System.out.println("=========Final Cache State:");
        System.out.println("File 1 Content: " + fileCache.readFile("file1.txt"));
        System.out.println("File 2 Content: " + fileCache.readFile("file2.txt"));
    }
}

