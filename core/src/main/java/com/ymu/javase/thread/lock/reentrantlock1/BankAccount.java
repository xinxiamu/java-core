package com.ymu.javase.thread.lock.reentrantlock1;

import java.util.concurrent.locks.ReentrantLock;

//模拟一个银行账户的存款、取款、查询余额过程
public class BankAccount {

    private double balance; //账户余额

    private final ReentrantLock lock = new ReentrantLock(); //定义互斥锁，可重入

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * 存款
     * @param amount 存款金额
     */
    public void deposit(double amount) {
        lock.lock(); //获取锁
        try {
            this.balance += amount;
            System.out.println("账号存入金额: " + amount + ">>>>余额：" + getBalance());
        } finally {
            lock.unlock(); //确保最终释放锁
        }
    }

    /**
     * 取款
     * @param amount 取款金额
     */
    public void withdraw(double amount) {
        lock.lock(); //获取锁
        try {
            if (this.balance >= amount) {
                this.balance -= amount;
                System.out.println("取款金额: " + amount + ">>>>余额：" + getBalance());
            } else {
                System.out.println("账户余额不足");
            }
        } finally {
            lock.unlock(); //释放锁
        }
    }

    /**
     * 获取账户余额
     * @return 返回账户当前余额
     */
    public double getBalance() {
        return balance;
    }
}
