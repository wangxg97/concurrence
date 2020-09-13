package com.shadow.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//ReentrantLock ���Ա����
@Slf4j(topic = "enjoy")
public class LockTest4 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();

        new Thread(()->{
            try {
                lock.lock();
                log.debug("��ȡ��----");
                TimeUnit.SECONDS.sleep(5);
                log.debug("t2 5s֮�����ִ��");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();

        //��仰��ʵû���κ����壬���ʱ��t2��˯,���߳�Ҳ��˯
        TimeUnit.SECONDS.sleep(1);

        //t1 ����ʧ����Ϊ��t2����
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                log.debug("t1 ��ȡ����--ִ�д���");
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("�������û�л�ȡ��");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        //����t1���Ա���ϣ��ʶ�2s֮����t1�����ڵȴ�t2�ͷ�����
        log.debug("���߳�---2s֮����t1");
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();

    }
}
