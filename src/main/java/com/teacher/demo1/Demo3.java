package com.teacher.demo1;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "enjoy")
public class Demo3 {

    private int count = 10;

    //直接加在方法声明上，相当于是synchronized(this)
    public synchronized void test(){
        count--;
        log.debug(Thread.currentThread().getName() + " count = " + count);
    }

}
