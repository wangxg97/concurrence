/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.Lock;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/16 19:45
 * @description ��
 */
public class TestFlag {
    static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("�߳��жϱ�־:"+Thread.currentThread().isInterrupted());
                while (flag){

                }
                System.out.println("��־flagΪ:" + flag);
                System.out.println("�߳��жϱ�־:"+Thread.currentThread().isInterrupted());
                System.out.println("�һ��ڼ���ִ��");
            }
        });

        t.start();
        //Thread.sleep(1000);
        flag = false;
        t.interrupt();
    }
}
