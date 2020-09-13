package com.shadow.lock;

import lombok.extern.slf4j.Slf4j;

//死锁
@Slf4j(topic = "enjoy")
public class LockTest {

    public static void main(String[] args) {
        Object x = new Object();
         Object y=new Object();


        new Thread(()->{
            synchronized (x){
                log.debug("locked x");
                try {
                    Thread.sleep(1000);//让出cpu的执行权，但是不会释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (y){
                    log.debug("locked y");
                    log.debug("t1.....");
                }
            }
        },"t1").start();

        new Thread(()->{
            synchronized (y){
                log.debug("locked y");
                try {
                    Thread.sleep(1000);//让出cpu的执行权，但是不会释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (x){
                    log.debug("locked x");
                    log.debug("t2.....");
                }
            }
        },"t2").start();


    }
}
