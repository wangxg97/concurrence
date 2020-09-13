package com.shadow.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//ReentrantLock 可以被打断
@Slf4j(topic = "enjoy")
public class LockTest4 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();

        new Thread(()->{
            try {
                lock.lock();
                log.debug("获取锁----");
                TimeUnit.SECONDS.sleep(5);
                log.debug("t2 5s之后继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();

        //这句话其实没有任何意义，这个时候t2再睡,主线程也在睡
        TimeUnit.SECONDS.sleep(1);

        //t1 加锁失败因为被t2持有
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                log.debug("t1 获取了锁--执行代码");
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("被打断了没有获取锁");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        //由于t1可以被打断，故而2s之后打断t1，不在等待t2释放锁了
        log.debug("主线程---2s之后打断t1");
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();

    }
}
