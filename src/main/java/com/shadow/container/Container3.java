package com.shadow.container;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 一道面试题：实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，
 * 当个数到5个时，线程2给出提示并结束线程2
 * <p>
 * 采用wait notify
 *
 * 虽然T2能够及时收到消息唤醒，但是wait会释放锁，notify不会释放锁，所以还是要等到t1线程完成之后,t2才能释放锁
 */
@Slf4j(topic = "enjoy")
public class Container3 {
    List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Container3 c = new Container3();
        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                if (c.size() != 5) {
                    try {
                        lock.wait();//唤醒了还是要去竞争锁 从wait队列中醒来，进入到队列中排队竞争锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("t2线程结束");
            }
        }, "t2").start();


        new Thread(() -> {

            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    log.debug("add" + i);

                    if (c.size() == 5) {
                        lock.notify();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }, "t1").start();


    }
}
