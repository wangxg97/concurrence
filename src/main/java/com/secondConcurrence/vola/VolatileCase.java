/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.secondConcurrence.vola;

import java.util.concurrent.TimeUnit;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/17 17:32
 * @description ：演示volatile提供的可见性,不能保证原子性
 * 一写多读
 */
public class VolatileCase {
    private volatile static boolean ready;
    private static int number;

    private static class PrintThread implements Runnable {

        @Override
        public void run() {
            System.out.println("PrintThread is running");
            while (!ready) {
            }
            ;
            System.out.println("number" + number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new PrintThread()).start();
        TimeUnit.SECONDS.sleep(1);
        //在主线程中对ready进行了修改，如果不加上volatile ,子线程中看不到变化
        number = 51;
        ready = true;
        TimeUnit.SECONDS.sleep(5);
        System.out.println("main is endding");
    }
}
