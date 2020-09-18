/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.Lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/16 20:31
 * @description ��
 */
public class LockSyncTest {
    private static Object lock = new Object();
    //�������park���̣߳��Ա��������
    private static Thread parkedThread;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            synchronized (lock){
                System.out.println("unparkǰ");
                LockSupport.unpark(parkedThread);
                System.out.println("unpark��");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //��t1�߳���ͬһ����ʱ��park�����ͷ�����Դ��������this��������ͷ���
                synchronized (this){
                    System.out.println("parkǰ");
                    parkedThread = Thread.currentThread();
                    LockSupport.park();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("park��");
                }
            }
        });

        t2.start();
        Thread.sleep(100);
        t1.start();

    }
}
