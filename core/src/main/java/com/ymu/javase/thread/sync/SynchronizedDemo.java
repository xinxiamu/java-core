package com.ymu.javase.thread.sync;


//synchronized 的关键是锁对象。对于实例方法，锁对象是当前对象实例（this）；对于静态方法，锁对象是类对象；对于同步代码块，你可以选择任何对象作为锁。
//Java 中每个对象都有一个内置的锁（也称为监视器锁或对象锁）。synchronized 使用对象锁来保护临界区，只有拥有锁的线程才能进入同步块。
//一个线程持有对象锁后，其它线程访问对象的同步方法或代码块，需要等待。但是可以访问非同步代码
class A {

    //同步代码块
    /*synchronized(lockObject) {
        // 这个代码块是同步的，使用lockObject作为锁
    }*/

    //同步方法
    //任何时候，对象只能一个线程访问
    //持有的是对象锁
    //synchronized关键字不可被继承，重写。意思，子类中重写的方法，不会自动同步的
    synchronized public void a() {
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>> 开始, thread name=" + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>> 结束, thread name=" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //静态同步方法。对整个类加锁
    //在静态方法前使用 synchronized 关键字，将锁定类的类对象，以确保同一时刻只有一个线程可以访问该静态方法。其他线程在尝试访问同步方法时将被阻塞。
    public static synchronized void aa() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>静态同步方法=" + Thread.currentThread().getName());
    }

    //获取同步值，非同步，可脏读
    public void c() {

    }

    //锁可重入
    //自己可以再次获取自己内部锁
    synchronized public void cc() {
        a();
    }

    public void bb() {
        try {
            System.out.println(">>>>>非同步代码块");
            Thread.sleep(1000);

            //同步代码块
            //同步代码块也是锁定对象的
            //锁定对象后，其它线程无法访问对象所有的同步代码块的
            synchronized (this) {
                System.out.println(">>>>>同步代码块, thread name=" + Thread.currentThread().getName());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

class B {
    public static void main(String[] args) {

        A a = new A();

        Thread aThread = new AThread(a);
        aThread.setName("A Thread");
        Thread bThread = new BThread(a);
        bThread.setName("B Thread");

        aThread.start();
        bThread.start();

        //线程a抛出异常的话，会自动释放锁，线程b获取。
    }
}

class AThread extends Thread {

    private final A a;

    public AThread(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        a.a();
    }
}

class BThread extends Thread {

    private final A a;

    public BThread(A a) {
        this.a = a;
    }

    @Override
    public void run() {
       a.a();
    }
}

/**
 * 作为同步块使用。
 * 在这个示例中，lock 对象用作同步块的锁对象，以确保 increment() 和 getCounter() 方法的线程安全。这可以防止两个线程同时访问 sharedCounter，从而保证了数据一致性。
 */
class SynchronizedBlockExample {
    private int sharedCounter = 0;
    private final Object lock = new Object(); // 锁对象

    public void increment() {
        synchronized (lock) { // 同步块，锁定lock对象
            sharedCounter++;
        }
    }

    public int getCounter() {
        synchronized (lock) { // 同步块，锁定lock对象
            return sharedCounter;
        }
    }

    public static void main(String[] args) {
        SynchronizedBlockExample example = new SynchronizedBlockExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final counter value: " + example.getCounter());
    }
}

