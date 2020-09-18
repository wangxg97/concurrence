/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.secondConcurrence.vola;

import java.util.concurrent.TimeUnit;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/17 17:32
 * @description ����ʾvolatile�ṩ�Ŀɼ���,���ܱ�֤ԭ����
 * һд���
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
        //�����߳��ж�ready�������޸ģ����������volatile ,���߳��п������仯
        number = 51;
        ready = true;
        TimeUnit.SECONDS.sleep(5);
        System.out.println("main is endding");
    }
}
