package com.shadow.guarded;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "enjoy")
public class Test {
    public static void main(String[] args) throws InterruptedException {
        GuardedObject1 guardedObject=new GuardedObject1();

        new Thread(()->{
            try {
                String result = Operate.dbOprate();
//                System.out.println("t1 set 完毕...");
                log.debug("t1 set 完毕...");
                guardedObject.setResponse(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"t1").start();
        log.debug("主线程等待（wait）t1 set");
//        System.out.println();


        Object response = guardedObject.getResponse(2000);
        log.debug("response [{}]",response);

//        System.out.println("response [{}]"+response);

    }
}
