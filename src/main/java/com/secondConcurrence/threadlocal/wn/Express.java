/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.secondConcurrence.threadlocal.wn;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/18 8:56
 * @description ：快递实体类
 */
public class Express {
    public final static String CITY = "ShangHai";
    private int km;//快递运输里程数
    private String site;//快递到达地点

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    //变化快递运输里程数,通知处于wait状态并需要处理里程数的线程进行业务处理
    public synchronized void changeKm() {
        this.km = 101;
        notifyAll();
    }

    //变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理
    public synchronized void changeSite() {
        this.site = "BeiJing";
        notifyAll();
    }

    //线程等待公里的变化
    public synchronized void waitKm() {
        while (this.km < 100) {
            try {
                wait();
                System.out.println("Check km thread[" + Thread.currentThread().getId() + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //业务逻辑
            System.out.println("the km thread[" + Thread.currentThread().getId() + "] is be notified");

        }
    }

    //线程等待目的地的变化
    public synchronized void waitSite() {
        while (this.site.equals(CITY)) {//快递到达目的地
            try {
                wait();
                System.out.println("Check Site thread[" + Thread.currentThread().getId() + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //业务逻辑
        System.out.println("the site thread[" + Thread.currentThread().getId() + "] is be notified");

    }

}
