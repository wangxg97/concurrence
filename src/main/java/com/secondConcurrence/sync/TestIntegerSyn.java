/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.secondConcurrence.sync;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/17 17:14
 * @description ：错误的加锁和原因分析
 */
public class TestIntegerSyn {
    public static void main(String[] args) {
        Worker worker=new Worker(1);
        for (int i = 0; i < 5; i++) {
            new Thread(worker).start();
        }
        System.out.println(worker.i);
    }


    private  static  class Worker implements  Runnable{
        private Integer i;

        public Worker(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {
            synchronized (i){
                System.out.println(System.identityHashCode(i));
                i++;//new Integer(i)
                System.out.println(System.identityHashCode(i));

            }
        }
    }
}
