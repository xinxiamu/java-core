package com.ymu.javase.thread.pause;
//暂停线程
//暂停线程意味着线程还可以恢复继续运行。在Java多线程中，可以使用suspend()方法暂停线程，使用resume()方法恢复线程的执行。

//这两个方法分别用于暂停和恢复线程。然而，它们已经被标记为不推荐使用，因为容易导致死锁和其他线程同步问题。这些方法在某些情况下可能会导致线程暂停后无法再次恢复，从而导致程序不可预测的行为。

//总体来说，建议使用更可靠和稳定的线程控制方法，例如使用线程中断机制来安全地暂停和恢复线程的执行。
// 如果可能，还可以考虑使用更高级的并发工具，如java.util.concurrent包中提供的类，来实现更安全和可控的线程操作。