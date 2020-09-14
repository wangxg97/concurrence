package com.shadow.Lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/14 10:53
 * @description ：使用condition 改写之前的 jack rose 程序
 */
@Slf4j(topic = "enjoy")
public class LockTest6 {
    static ReentrantLock lock = new ReentrantLock();
    static boolean isPrettyGril = false; // 女人
    static boolean isMoney = false;//工资

    public static void main(String[] args) {
        //没有女人的 waitSet
        Condition waitpg = lock.newCondition();
        // 没有钱的waitSet
        Condition waitm = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                log.debug("有没有女人[{}]", isPrettyGril);
                while (!isPrettyGril) {
                    log.debug("没有女人！等女人");
                    waitpg.await();
                }
                log.debug("男女搭配干活不累；啪啪啪写完了代码");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "jack").start();


        new Thread(() -> {
            try {
                lock.lock();
                log.debug("有没有工资[{}]", isMoney);
                while (!isMoney) {
                    log.debug("没有工资！等发工资");
                    waitm.await();
                }
                log.debug("-----卧槽好多钱；啪啪啪写完了代码");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "rose").start();

        new Thread(()->{
            try {
                lock.lock();
                isMoney=true;
                log.debug("钱来哦了");

                isPrettyGril = true;
                log.debug("桥老师");
                waitpg.signal();
                waitm.signal();

            } finally {
                lock.unlock();
            }
        },"boss").start();
    }
}