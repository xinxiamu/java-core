package com.ymu.javase.thread.timer;

import java.util.Date;
import java.util.concurrent.*;

public class ScheduleExecutorDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduleExecutor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("task running: " + Thread.currentThread().getName());
        };

        //延迟1秒后执行
        //ScheduledFuture<?> future = scheduleExecutor.schedule(task, 1, TimeUnit.SECONDS);

        //延迟1秒后执行，并每隔2秒执行一次
        ScheduledFuture<?> future2 = scheduleExecutor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        // 主线程等待一段时间后关闭 scheduler
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 关闭 scheduler
        scheduleExecutor.shutdown();
    }
}

class PriceService {

    private double price; //注意线程安全

    public synchronized void updatePrice(double price) {
        this.price = getPrice() + price;
    }

    public synchronized double getPrice() {
        return price;
    }
}

class PriceUpdateTask implements Runnable {

    private final PriceService priceService;

    PriceUpdateTask(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public void run() {
        System.out.println("PriceUpdate running: " + new Date());
        try {
            Thread.sleep(3000);
            priceService.updatePrice(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("PriceUpdate end: " + new Date());
    }
}

class PriceManager {

    public static void main(String[] args) {
        ScheduledExecutorService scheduleExecutor = Executors.newScheduledThreadPool(1);
        //定时任务
        PriceService priceService = new PriceService();
        scheduleExecutor.scheduleAtFixedRate(new PriceUpdateTask(priceService), 0, 1, TimeUnit.SECONDS); //每隔2秒更新一次价格

        // 主线程等待一段时间后关闭 scheduler
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 关闭 scheduler
        scheduleExecutor.shutdown();

        System.out.println("price: " + priceService.getPrice());
    }
}
