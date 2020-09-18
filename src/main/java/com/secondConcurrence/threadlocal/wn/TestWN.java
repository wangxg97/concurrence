/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.secondConcurrence.threadlocal.wn;

import java.util.concurrent.TimeUnit;

/**
 * @author £∫wangxg
 * @version £∫
 * @program £∫concurrence
 * @date £∫Created in 2020/9/18 9:13
 * @description £∫≤‚ ‘wait notify
 */
public class TestWN {

    private static Express express=new Express(0,Express.CITY);

    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }

    private static class  CheckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }
        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }
        TimeUnit.SECONDS.sleep(1);
        express.changeKm();
    }
}
