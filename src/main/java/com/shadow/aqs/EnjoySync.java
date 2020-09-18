/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/16 9:42
 * @description ：aqs框架--同步器
 */
public class EnjoySync extends AbstractQueuedSynchronizer {
    //主要是尝试加锁，不实现重入
    @Override
    public boolean tryAcquire(int arg) {
        if (getState() == 0) {
            boolean b = compareAndSetState(0, 1);
            if (b) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
        }
        return false;
    }

    //释放锁
    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    //有沒有被人持有
    @Override
    protected boolean isHeldExclusively() {
        if (getState() == 0) {
            return false;
        }
        return true;
    }
}
