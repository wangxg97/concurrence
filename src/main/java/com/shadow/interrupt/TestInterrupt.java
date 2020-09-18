/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.interrupt;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/17 15:21
 * @description ： interrupt 方法只是设置一个中断状态，而不是使当前线程中断运行
 */
public class TestInterrupt {
    static volatile  boolean flag=true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("线程中断标志" + Thread.currentThread().isInterrupted());
            while (flag) {
                System.out.println(flag);
            }
            System.out.println("标志flag为:" + flag);
            System.out.println("线程中断标志" + Thread.currentThread().isInterrupted());
            System.out.println("继续执行");
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        flag=false;
        t1.interrupt();
    }
}
