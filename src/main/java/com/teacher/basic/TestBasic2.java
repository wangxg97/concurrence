package com.teacher.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "enjoy")
public class TestBasic2 {
    public static void main(String[] args) {
        BasicLock basicLock = new BasicLock();


        new Thread(()->{
            log.debug("start");
            basicLock.x();
        },"t1").start();

        new Thread(()->{
            log.debug("start");
            basicLock.y();
        },"t2").start();

        new Thread(()->{
            log.debug("start");
            basicLock.z();
        },"t3").start();
    }
}
