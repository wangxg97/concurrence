package com.shadow.Lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/14 10:37
 * @description ：ReentrantLock 可被打断
 */
@Slf4j(topic = "enjoy")
public class LockTest4 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();

        //t2首先获取锁，然后阻塞5s
        new  Thread(()->{
            try {
                //reetrantlock 必须要和try catch在一块
                lock.lock();
                log.debug("获取锁----");
                TimeUnit.SECONDS.sleep(5);
                log.debug("t2 5s 之后继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();

        //t1加锁失败因为被t2持有
        Thread t1 = new Thread(() -> {
            try {
                //可以被打断
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");

        t1.start();

        TimeUnit.SECONDS.sleep(2);
        log.debug("主线程------2s后打断t1");
        t1.interrupt();



    }
}