/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.secondConcurrence.threadlocal;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/18 10:24
 * @description ����wait/notifyʵ�������ߺ������߳���
 * ���ö��̼߳���������wait/notify�����ʵ��һ�����������ߺ�����������ĳ��򣬶�ĳһ������ǹ�ţ����в����������������20���ӵ���
 * �������߳���һ��ѹ���̣߳���������ǹ����ѹ���ӵ���
 * �������߳���һ������̣߳������ϴ�ǹ��������ӵ���
 */
public class WorkTest {
    private static Object o = new Object();
    private static volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            //�������߳���һ��ѹ���̣߳���������ǹ����ѹ���ӵ�
            Thread t1 = new Thread(() -> {
                synchronized (o) {
                    while (i >= 20) {
                        try {
                            System.err.println("��ϻ�������޷�װ��");
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    i++;
                    System.out.println("��ϻδ������ʼװ���������ӵ�:" + i);
                    o.notifyAll();
                }
            });

            //�������߳���һ������̣߳������ϴ�ǹ��������ӵ���
            Thread t2 = new Thread(() -> {
                synchronized (o) {
                    while (i <= 0) {
                        try {
                            System.err.println("��ϻ�ѿգ��޷����");
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("��ϻ�е�����ʼ����������ӵ�:" + i);
                    i--;
                    System.out.println("����껹ʣ�ӵ�:" + i);
                    o.notifyAll();

                }
            });

            t1.start();
            t2.start();


        }


    }

}
