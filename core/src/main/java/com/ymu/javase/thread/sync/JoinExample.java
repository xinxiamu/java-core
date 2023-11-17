package com.ymu.javase.thread.sync;

/**
 * <p>join() 方法是 Java 多线程编程中的一个重要方法，它用于等待一个线程的结束。主要用途是等待某个线程的执行完成，然后再继续执行当前线程。以下是对 join() 方法的理解、使用和注意事项：</p>
 * <ui>
 *     <li>join() 方法是一个实例方法，通常用于在一个线程中等待另一个线程的完成。</li>
 *     <li>当一个线程调用另一个线程的 join() 方法时，它会阻塞，直到被等待的线程执行完成。</li>
 *     <li>如果被等待的线程已经完成，join() 方法会立即返回。</li>
 *     <li>join() 方法可以用于等待单个线程的完成，也可以用于等待多个线程的完成，多次调用 join()。</li>
 * </ui>
 */
public class JoinExample {
    public static void main(String[] args) {
        Thread workerThread = new Thread(() -> {
            System.out.println(">>>>>工作线程开始工作");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(">>>>>工作线程结束工作");
        });

        //启动工作线程
        workerThread.start();

        try {
            System.out.println(">>>>>这里等待工作线程结束");
            workerThread.join(); //等待工作线程结束，同步效果
            System.out.println(">>>>>>工作线程结束，回到了主线程了");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

//在上述示例中，主线程创建了一个工作线程 workerThread，并调用 workerThread.join() 来等待 workerThread 的完成。主线程会阻塞，直到 workerThread 执行完成。

class JoinExample1 {
    public static void main(String[] args) {
        Thread workerThread1 = new Thread(() -> {
            System.out.println(">>>>>工作线程1开始工作");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(">>>>>工作线1程结束工作");
        });

        Thread workerThread2 = new Thread(() -> {
            System.out.println(">>>>>工作线程2开始工作");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(">>>>>工作线2程结束工作");
        });

        //启动工作线程
        workerThread1.start();
        workerThread2.start();

        try {
            System.out.println(">>>>>这里等待工作线程结束");
            workerThread2.join(); //等待工作线程结束，同步效果
            workerThread1.join(); //等待工作线程结束，同步效果
            System.out.println(">>>>>>工作线程结束，回到了主线程了");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        /**
         * 等待所有线程执行完成回到主线程，还有一种方法，使用{@link com.ymu.javase.thread.communication.CountDownLatchExample}
         */
    }
}

class JoinExample2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(">>>>>我是线程1");
            try {
                Thread.sleep(1000);
                //int a = 1/0;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(">>>>>我是线程1, 我执行完了");
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println(">>>>>我是线程2,我在等待线程1执行完");
                Thread.sleep(5000);
                t1.join();
                System.out.println(">>>>等待线程1执行完了，再继续执行我");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
    }
}

//总结：
//join() 方法会引发中断异常：如果等待线程被中断，join() 方法会抛出 InterruptedException 异常，因此通常需要在调用 join() 时处理中断异常。
//
//避免死锁：小心避免死锁。如果多个线程之间相互等待，可能会导致死锁情况。确保使用 join() 方法的方式不会导致线程之间的相互等待。
//
//超时设置：join() 方法可以带有超时参数，允许在等待的时间达到一定限制后不再等待。这有助于避免永久阻塞。
//
//合理使用：join() 方法通常用于协调线程的执行顺序，确保一个线程在另一个线程之后执行。然而，在某些情况下，它可能会导致性能问题，因此需要根据具体情况使用。
//
//并发性能：过度使用 join() 可能会导致线程的串行执行，从而降低了多线程并发执行的性能。请根据需求谨慎使用。
//
//总之，join() 方法是一种强大的线程协调工具，但需要小心使用，以避免潜在的问题。它通常用于确保线程的执行顺序或等待其他线程的完成。


