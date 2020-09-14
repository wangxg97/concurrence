package com.shadow.Lock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/14 11:48
 * @description ：三个线程循环4次打印  a b c
 */
@Slf4j(topic = "enjoy")
public class LockTest9 {
    //默认第一个线程
    int flag = 1;

    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify();
        new Thread(() -> {
            try {
                waitNotify.print("a", 1, 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();


        new Thread(() -> {
            try {
                waitNotify.print("b", 2, 3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();


        new Thread(() -> {
            try {
                waitNotify.print("c", 3, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3").start();
    }

    static class WaitNotify {
        int flag = 1;

        /**
         * 打印结果
         *
         * @param content  结果
         * @param waitFlag 当前线程的顺序
         * @param nextFlag 下一个打印的线程的顺序
         */
        public void print(String content, int waitFlag, int nextFlag) throws InterruptedException {
            for (int i = 0; i < 4; i++) {
                synchronized (this) {
                    //用wait一定用while，记住
                    while (flag != waitFlag) {
                        this.wait();
                    }
                    log.debug(content);
                    flag = nextFlag;
                    this.notifyAll();
                }
            }
        }
    }
}

