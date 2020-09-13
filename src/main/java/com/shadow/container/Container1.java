package com.shadow.container;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * һ�������⣺ʵ��һ���������ṩ����������add,size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�����
 * ��������5��ʱ���߳�2������ʾ�������߳�2
 * <p>
 * ����list�������߳�֮�䲻��֤�ɼ��ԣ������߳�2ʼ�ս�������
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
                //list �����ǲ��ɼ��ģ�����t2�߳���Զ����������
                if (c.size() == 5) {
                    break;
                }
            }
            log.debug("t2�߳̽���");
        },"t2").start();
    }
}
