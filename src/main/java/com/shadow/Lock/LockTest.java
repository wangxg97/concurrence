package com.shadow.Lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/14 10:25
 * @description ：死锁
 */
@Slf4j(topic = "enjoy")
public class LockTest {

    //定义两把锁
    static Object x=new Object();
    static Object y=new Object();

    public static void main(String[] args) {
        new Thread(()->{
            //获取x的锁
            synchronized (x){
                log.debug("locked x");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (y){
                    log.debug("locked x");
                    log.debug("t1---------");
                }
            }
        },"t1").start();


        new Thread(()->{
            synchronized (y){
                log.debug("locked y");
                try {
                    
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (x){
                    log.debug("locked x");
                    log.debug("t2---------");
                }
            }
        },"t2").start();
    }

}