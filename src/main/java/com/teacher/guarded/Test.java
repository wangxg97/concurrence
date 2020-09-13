package com.teacher.guarded;

import com.shadow.guarded.GuardedObject1;
import com.shadow.guarded.Operate;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "enjoy")
public class Test {
    public static void main(String[] args) throws InterruptedException {

        GuardedObject guardedObject = new GuardedObject();


        new Thread(() -> {
            String result = null;
            try {
                result = Operate.dbOprate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("t1 set完毕...");
            guardedObject.setResponse(result);
        },"t1").start();


        log.debug("主线程等待（wait）t1 set");
        //有没有实现超时？
        Object response = guardedObject.getResponse();


        log.debug("response: [{}]",response);
    }



}
