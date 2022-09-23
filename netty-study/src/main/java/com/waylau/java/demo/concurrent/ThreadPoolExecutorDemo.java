/**
 * Welcome to https://waylau.com
 */
package com.waylau.java.demo.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor Demo.
 *
 * @since 1.0.0 2019年11月7日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
public class ThreadPoolExecutorDemo {

	public static void main(String[] args) {

		// 队列
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10);

		// 初始化线程池执行器
		ExecutorService pool = new ThreadPoolExecutor(2, 2, 4000, TimeUnit.MILLISECONDS, workQueue);

		for (int i = 0; i < 3; i++) {

			// 提交任务
			pool.submit(new MyTask(i));
		}
	}

}

class MyTask implements Runnable {

	private final int i;

	public MyTask(int i) {
		this.i = i;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + "-" + i + " is working");
		try {
			// 模拟一段耗时工作
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + "-" + i + " done");
	}
}
