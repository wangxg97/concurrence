/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.interrupt;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/17 15:21
 * @description �� interrupt ����ֻ������һ���ж�״̬��������ʹ��ǰ�߳��ж�����
 */
public class TestInterrupt {
    static volatile  boolean flag=true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("�߳��жϱ�־" + Thread.currentThread().isInterrupted());
            while (flag) {
                System.out.println(flag);
            }
            System.out.println("��־flagΪ:" + flag);
            System.out.println("�߳��жϱ�־" + Thread.currentThread().isInterrupted());
            System.out.println("����ִ��");
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        flag=false;
        t1.interrupt();
    }
}
