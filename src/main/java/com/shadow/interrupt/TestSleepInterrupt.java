/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.interrupt;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/17 15:41
 * @description ���߳��г��õ�������������sleep��join��wait ������Ӧ�жϣ�Ȼ���׳�һ���ж��쳣 InterruptedException�����ǣ�ע���ʱ���̵߳��ж�״̬�ᱻ�����
 */
public class TestSleepInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "�߳��жϱ�־��" + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + "�߳��жϱ�־��" + Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        });

        t1.start();
        t1.interrupt();
    }
}
