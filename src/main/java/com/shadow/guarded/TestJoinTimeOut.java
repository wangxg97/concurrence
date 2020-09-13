package com.shadow.guarded;

import javax.sound.midi.Soundbank;
import java.util.concurrent.TimeUnit;

public class TestJoinTimeOut {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        t1.join(2000);
        System.out.println("man end");
    }
}

