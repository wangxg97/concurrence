package com.shadow.container;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * һ�������⣺ʵ��һ���������ṩ����������add,size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�����
 * ��������5��ʱ���߳�2������ʾ�������߳�2
 *
 * û�м���ͬ��������size����5��ʱ����������̼߳���һ�²�break�����Ǻܾ�׼
 * �˷�cpu���õ�����ѭ��
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
            log.debug("t2�߳̽���");
        },"t2").start();
    }
}
