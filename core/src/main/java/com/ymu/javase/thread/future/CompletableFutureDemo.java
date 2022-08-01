package com.ymu.javase.thread.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

// https://blog.csdn.net/qq_31865983/article/details/106137777
public class CompletableFutureDemo {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        System.out.println("main:::" + Thread.currentThread());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CompletableFuture f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + ":::" + "abc");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        }, forkJoinPool);

        System.out.println("main start::" + Thread.currentThread());
        System.out.println("f1:::" + f1.get());

        System.out.println("main end::" + Thread.currentThread());
    }
}

