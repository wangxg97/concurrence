package com.shadow.guarded;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "enjoy")
public class GuardedObject {

    private Object lock=new Object();

    private Object response;

    /**
     * 加锁获取 response 的值，如果response没有值则等待
     * @return
     */
    public  Object getResponse() throws InterruptedException {
        synchronized (lock){
//            System.out.println("主线程获取response 如果为null则wait");
            log.debug("主线程获取response 如果为null则wait");
            while (response==null){
                lock.wait();
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
