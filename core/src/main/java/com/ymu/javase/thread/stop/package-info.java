package com.ymu.javase.thread.stop;

//停止正在运行的线程

//Java中终止正在运行中的线程，主要有以下三种方式：
//1.使用退出标志，使线程正常退出，也就是当run方法完成后线程终止。
//2.使用stop方法强行终止线程，但是不推荐使用该方式，因为stop和suspend以及resume一样，都是过期作废的方法，使用它们可能产生不可预料的后果。
//3.使用interrupt方法中断线程。