package com.teacher.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "enjoy")
public class TestBasic4 {
    public static void main(String[] args) {
        BasicLock1 basicLock1 = new BasicLock1();


        new Thread(()->{
            log.debug("start");

            basicLock1.x();
        },"t1").start();

        new Thread(()->{
            log.debug("start");

            basicLock1.y();
        },"t2").start();


    }
}
