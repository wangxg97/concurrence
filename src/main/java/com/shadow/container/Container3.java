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
 * ��ȻT2�ܹ���ʱ�յ���Ϣ���ѣ�����wait���ͷ�����notify�����ͷ��������Ի���Ҫ�ȵ�t1�߳����֮��,t2�����ͷ���
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
                        lock.wait();//�����˻���Ҫȥ������ ��wait���������������뵽�������ŶӾ�����
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("t2�߳̽���");
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
