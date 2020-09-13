package com.shadow.container;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 一道面试题：实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，
 * 当个数到5个时，线程2给出提示并结束线程2
 * <p>
 * 这里list在两个线程之间不保证可见性，所以线程2始终结束不了
 */
@Slf4j(topic = "enjoy")
public class Container1 {
    List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Container1 c = new Container1();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                log.debug("add" + i);

            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                //list 集合是不可见的，所以t2线程永远都结束不了
                if (c.size() == 5) {
                    break;
                }
            }
            log.debug("t2线程结束");
        },"t2").start();
    }
}
