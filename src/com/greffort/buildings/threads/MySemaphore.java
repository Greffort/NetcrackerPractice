package com.greffort.buildings.threads;

/**
 * @author MySemaphore
 */

public class MySemaphore {
    private int permits;
    private Object last;


    public synchronized void acquire(Object object) {
        try {
            if (last == null) {
                if (object instanceof SequentalCleaner) {
                    wait();
                }
                last = object;
                permits++;
            } else if (permits > 1 || last.getClass() == object.getClass()) {
                wait();
            }
            last = object;
            permits++;
        } catch (Exception e) {
        }
    }

    public synchronized void release() {
        permits--;
        notify();
    }
}
