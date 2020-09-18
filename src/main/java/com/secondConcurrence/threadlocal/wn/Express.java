/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.secondConcurrence.threadlocal.wn;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/18 8:56
 * @description �����ʵ����
 */
public class Express {
    public final static String CITY = "ShangHai";
    private int km;//������������
    private String site;//��ݵ���ص�

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    //�仯������������,֪ͨ����wait״̬����Ҫ������������߳̽���ҵ����
    public synchronized void changeKm() {
        this.km = 101;
        notifyAll();
    }

    //�仯�ص㣬Ȼ��֪ͨ����wait״̬����Ҫ����ص���߳̽���ҵ����
    public synchronized void changeSite() {
        this.site = "BeiJing";
        notifyAll();
    }

    //�̵߳ȴ�����ı仯
    public synchronized void waitKm() {
        while (this.km < 100) {
            try {
                wait();
                System.out.println("Check km thread[" + Thread.currentThread().getId() + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //ҵ���߼�
            System.out.println("the km thread[" + Thread.currentThread().getId() + "] is be notified");

        }
    }

    //�̵߳ȴ�Ŀ�ĵصı仯
    public synchronized void waitSite() {
        while (this.site.equals(CITY)) {//��ݵ���Ŀ�ĵ�
            try {
                wait();
                System.out.println("Check Site thread[" + Thread.currentThread().getId() + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //ҵ���߼�
        System.out.println("the site thread[" + Thread.currentThread().getId() + "] is be notified");

    }

}
