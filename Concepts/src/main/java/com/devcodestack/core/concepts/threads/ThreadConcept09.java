package com.devcodestack.core.concepts.threads;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/*
 * CompletableFuture Fundamentals
 * 
 */
public class ThreadConcept09 {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		TaskEngine engine = new TaskEngine();
		
		CompletableFuture<Void> future1 = CompletableFuture.runAsync(engine::turnOn, executorService);
		CompletableFuture<Boolean> future2 = CompletableFuture.supplyAsync(engine::start, executorService);
		CompletableFuture<Boolean> future3 = CompletableFuture.supplyAsync(engine::turnOff, executorService);
		CompletableFuture<Boolean> future4 = CompletableFuture.supplyAsync(engine::cleanUp, executorService);
		
		/*
		 * thenApplyAsync is used to transform the result of a future asynchronously.
		 * whereas thenComposeAsync is used to chain two futures where the second future 
		 * depends on the result of the first.
		 */
		CompletableFuture.supplyAsync(() -> 10, executorService)
				.thenApplyAsync(res -> res * 10)
				.thenComposeAsync(i -> CompletableFuture.supplyAsync(() -> i + 10))
				.thenAccept(result -> System.out.println("Result: " + result));
		
		var futures = Stream.of(future1, future2, future3, future4).toList();
		
//		blockingWay(futures);
//		nonBlockingWay(futures);
		
		executorService.shutdown();
		
		System.out.println("Program completed by " + Thread.currentThread().getName());
		
	}
	
	private static void blockingWay(List<CompletableFuture<? extends Object>> futures) {
		futures.stream().forEach(future -> {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				System.out.println("Exception occured...");
				e.printStackTrace();
			}
		});
	}
	
	private static void nonBlockingWay(List<CompletableFuture<? extends Object>> futures) {
		futures.stream().forEach(future -> {
			try {
				
				/* Check variation in results by commenting out any one the below line */
//				future.thenAccept(result -> System.out.println("Task completed with response: " + result));
//				future.thenAcceptAsync(result -> System.out.println("Task completed with response: " + result));
//				future.exceptionally(ex -> {
//					System.out.println("Exception occurred...");
//					return null;
//				}).thenAccept(result -> System.out.println("Task completed with response: " + result));
				
			} catch (Exception e) {
				System.out.println("Exception occured...");
				e.printStackTrace();
			}
		});
	}

}

class TaskEngine {
	
	public void turnOn() {
		try {
			Thread.sleep(5000);
			System.out.println("Task created by " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted...");
		}
	}
	
	public boolean start() {
		try {
			Thread.sleep(5000);
			System.out.println("Task started by " + Thread.currentThread().getName());
			return true;
		} catch(Exception ex) {
			System.out.println("Sleep interrupted...");
			return false;
		}
	}
	
	public boolean turnOff() {
		try {
			Thread.sleep(5000);
			System.out.println("Task terminated by " + Thread.currentThread().getName());
			return true;
		} catch(Exception ex) {
			System.out.println("Sleep interrupted...");
			return false;
		}
	}
	
	public boolean cleanUp() {
		try {
			Thread.sleep(5000);
//			var div = 10/0;
			System.out.println("Task clean up by " + Thread.currentThread().getName());
			return true;
		} catch(InterruptedException ex) {
			System.out.println("Sleep interrupted...");
			return false;
		}
	}
}
