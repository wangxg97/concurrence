package com.teacher.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j(topic = "enjoy")
public class Lock2 {
    //��д��
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();


    public static void main(String[] args) throws InterruptedException {

        /**
         * t1  �����õ�д��W���� Ȼ��˯����5s
         * ֮��Ż���ѱ���
         */
        Thread t1 = new Thread(() -> {
            w.lock();

            try {
                log.debug("t1 +");
                TimeUnit.SECONDS.sleep(5);
                log.debug("5s ֮��");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                w.unlock();
            }
        }, "t1");

        t1.start();

        TimeUnit.SECONDS.sleep(1);


        /**
         * t1��˯�ߵĹ����� t2�����õ� ��д����
         * t2 һֱ����
         */

        Thread t2 = new Thread(() -> {


            try {
                r.lock();
                log.debug("t2----+-------");
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {

                e.printStackTrace();
            } finally {
                log.debug("t2-----jian-------");
                r.unlock();
            }
        }, "t2");
        t2.start();

        TimeUnit.SECONDS.sleep(1);


        /**
         * t1��˯�ߵĹ����� t3�����õ� ��д����
         * t3 һֱ����
         *
         * ��t1�ͷ���֮�� t3��t2 ��ͬʱ�õ���
         * ��������
         */
        Thread t3 = new Thread(() -> {
            try {
                r.lock();
                log.debug("t3----+-------");
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                log.debug("t3----�ͷ�-------");

                r.unlock();
            }
        }, "t3");
        t3.start();


        /**
         * ��д��
         * t1˯�ߵ�ʱ�� t4Ҳҳ����
         * ˳��Ӧ�� t2 t3  t4
         */

        Thread t4 = new Thread(() -> {
            try {
                w.lock();
                log.debug("t4--------+---");
                TimeUnit.SECONDS.sleep(10);
                log.debug("t4--------����---");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                log.debug("t4--------jian---");
                w.unlock();
            }
        }, "t4");

        t4.start();


        /**
         *
         * t5 �Ƕ���
         * ���᲻���t2 t3 һ��ִ��
         */

        Thread t5 = new Thread(() -> {


            try {
                r.lock();
                log.debug("t5--------+---");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                log.debug("t5--------jian---");
                r.unlock();
            }
        }, "t5");

        t5.start();


    }


}
