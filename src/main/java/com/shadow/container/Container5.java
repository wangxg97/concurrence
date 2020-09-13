package com.shadow.container;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * һ�������⣺ʵ��һ���������ṩ����������add,size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�����
 * ��������5��ʱ���߳�2������ʾ�������߳�2
 *
    CountDownLatch
 * ʹ��await��countdown�������wait��notify
 * CountDownLatch���漰��������count��ֵΪ��ʱ��ǰ�̼߳�������
 * �൱���Ƿ���ǹ���˶�Ա�̵߳���await�ȴ���������0��ʼ����
 * �����漰ͬ����ֻ���漰�߳�ͨ�ŵ�ʱ����synchronized��wait��notify���Ե�̫����
 */
@Slf4j(topic = "enjoy")
public class Container5 {
    List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Container5 c = new Container5();

        CountDownLatch latch=new CountDownLatch(1);
        new Thread(() -> {
                if (c.size() != 5) {
                    try {
                        //����
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("t2�߳̽���");
        }, "t2").start();


        new Thread(() -> {

                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    log.debug("add" + i);

                    if (c.size() == 5) {
                        latch.countDown();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }, "t1").start();


    }
}
