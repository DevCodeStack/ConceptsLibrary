package com.devcodestack.core.concepts.threads;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * SingleThreadExecutor allows to create only single thread
 * FixedThreadPool allows user to provide a fixed number of threads to execute
 * CachedThreadPool creates and removes thread as per requirement
 * ScheduledThreadPool creates user provided threads after specific delay from startup
 * 
 */
public class ThreadConcept04 {

	public static void main(String[] args) {
		
		ExecutorService executorService1 = Executors.newSingleThreadExecutor();
		ExecutorService executorService2 = Executors.newFixedThreadPool(3);
		ExecutorService executorService3 = Executors.newCachedThreadPool();
		ScheduledExecutorService executorService4 = Executors.newScheduledThreadPool(3);
		
		singleTask(executorService1);
		fixedTask(executorService2);
		cachedTask(executorService3);
		scheduledTask(executorService4);
	}

	private static void singleTask(ExecutorService executorService) {
		for(int i=1; i<=5; i++) {
			var task = i;
			executorService.submit(() -> {
				System.out.println("Single thread task " + task + " executed by " + Thread.currentThread().getName());
			});
		}
		executorService.shutdown();
	}

	private static void fixedTask(ExecutorService executorService) {
		for(int i=1; i<=5; i++) {
			var task = i;
			executorService.submit(() -> {
				System.out.println("Fixed thread task " + task + " executed by " + Thread.currentThread().getName());
			});
		}
		executorService.shutdown();
	}

	private static void cachedTask(ExecutorService executorService) {
		for(int i=1; i<=5; i++) {
			var task = i;
			executorService.submit(() -> {
				System.out.println("Cached thread task " + task + " executed by " + Thread.currentThread().getName());
			});
		}
		executorService.shutdown();
	}

	private static void scheduledTask(ScheduledExecutorService executorService) {
		for(int i=1; i<=5; i++) {
			var task = i;
			executorService.schedule(() -> {
				System.out.println("Scheduled thread task " + task + " executed by " + Thread.currentThread().getName() + " at " + LocalTime.now());
			}, 3, TimeUnit.SECONDS);
		}
		executorService.shutdown();
	}

}
