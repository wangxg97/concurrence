package com.teacher.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "enjoy")
public class BasicLock {

    public synchronized void x(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("x");
    }


    public synchronized void y(){
        log.debug("y");
    }












    public void z(){
        log.debug("z");
    }
}
