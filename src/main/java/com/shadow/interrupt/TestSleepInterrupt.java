/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.interrupt;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/17 15:41
 * @description ：线程中常用的阻塞方法，如sleep，join和wait 都会响应中断，然后抛出一个中断异常 InterruptedException。但是，注意此时，线程的中断状态会被清除。
 */
public class TestSleepInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "线程中断标志：" + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + "线程中断标志：" + Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        });

        t1.start();
        t1.interrupt();
    }
}
