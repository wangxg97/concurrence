/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.secondConcurrence.threadlocal;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/18 10:24
 * @description ：《wait/notify实现生产者和消费者程序》
 * 采用多线程技术，例如wait/notify，设计实现一个符合生产者和消费者问题的程序，对某一个对象（枪膛）进行操作，其最大容量是20颗子弹，
 * 生产者线程是一个压入线程，它不断向枪膛中压入子弹，
 * 消费者线程是一个射出线程，它不断从枪膛中射出子弹。
 */
public class WorkTest {
    private static Object o = new Object();
    private static volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            //生产者线程是一个压入线程，它不断向枪膛中压入子弹
            Thread t1 = new Thread(() -> {
                synchronized (o) {
                    while (i >= 20) {
                        try {
                            System.err.println("弹匣已满，无法装弹");
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    i++;
                    System.out.println("弹匣未满，开始装弹，现有子弹:" + i);
                    o.notifyAll();
                }
            });

            //消费者线程是一个射出线程，它不断从枪膛中射出子弹。
            Thread t2 = new Thread(() -> {
                synchronized (o) {
                    while (i <= 0) {
                        try {
                            System.err.println("弹匣已空，无法射击");
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("弹匣有弹，开始射击，现有子弹:" + i);
                    i--;
                    System.out.println("射击完还剩子弹:" + i);
                    o.notifyAll();

                }
            });

            t1.start();
            t2.start();


        }


    }

}
