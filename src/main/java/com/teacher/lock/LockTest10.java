/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.teacher.lock;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/17 13:24
 * @description ����д��
 * <p>
 * ��������
 * ��д����
 * дд����
 * ������֧������
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ���������֮ǰ�����⣬����t1 t3 �Ტ��ִ�У����Ƕ�д���ǻ���ģ�����t2������ t1 t3��ȫִ����֮�����ִ��֮ǰִ�У�����ִ��
 */
@Slf4j(topic = "enjoy")
public class LockTest10 {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        //��
        new Thread(() -> {

            readLock.lock();
            log.debug("read ��ȡ��");
            try {
                for (int i = 0; i < 10; i++) {
                    m1(i);
                }
            } finally {
                readLock.unlock();
            }
        }, "t1").start();

        //��
        new Thread(() -> {

            readLock.lock();
            log.debug("read ��ȡ��");
            try {
                for (int i = 0; i < 20; i++) {
                    m1(i);
                }
            } finally {
                readLock.unlock();
            }
        }, "t3").start();

        //д
        new Thread(() -> {

            writeLock.lock();
            log.debug("write ��ȡ��");
            try {
                for (int i = 0; i < 20; i++) {
                    m1(i);
                }
            } finally {
                writeLock.unlock();
            }
        }, "t2").start();


    }

    public static void m1(int i) {
        log.debug(Thread.currentThread().getName() + "exe" + i);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}