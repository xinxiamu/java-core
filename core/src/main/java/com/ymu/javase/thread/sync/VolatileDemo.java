package com.ymu.javase.thread.sync;

//volatile关键字的作用是，使得变量在多个线程之间可见
//volatile 是Java中的一个关键字，用于修饰变量，它的主要作用是确保多线程之间对该变量的可见性和禁止指令重排，而不涉及互斥或同步。

//可见性： volatile 关键字确保变量的值对所有线程都是可见的。在多线程环境中，每个线程可能缓存变量的值，而使用 volatile 修饰的变量将在读写时立即更新到共享内存中，从而保证了可见性。
//禁止指令重排： volatile 还能防止编译器和运行时环境对代码进行指令重排优化，以保证被修饰的变量按照程序的顺序执行。这对于涉及到多线程的代码非常重要，因为指令重排可能导致线程不安全的问题。

//弊端
//不具备互斥性： volatile 仅保证了可见性和禁止指令重排，但不提供互斥保护。如果多个线程同时尝试修改一个 volatile 变量，可能会导致竞争条件，从而产生不一致的结果。因此，volatile 适用于某些特定场景，如标志位的设置和读取，但不能替代锁或其他同步机制，用于更复杂的操作。
//
//不能用于复合操作： 如果你需要执行复合操作，如检查然后更新某个变量，volatile 无法确保这些操作的原子性。这时候需要使用更高级的同步机制，如 synchronized 块或 java.util.concurrent 包中的原子类。
//
//内存开销： 使用 volatile 变量可能导致一些额外的内存开销，因为它需要确保可见性，通常需要通过内存屏障和缓存刷新来实现，这可能对性能有一定的影响。在一些高性能的应用中，这可能会成为一个问题。
//
//适用性有限： volatile 主要适用于一些特定的场景，如标志位、状态标记等，而不适合用于复杂的同步需求。对于更复杂的同步需求，需要使用更灵活的同步机制，如 synchronized 或 java.util.concurrent 包中的锁和工具类。

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {

    private volatile boolean flag = false;

    public void toggleFlag() {
        flag = !flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();

        Thread readerThread  = new Thread(() -> {
            while (!demo.isFlag()) { //当flag变化时候，这里线程马上能感知其变化。如果不添加volatile关键字，无法感知，可能造成死循环
                System.out.println(">>>>>>>flag=" + demo.isFlag());
            }
            System.out.println("Flag is now true.");
        });

        Thread writerThread  = new Thread(() -> {
            demo.toggleFlag();
            System.out.println(">>>>设置flag为true");
        });

        writerThread.start();
        readerThread.start();

    }
}

//volatile非原子的，非线程安全。
//使用原子操作控制

//原子操作是一种不可分割的操作，这意味着它要么完全执行，要么完全不执行。在多线程环境中，原子操作确保多个线程同时对共享资源进行访问时不会导致竞态条件或数据不一致性
class AtomicIntegerExample {
    private AtomicInteger counter = new AtomicInteger(); //原子操作，线程安全。

    public static void main(String[] args) {
        AtomicIntegerExample example = new AtomicIntegerExample();

        Runnable runnable = () -> {

            //同步，执行一个线程，再一个线程
            synchronized (example) {
                for (int i = 0; i < 1000; i++) {
                    example.counter.incrementAndGet();
                    System.out.println(">>>>thread name = " + Thread.currentThread().getName() + ">>>>>>>>>>value = " + example.counter.get());
                }
            }

            //线程访问具有随机性
            /*for (int i = 0; i < 1000; i++) {
                example.counter.incrementAndGet();
                System.out.println(">>>>thread name = " + Thread.currentThread().getName() + ">>>>>>>>>>value = " + example.counter.get());
            }*/

        };

        Thread t = new Thread(runnable);
        t.setName("t1");
        Thread t2 = new Thread(runnable);
        t2.setName("t2");

        t.start();
        t2.start();

        try {
            t.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // atomicInt 的值将会是 2000，而不会有竞态条件
        System.out.println("Final atomicInt value: " + example.counter.get());
        //System.out.println("Final count value: " + example.count);
    }
}

//如何终止死循环代码
class DealEachFor implements Runnable {

//    private boolean flag = true;
    private volatile boolean flag = true; //解决办法，添加关键字volatile

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void println() {
        try {
            while (flag) {
                System.out.println(">>>>>>>>死循环输出");
                Thread.sleep(1000);
            }
            System.out.println(">>>>循环输出成功停止啦");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        println();
    }
}

class TestDealEachFor {
    public static void main(String[] args) throws InterruptedException {
        DealEachFor dealsEachFor = new DealEachFor();
        Thread t = new Thread(dealsEachFor);
        t.start();
        Thread.sleep(5000);
        dealsEachFor.setFlag(false);
    }
}
