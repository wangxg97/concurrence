/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.Lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/16 20:08
 * @description ：
 */
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ParkThread());
        t.start();
        //Thread.sleep(100); //①
        System.out.println(Thread.currentThread().getName()+"开始唤醒阻塞线程");
        //t.interrupt();
        LockSupport.unpark(t);//只有第一次阻塞会被唤醒，但是第二次依然会继续阻塞
        System.out.println(Thread.currentThread().getName()+"结束唤醒");

    }
}

class ParkThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始阻塞");
        LockSupport.park();
        System.out.println(Thread.currentThread().getName()+"第一次结束阻塞");
        LockSupport.park();
        System.out.println("第二次结束阻塞");
    }
}
