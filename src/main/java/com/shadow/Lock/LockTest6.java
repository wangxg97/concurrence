package com.shadow.Lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ��wangxg
 * @version ��
 * @program ��concurrence
 * @date ��Created in 2020/9/14 10:53
 * @description ��ʹ��condition ��д֮ǰ�� jack rose ����
 */
@Slf4j(topic = "enjoy")
public class LockTest6 {
    static ReentrantLock lock = new ReentrantLock();
    static boolean isPrettyGril = false; // Ů��
    static boolean isMoney = false;//����

    public static void main(String[] args) {
        //û��Ů�˵� waitSet
        Condition waitpg = lock.newCondition();
        // û��Ǯ��waitSet
        Condition waitm = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                log.debug("��û��Ů��[{}]", isPrettyGril);
                while (!isPrettyGril) {
                    log.debug("û��Ů�ˣ���Ů��");
                    waitpg.await();
                }
                log.debug("��Ů����ɻ�ۣ�žžžд���˴���");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "jack").start();


        new Thread(() -> {
            try {
                lock.lock();
                log.debug("��û�й���[{}]", isMoney);
                while (!isMoney) {
                    log.debug("û�й��ʣ��ȷ�����");
                    waitm.await();
                }
                log.debug("-----�Բۺö�Ǯ��žžžд���˴���");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "rose").start();

        new Thread(()->{
            try {
                lock.lock();
                isMoney=true;
                log.debug("Ǯ��Ŷ��");

                isPrettyGril = true;
                log.debug("����ʦ");
                waitpg.signal();
                waitm.signal();

            } finally {
                lock.unlock();
            }
        },"boss").start();
    }
}