package com.concurrent.lock.myLock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author lxq
 * @date 2021年08月13日 10:49
 */
public class NoReentrantLock implements Lock, Serializable {

    private FairSync fairSync = new FairSync();

    private static class FairSync extends AbstractQueuedSynchronizer{

        // 是否为独占锁
        @Override
        protected boolean isHeldExclusively() {
            return true;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if(compareAndSetState(0, 1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            if(getState() == 0){
                throw new IllegalMonitorStateException();
            }

            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public Condition newCondition()   { return new ConditionObject(); }
    }

    @Override
    public void lock() {
        fairSync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        fairSync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return fairSync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return fairSync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        fairSync.release(1);
    }

    @Override
    public Condition newCondition() {
        return fairSync.newCondition();
    }
}
