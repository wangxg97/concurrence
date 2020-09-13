package com.shadow.container;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * һ�������⣺ʵ��һ���������ṩ����������add,size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�����
 * ��������5��ʱ���߳�2������ʾ�������߳�2
 * <p>
 * ����wait notify
 *
 * ��Ƚ���һ�����ӣ�����T1������wait�ͷ�����T2�ܹ���ʱ����
 */
@Slf4j(topic = "enjoy")
public class Container4 {
    List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Container4 c = new Container4();
        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                if (c.size() != 5) {
                    try {
                        lock.wait();//�����˻���Ҫȥ������ ��wait���������������뵽�������ŶӾ�����
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("t2�߳̽���");
                lock.notify();
            }
        }, "t2").start();


        new Thread(() -> {

            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    log.debug("add" + i);

                    if (c.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();//Ҫ�ͷ�����t2�̲߳��ܵõ�����ִ��
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
