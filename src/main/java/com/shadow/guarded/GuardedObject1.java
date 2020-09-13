package com.shadow.guarded;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuardedObject1 {

    private Object lock=new Object();

    private Object response;

    /**
     * 加锁获取 response 的值，如果response没有值则等待
     * @return
     */
    public  Object getResponse(long millis) throws InterruptedException {
        synchronized (lock){
            //开始时间
            long begin=System.currentTimeMillis();
            //经历了多少时间，开始肯定是0
            long timePassed=0;
//            System.out.println("主线程获取response 如果为null则wait");
            log.debug("主线程获取response 如果为null则wait");
            while (response==null){
                //等待了多少时间
                long waitTime=millis-timePassed;
                if (waitTime<=0){
                    log.debug("超时了，直接结束while 不等了");
                    break;
                }
                lock.wait(waitTime);
                //如果我被别人叫醒了？我要去再次检查结果， 如果没有结果则继续等待
                //确定我等了多少时间？和millis对比如果大于了millis 则可以直接结束
                timePassed=System.currentTimeMillis()-begin;
                log.debug("经历了:{}",timePassed);
            }
        }
        return response;
    }

    /**
     * t1 给response设置值
     * @param response
     */
    public void setResponse(Object response){
        synchronized (lock){
            this.response=response;
            //设置完成之后唤醒主线程
            lock.notifyAll();
        }
    }
}
