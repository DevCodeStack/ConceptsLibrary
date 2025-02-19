package com.devcodestack.core.concepts.threads;

/*
 * Thread creation using lambda function
 * Daemon thread creation
 * 
 */
public class ThreadConcept01 {

    public static void main(String[] args){
    	System.out.println("Main Thread started execution...");
    	
        Thread lambdaThread = new Thread(() ->
        {
        	System.out.println("Lamda Thread started execution...");
        	try {
    			Thread.sleep(10000);
    		} catch (InterruptedException e) {
    			System.out.println("Sleep interrupted...");
    		}
            System.out.println("Lambda Thread running...");
        });

        /* Daemon thread can be better visualized in debug mode */
//        lambdaThread.setDaemon(true);
        lambdaThread.start();
        
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted...");
		}
        System.out.println("Main Thread completed execution...");
    }
}
