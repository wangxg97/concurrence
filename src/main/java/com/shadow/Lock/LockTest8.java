package com.shadow.Lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/14 11:41
 * @description ：使用 park unpark 实现两个线程之间顺序调用
 */
@Slf4j(topic = "enjoy")
public class LockTest8 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("a");
        }, "t1");
        t1.start();

        new Thread(()->{
            log.debug("b");
            LockSupport.unpark(t1);
        },"t2").start();
    }
}