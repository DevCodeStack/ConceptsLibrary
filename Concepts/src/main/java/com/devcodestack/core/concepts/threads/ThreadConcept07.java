package com.devcodestack.core.concepts.threads;

/*
 * Virtual Thread: Java 19 onwards developers are provided with support of virtual threads 
 * which can be utilized instead of creating own daemon threads.
 * 
 * This also eliminates the case of outofmemory exception
 * 
 */
public class ThreadConcept07 {

	public static void main(String[] args) {


		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				
				try {
					System.out.println("Task in execution by " + Thread.currentThread().getName());
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("Sleep interrupted...");
				}
			}
		};
		
		var count = 1;
		while(count < 50000) {
			
			Thread thread = new Thread(runnable);
			thread.setDaemon(true);
//			Thread thread = Thread.ofVirtual(runnable); //JDK 19
			thread.setName("Thread-" + count);
			thread.start();
			count++;
		}

	}

}
