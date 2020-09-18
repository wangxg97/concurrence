/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.teacher.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/17 14:01
 * @description ����д��֧�����뵫��ֻ֧�ֽ�����֧������
 *
 * ��д��Ϊʲô��������
 *      ��Ҫ�������t1 t2 t3 �߳�ͬʱ�Ȼ�ȡ��������Ϊ�������ǻ���ģ����Կ���ͬʱ��ȡ����������Ȼ����д��������
 *      t2��ȡ����д����t1��ȡ�˶�������ִ��ҵ����룬����������˶�д�����⣬���Զ�д����������
 *
 * ��д��Ϊʲô�ܽ���
 *      ��Ϊֻ��һ���߳̿��Ի�ȡ��������д���⣬дд���⣩����������Ķ���Ҳ֮��һ���߳̿��Ի�ȡ��
 *
 */
@Slf4j(topic = "enjoy")
public class LockTest11 {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        //������ �Ȼ�ȡд���ڻ�ȡ����
//        new Thread(()->{
//            writeLock.lock();
//            try {
//                log.debug("write �Ѿ���ȡ");
//                readLock.lock();
//                log.debug("read �Ѿ���ȡ");
//            } finally {
//                readLock.unlock();
//                writeLock.unlock();
//            }
//
//        },"t1").start();

        //������ �Ȼ�ȡ�����ڻ�ȡд�� ��֧��
        new Thread(()->{
            readLock.lock();
            try {
                log.debug("read �Ѿ���ȡ");
                writeLock.lock();
                log.debug("write �Ѿ���ȡ");
            } finally {
                readLock.unlock();
                writeLock.unlock();
            }

        },"t1").start();
    }
}
