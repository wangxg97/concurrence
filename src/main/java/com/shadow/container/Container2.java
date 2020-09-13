package com.shadow.container;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 一道面试题：实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，
 * 当个数到5个时，线程2给出提示并结束线程2
 *
 * 没有加入同步，可能size等于5的时候，有另外的线程加了一下才break，不是很精准
 * 浪费cpu，用的是死循环
 */
@Slf4j(topic = "enjoy")
public class Container2 {
    volatile List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Container2 c = new Container2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                log.debug("add" + i);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (c.size() == 5) {
                    break;
                }
            }
            log.debug("t2线程结束");
        },"t2").start();
    }
}
