/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.secondConcurrence.threadlocal;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/17 18:01
 * @description ����ʾThreadLocal��ʹ��,û���̶߳��б����ĵĸ������̵߳ĸ���
 */
public class UseThreadLocal {
    private  static  ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 1;//��ʼ��Ϊ1
        }
    };

    //����3���߳�
    public void  startThreadArray(){
        for (int i = 0; i < 3; i++) {
            new Thread(new TestThread(i)).start();
        }
    }

    public static  class  TestThread implements  Runnable{
        int id;

        public TestThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":start");
            Integer s = threadLocal.get();
            s=s+id;
            threadLocal.set(s);
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
        }
    }

    public static void main(String[] args) {
        UseThreadLocal useThreadLocal=new UseThreadLocal();
        useThreadLocal.startThreadArray();
    }

}
