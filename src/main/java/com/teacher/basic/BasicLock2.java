package com.teacher.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "enjoy")
public class BasicLock2 {

    public synchronized static void x(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("x");
    }


    public synchronized static void y(){
        log.debug("y");
    }

    public void z(){
        log.debug("z");
    }
}
