/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.Lock;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/16 19:45
 * @description ：
 */
public class TestFlag {
    static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("线程中断标志:"+Thread.currentThread().isInterrupted());
                while (flag){

                }
                System.out.println("标志flag为:" + flag);
                System.out.println("线程中断标志:"+Thread.currentThread().isInterrupted());
                System.out.println("我还在继续执行");
            }
        });

        t.start();
        //Thread.sleep(1000);
        flag = false;
        t.interrupt();
    }
}
