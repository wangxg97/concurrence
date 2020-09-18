/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.Lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/16 20:08
 * @description ��
 */
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ParkThread());
        t.start();
        //Thread.sleep(100); //��
        System.out.println(Thread.currentThread().getName()+"��ʼ���������߳�");
        //t.interrupt();
        LockSupport.unpark(t);//ֻ�е�һ�������ᱻ���ѣ����ǵڶ�����Ȼ���������
        System.out.println(Thread.currentThread().getName()+"��������");

    }
}

class ParkThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"��ʼ����");
        LockSupport.park();
        System.out.println(Thread.currentThread().getName()+"��һ�ν�������");
        LockSupport.park();
        System.out.println("�ڶ��ν�������");
    }
}
