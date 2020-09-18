package com.teacher.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * ��Ҫ����֤signal������ת��node���̣߳���������
 * �Ƚ��ȳ�  231
 */
@Slf4j(topic = "enjoy")
public class Lock1 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        //��������
        Condition wait = lock.newCondition();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                log.debug("t1 ȥawait");
                wait.await();
                log.debug("t1 �с�");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");

        t1.start();



        //t1 ���ȵõ��� Ȼ��ȥwait���е�������  �ͷ�����



        //t4 ���� ��ȡ���� ˯��5s û���ͷ��� 5s֮�� ��t1 ����
        Thread t4 = new Thread(() -> {


            try {
                lock.lock();
                log.debug("t4--------�@ȡ��---");
                TimeUnit.SECONDS.sleep(5);
                wait.signal();
                log.debug("���t1");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t4");

        t4.start();

        TimeUnit.SECONDS.sleep(1);


        //����ʧ��  �����е�������
        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                log.debug("t2----�õ��i-------");
            } catch (Exception e) {

                e.printStackTrace();
            } finally {
                log.debug("t2----ጷ�-------");
                lock.unlock();
            }
        }, "t2");
        t2.start();

        //˳������  ˳�����
        TimeUnit.SECONDS.sleep(1);


        // ����ʧ��  �����е�������
        Thread t3 = new Thread(() -> {


            try {
                lock.lock();
                log.debug("t3----�õ��i-------");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                log.debug("t3----�ͷ�-------");

                lock.unlock();
            }
        }, "t3");


        t3.start();




    }


}
