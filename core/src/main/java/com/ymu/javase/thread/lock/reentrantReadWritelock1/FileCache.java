package com.ymu.javase.thread.lock.reentrantReadWritelock1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileCache {

    private final Map<String, String> cache = new HashMap<>();

    //private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true); //true表示公平锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public String readFile(String fileName) {
        try {
            lock.readLock().lock();
            //从缓存中读取文件内容
            return cache.getOrDefault(fileName, "File Not Find");
        } finally {
            lock.readLock().unlock();
        }
    }

    public void writeFile(String fileName, String content) {
        try {
            lock.writeLock().lock();
            //将文件内容写入缓存
            cache.put(fileName, content);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
