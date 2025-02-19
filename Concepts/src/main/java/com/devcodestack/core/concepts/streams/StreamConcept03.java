package com.devcodestack.core.concepts.streams;

import java.util.Arrays;
import java.util.List;

/*
 * Parallel stream offers advantage over normal stream but that doesn't holds good in every scenario
 * Based on use case, user has to decide which operation to use
 * 
 */
public class StreamConcept03 {
	
	public static void main(String args[]) {
		
		List<Integer> nums = Arrays.asList(4, 8, 0, 7, 1, 18, 13, 97, 45, 78, 36, 0, 4);
		List<Worker> workers = Arrays.asList(new Worker(), new Worker(), new Worker(), new Worker());
		
		System.out.println("Scenario 1...");
		var start = System.currentTimeMillis();
		
		nums.parallelStream()
			.mapToInt(num -> num * 2)
			.sum();
		
		var stop = System.currentTimeMillis();
		System.out.println("Time taken to execute using parallel: " + (stop - start));
		
		start = System.currentTimeMillis();
		
		nums.stream()
			.mapToInt(num -> num * 2)
			.sum();
		
		stop = System.currentTimeMillis();
		System.out.println("Time taken to execute: " + (stop - start));
		
		System.out.println("Scenario 2...");
		start = System.currentTimeMillis();
		
		workers.parallelStream()
				.forEach(Worker::work);
		
		stop = System.currentTimeMillis();
		System.out.println("Time taken to execute using parallel: " + (stop - start));
		
		start = System.currentTimeMillis();
		
		workers.stream()
				.forEach(Worker::work);
		
		stop = System.currentTimeMillis();
		System.out.println("Time taken to execute: " + (stop - start));
	}
}

class Worker {
	
	void work() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted...");
		}
		System.out.println("Work completed..." + Thread.currentThread().getName());
	}
}
