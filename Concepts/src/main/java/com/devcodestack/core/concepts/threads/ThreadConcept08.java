package com.devcodestack.core.concepts.threads;

/*
 * Deadlock scenario: The condition where threads are waiting for each other to finish
 * 
 * Here join is used to resolve the deadlock
 */
public class ThreadConcept08 {

	public static void main(String[] args) {
		
		LoadEngine bullet = new LoadEngine();
		LoadEngine thunderbird = new LoadEngine();
		
		Thread threadBullet = new Thread(() -> {
			System.out.println("bullet turned on...");
			bullet.turnOn(thunderbird);
		}, "Thread-bullet");
		
		Thread threadThunderbird = new Thread(() -> {
			System.out.println("thunderbird turned on...");
			
			try {
				threadBullet.join();
			} catch (InterruptedException e) {
				System.out.println("threadBullet join interrupted...");
			}
			thunderbird.turnOn(bullet);
		}, "Thread-thunderbird");
		
		threadBullet.start();
		threadThunderbird.start();
		
	}

}

class LoadEngine {
	
	public synchronized void turnOn(LoadEngine engine) {
		try {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " trying to start engine...");
			engine.start();
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted...");
		}
	}
	
	public void start() {
		synchronized(this) {
			System.out.println("Engine started by " + Thread.currentThread().getName());
		}
	}
}
