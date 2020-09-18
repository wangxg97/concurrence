/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.Lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/16 20:31
 * @description ：
 */
public class LockSyncTest {
    private static Object lock = new Object();
    //保存调用park的线程，以便后续唤醒
    private static Thread parkedThread;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            synchronized (lock){
                System.out.println("unpark前");
                LockSupport.unpark(parkedThread);
                System.out.println("unpark后");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //和t1线程用同一把锁时，park不会释放锁资源，若换成this锁，则会释放锁
                synchronized (this){
                    System.out.println("park前");
                    parkedThread = Thread.currentThread();
                    LockSupport.park();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("park后");
                }
            }
        });

        t2.start();
        Thread.sleep(100);
        t1.start();

    }
}
