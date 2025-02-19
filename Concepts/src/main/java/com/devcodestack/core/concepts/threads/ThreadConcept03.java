package com.devcodestack.core.concepts.threads;

/*
 * ThreadLocal is used by individual thread to store values 
 * only to be visible by that specific thread only
 * 
 * InheritableThreadLocal is used when a value is 
 * meant to be passed from parent to child thread
 */
public class ThreadConcept03 {
	
	public static void main(String args[]) {
		
		LocalThread innerThread = new LocalThread();
		ThreadLocal<LocalThread> threadLocal = new ThreadLocal<>();
		
		/* Thread 1 */
		Thread lambdaThread1 = new Thread(() ->
        {
        	threadLocal.set(innerThread);
        	var localThread = threadLocal.get();
            System.out.println("Lambda1 Thread running...");
            try {
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			System.out.println("Sleep interrupted...");
    		}
            
            for(int i=0; i<50000; i++) {
            	localThread.increment();
	    	}
            
	        System.out.println("Lambda1 Count: " + localThread.getCount());
            localThread.setFlag(true);
	        System.out.println("Lambda1 flag: " + localThread.getFlag());
	        threadLocal.remove();
        });
		
		/* Thread 2 */
		Thread lambdaThread2 = new Thread(() ->
	    {
        	threadLocal.set(innerThread);
        	var localThread = threadLocal.get();
	        System.out.println("Lambda2 Thread running...");
	        
	        for(int i=0; i<50000; i++) {
	    		
	        	localThread.increment();
	    	}
	        System.out.println("Lambda2 Count: " + localThread.getCount());
	        
	        while(!localThread.getFlag()) {
	        	
	        }
	        localThread.setFlag(false);
	        System.out.println("Lambda2 flag: " + localThread.getFlag());
	        threadLocal.remove();
	    });
		
		InheritableThreadLocal<LocalThread> inheritedThreadLocal = new InheritableThreadLocal <>();
		
		/* Thread 3 */
		Thread lambdaThread3 = new Thread(() ->
	    {
	    	System.out.println("Lambda3 Thread running...");
        	threadLocal.set(innerThread);
        	inheritedThreadLocal.set(innerThread);
        	
	        /* Inner Thread 4 */
	        Thread lambdaThread4 = new Thread(() ->
		    {
		    	System.out.println("Lambda4 Thread running...");
		    	var innerLocalThread = inheritedThreadLocal.get();
		    	var outerLocalThread = threadLocal.get();
		    	
		    	System.out.println("Lambda4 inherited count: " + innerLocalThread.getCount());
		        System.out.println("Lambda4 inherited flag: " + innerLocalThread.getFlag());
		        
		        /* Below threadlocal is null as it is not visible */
//		        System.out.println("Lambda4 count: " + outerLocalThread.getCount());
//		        System.out.println("Lambda4 flag: " + outerLocalThread.getFlag());
		    });
	        
	        lambdaThread4.start();
	        
	        threadLocal.remove();
	        inheritedThreadLocal.remove();
	    });
		
		lambdaThread1.start();
		lambdaThread2.start();
		lambdaThread3.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted...");
		}
		
		System.out.println("Main count: " + innerThread.getCount());
		System.out.println("Main flag: " + innerThread.getFlag());
	}

}

class LocalThread {
	
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
