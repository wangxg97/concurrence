package com.shadow.guarded;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuardedObject1 {

    private Object lock=new Object();

    private Object response;

    /**
     * ������ȡ response ��ֵ�����responseû��ֵ��ȴ�
     * @return
     */
    public  Object getResponse(long millis) throws InterruptedException {
        synchronized (lock){
            //��ʼʱ��
            long begin=System.currentTimeMillis();
            //�����˶���ʱ�䣬��ʼ�϶���0
            long timePassed=0;
//            System.out.println("���̻߳�ȡresponse ���Ϊnull��wait");
            log.debug("���̻߳�ȡresponse ���Ϊnull��wait");
            while (response==null){
                //�ȴ��˶���ʱ��
                long waitTime=millis-timePassed;
                if (waitTime<=0){
                    log.debug("��ʱ�ˣ�ֱ�ӽ���while ������");
                    break;
                }
                lock.wait(waitTime);
                //����ұ����˽����ˣ���Ҫȥ�ٴμ������ ���û�н��������ȴ�
                //ȷ���ҵ��˶���ʱ�䣿��millis�Ա����������millis �����ֱ�ӽ���
                timePassed=System.currentTimeMillis()-begin;
                log.debug("������:{}",timePassed);
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
