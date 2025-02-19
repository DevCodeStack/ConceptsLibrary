package com.devcodestack.core.concepts.threads;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Visibility issue or Race Condition
 * Multiple threads working on a shared object
 * 
 * Using Atomic / Synchronized / Volatile to solve above issue
 * 
 */
public class ThreadConcept02 {
	
	public static void main(String[] args) {
		
		innerAtomicThread();
		innerSynchronizedThread();
		innerVolatileThread();
	}
	
	private static void innerAtomicThread() {
		
		AtomicThread innerThread = new AtomicThread();
		
		Thread lambdaThread1 = new Thread(() ->
        {
            System.out.println("Lambda1 Thread running...");
	        for(int i=0; i<50000; i++) {
	    		
	        	innerThread.increment();
	    	}
	        System.out.println("Lambda1 Count: " + innerThread.getCount());
        });
		
		Thread lambdaThread2 = new Thread(() ->
	    {
	        System.out.println("Lambda2 Thread running...");
	        for(int i=0; i<50000; i++) {
	    		
	        	innerThread.increment();
	    	}
	        System.out.println("Lambda2 Count: " + innerThread.getCount());
	    });
		
		lambdaThread1.start();
		lambdaThread2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted...");
		}
		System.out.println("Final count: " + innerThread.getCount());
	}
	
	private static void innerSynchronizedThread() {
		SynchronizedThread innerThread = new SynchronizedThread();
		
		Thread lambdaThread1 = new Thread(() ->
        {
            System.out.println("Lambda1 Thread running...");
	        for(int i=0; i<50000; i++) {
	    		
	        	innerThread.increment();
	    	}
	        System.out.println("Lambda1 Count: " + innerThread.getCount());
        });
		
		Thread lambdaThread2 = new Thread(() ->
	    {
	        System.out.println("Lambda2 Thread running...");
	        for(int i=0; i<50000; i++) {
	    		
	        	innerThread.increment();
	    	}
	        System.out.println("Lambda2 Count: " + innerThread.getCount());
	    });
		
		lambdaThread1.start();
		lambdaThread2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted...");
		}
		System.out.println("Final count: " + innerThread.getCount());
	}
	
	private static void innerVolatileThread() {
		VolatileThread innerThread = new VolatileThread();
		
		Thread lambdaThread1 = new Thread(() ->
        {
            System.out.println("Lambda1 Thread running...");
            try {
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			System.out.println("Sleep interrupted...");
    		}
            innerThread.setFlag(true);
	        System.out.println("Lambda1 Count: " + innerThread.getFlag());
        });
		
		Thread lambdaThread2 = new Thread(() ->
	    {
	        System.out.println("Lambda2 Thread running...");
	        while(!innerThread.getFlag()) {
	        	
	        }
	        innerThread.setFlag(false);
	        System.out.println("Lambda2 Count: " + innerThread.getFlag());
	    });
		
		lambdaThread1.start();
		lambdaThread2.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted...");
		}
		System.out.println("Flag: " + innerThread.getFlag());
	}
}

class AtomicThread {
	
	private AtomicInteger count = new AtomicInteger(0);
	
	public void increment() {
		count.incrementAndGet();
	}
	
	public int getCount() {
		return count.get();
	}
}

class SynchronizedThread {
	
	private int count;
	
	/* Avoid synchronizing a method to prevent use of lock
	 * instead add a synchonized block
	 *
	 */
	public void increment() {
		synchronized (this) {
			count++;
		}
	}
	
	public int getCount() {
		return count;
	}
}

class VolatileThread {
	
	private int count;
	private volatile boolean flag;
	
	public void increment() {
		count++;
	}
	
	public void setFlag(boolean flg) {
		flag = flg;
	}
	
	public int getCount() {
		return count;
	}
	
	public boolean getFlag() {
		return flag;
	}
}
