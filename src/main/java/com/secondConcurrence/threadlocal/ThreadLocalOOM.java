/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.secondConcurrence.threadlocal;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/17 19:36
 * @description ��threadlocal��ɵ��ڴ�й©��ʾ
 * �ڴ�й© ��ָ�������Ѷ�̬����Ķ��ڴ�����ĳ��ԭ�����δ�ͷŻ��޷��ͷ�
 */
public class ThreadLocalOOM {
    private static final int TASK_LOOP_SIZE = 500;

    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor
            (5, 5, 0, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

    static class  LocalVariable{
        private byte[] a=new byte[1024*1024*5];//5M��С������
    }

    final static ThreadLocal<LocalVariable> localVariable=new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < TASK_LOOP_SIZE; i++) {
            poolExecutor.execute(()->{
                localVariable.set( new LocalVariable());
                System.out.println("use lcoal variable");
                localVariable.remove();
            });
        }
    }
}
