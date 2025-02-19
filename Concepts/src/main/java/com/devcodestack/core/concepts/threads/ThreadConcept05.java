package com.devcodestack.core.concepts.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * ThreadPoolExecutor is used to create pool of thread
 * corePoolSize is the number of thread that will be present even if they are idle
 * maxPoolSize is the number of thread will come into action including core threads after blocking queue is full
 * blockingQueue will hold the number of task after core thread picks up individual task
 * 
 * Below eg. coreThread is 1, maxPoolSize is 3, blockingQueue capacity is 2
 * Consider 5 tasks are submitted
 * then coreThread will pick first task, and 2 task will be routed to blockingQueue
 * since maxPoolSize is 3, then excluding coreThread, 
 * JVM will create 2 more thread which will pick the remaining 2 task
 * 
 * Exception scenario: if 6 task are submitted, one task will get rejected 
 * as there 3 active threads and only 2 space in blocking queue
 */
public class ThreadConcept05 {

	public static void main(String[] args) {
		
		ExecutorService executorService = new ThreadPoolExecutor(1 , 
												3, 
												10000, 
												TimeUnit.MILLISECONDS, 
												new LinkedBlockingQueue<>(2));
		
		for(int i=1; i<=5; i++) {
			var task = i;
			executorService.submit(() -> {
				System.out.println("Task " + task + " executed by " + Thread.currentThread().getName());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					System.out.println("Sleep interrupted...");
				}
				System.out.println("Task " + task + " completed by " + Thread.currentThread().getName());
			});
		}
		executorService.shutdown();

	}

}
