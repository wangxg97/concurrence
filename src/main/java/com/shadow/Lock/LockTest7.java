package com.shadow.Lock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/14 11:23
 * @description ：让两个线程顺序执行 先b在a
 */
@Slf4j(topic = "enjoy")
public class LockTest7 {
    static final Object lock = new Object();
    static boolean isFlag = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {

                while (!isFlag) {
                    try {
                        lock.wait();
                        log.debug("a");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "a").start();


        new Thread(()->{
            synchronized (lock){
                    isFlag=true;
                    log.debug("b");
                    lock.notify();
            }
        },"b").start();
    }
}