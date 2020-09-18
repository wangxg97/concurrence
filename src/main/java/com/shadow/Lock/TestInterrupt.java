/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.Lock;

import java.util.Random;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/16 19:58
 * @description ��
 */
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new Runnable() {
            private int count = 0;
            @Override
            public void run() {
                try {
                    count = new Random().nextInt(1000);
                    count = count * count;
                    System.out.println("count:"+count);
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName()+"�̵߳�һ���жϱ�־��"+Thread.currentThread().isInterrupted());
                    //���°��߳��ж�״̬����Ϊtrue���Ա��ϲ�����ж�
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName()+"�̵߳ڶ����жϱ�־��"+Thread.currentThread().isInterrupted());
                }
            }
        });

        t.start();

        Thread.sleep(100);
        t.interrupt();
    }
}
