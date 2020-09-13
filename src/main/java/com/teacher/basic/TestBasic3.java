package com.teacher.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * 永远y之前
 */
@Slf4j(topic = "enjoy")
public class TestBasic3 {
    public static void main(String[] args) {
        BasicLock basicLock = new BasicLock();
        BasicLock basicLock1 = new BasicLock();

        new Thread(()->{
            log.debug("start");
            basicLock.x();
        },"t1").start();

        new Thread(()->{
            log.debug("start");
            basicLock1.y();
        },"t2").start();


    }
}
