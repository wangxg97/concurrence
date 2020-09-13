package com.teacher.demo1;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "enjoy")
public class Demo2 {

    private int count = 10;

    public  void test(){
        //synchronized(this)锁定的是当前类的实例,这里锁定的是Demo2类的实例
        synchronized (this){
            count--;
            log.debug(Thread.currentThread().getName() + " count = " + count);
        }
    }

}
