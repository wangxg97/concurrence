package com.shadow.Lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/14 10:37
 * @description ��ReentrantLock �ɱ����
 */
@Slf4j(topic = "enjoy")
public class LockTest4 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();

        //t2���Ȼ�ȡ����Ȼ������5s
        new  Thread(()->{
            try {
                //reetrantlock ����Ҫ��try catch��һ��
                lock.lock();
                log.debug("��ȡ��----");
                TimeUnit.SECONDS.sleep(5);
                log.debug("t2 5s ֮�����ִ��");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();

        //t1����ʧ����Ϊ��t2����
        Thread t1 = new Thread(() -> {
            try {
                //���Ա����
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");

        t1.start();

        TimeUnit.SECONDS.sleep(2);
        log.debug("���߳�------2s����t1");
        t1.interrupt();



    }
}