package com.devcodestack.core.concepts.threads;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * Producer Consumer problem
 * 
 * Issue occurs when rate of production is not equal to rate of consumption
 * 
 * The same can be solved using synchronization or by using blocking queue
 * Blocking Queue is much preferable as it internally handles concurrency
 * 
 */
public class ThreadConcept06 {

	public static void main(String[] args) {
		
		legacyWay();
		blockingWay();

	}
	
	private static void legacyWay() {
		
		final Queue<Integer> buffer = new PriorityQueue<>(5);
		Factory factory = new Factory(buffer, 5);
		
		Thread producerThread = new Thread(() -> {
			
			try {
				for(int item=1; item<=10 ; item++) {
					factory.produce(item);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				System.out.println("Sleep interrupted...");
			}
			
		});
		
		Thread consumerThread = new Thread(() -> {
			
			try {
				for(int item=1; item<=10 ; item++) {
					factory.consume();
					Thread.sleep(2000);
				}
			} catch (InterruptedException e) {
				System.out.println("Sleep interrupted...");
			}
			
		});
		
		producerThread.start();
		consumerThread.start();
	}
	
	private static void blockingWay() {
		
		final BlockingQueue<Integer> buffer = new LinkedBlockingQueue<>(5);
		Factory factory = new Factory(buffer, 5);
		
		Thread producerThread = new Thread(() -> {
			
			try {
				for(int item=1; item<=10 ; item++) {
					factory.produceBQ(item);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				System.out.println("Sleep interrupted...");
			}
			
		});
		
		Thread consumerThread = new Thread(() -> {
			
			try {
				for(int item=1; item<=10 ; item++) {
					factory.consumeBQ();
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				System.out.println("Sleep interrupted...");
			}
			
		});
		
		producerThread.start();
		consumerThread.start();
	}

}

class Factory {
	private Queue<Integer> buffer;
	private BlockingQueue<Integer> bBuffer;
	private Integer max;
	
	Factory(BlockingQueue<Integer> buf, Integer max){
		this.bBuffer = buf;
		this.max = max;
	}
	
	Factory(Queue<Integer> buf, Integer max){
		this.buffer = buf;
		this.max = max;
	}
	
	public void produce(Integer item) {
		
		synchronized(buffer) {
			System.out.println("Item " + item + " created...");
			while(buffer.size() == max) {
				try {
					buffer.wait();
				} catch (InterruptedException e) {
					System.out.println("Wait interrupted...");
				}
			}
			buffer.add(item);
			System.out.println("Item " + item + " moved...");
			buffer.notifyAll();
		}
	}
	
	public void consume() {
		
		synchronized(buffer) {
			while(buffer.isEmpty()) {
				try {
					buffer.wait();
				} catch (InterruptedException e) {
					System.out.println("Wait interrupted...");
				}
			}
			
			var item = buffer.poll();
			System.out.println("Item " + item + " extracted...");
			buffer.notifyAll();
		}
	}
	
	public void produceBQ(Integer item) {
		
		System.out.println("BQ Item " + item + " created...");
		try {
			bBuffer.put(item);
			System.out.println("BQ Item " + item + " moved...");
		} catch (InterruptedException e) {
			System.out.println("Interrupted...");
		}
	}
	
	public void consumeBQ() {
		
		Integer item;
		try {
			item = bBuffer.take();
			System.out.println("BQ Item " + item + " extracted...");
		} catch (InterruptedException e) {
			System.out.println("Interrupted...");
		}
	}
	
}
