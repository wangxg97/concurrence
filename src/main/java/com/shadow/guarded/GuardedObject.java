package com.shadow.guarded;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "enjoy")
public class GuardedObject {

    private Object lock=new Object();

    private Object response;

    /**
     * ������ȡ response ��ֵ�����responseû��ֵ��ȴ�
     * @return
     */
    public  Object getResponse() throws InterruptedException {
        synchronized (lock){
//            System.out.println("���̻߳�ȡresponse ���Ϊnull��wait");
            log.debug("���̻߳�ȡresponse ���Ϊnull��wait");
            while (response==null){
                lock.wait();
            }
        }
        return response;
    }

    /**
     * t1 ��response����ֵ
     * @param response
     */
    public void setResponse(Object response){
        synchronized (lock){
            this.response=response;
            //�������֮�������߳�
            lock.notifyAll();
        }
    }
}
