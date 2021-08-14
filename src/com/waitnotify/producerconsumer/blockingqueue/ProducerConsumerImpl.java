package com.waitnotify.producerconsumer.blockingqueue;

import java.util.LinkedList;

//Class is final as this class should not be overridden
final class ProducerConsumer {

	//Interview question - Here list is final but still we are able to add the element.
	//That's because we can do that. If list is final,we cannot make an object of that list again.
	private final LinkedList<Integer> list = new LinkedList<Integer>();
	
	public static final int CAPACITY = 2;

	void producer() throws InterruptedException {
		int value = 0;

		while (true) {
			synchronized (this) {
				while (list.size() == CAPACITY) {
					wait();//Wait method should always be used in synchronized block otherwise it will give IllegalMonitorStateException
				}
				
				System.out.println("Producer produced   : " + value);
				
				list.add(value++);
				notify(); //Notify the consumer() function to continue.
				
				Thread.sleep(1000);
			}
		}
	}

	void consumer() throws InterruptedException {

		while (true) {
			synchronized (this) {
				while (list.isEmpty()) {
					wait();
				}

				int value = list.removeFirst();
				notify(); //Notify the consumer() function to continue.
				
				System.out.println("Consumer consumed  : " + value);
				
				Thread.sleep(1000);
			}
		}
	}
}

public class ProducerConsumerImpl {
	public static void main(String[] args) throws InterruptedException {

		ProducerConsumer pc = new ProducerConsumer();

		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {

				try {
					pc.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					pc.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		
		//Assuring that t1 finishes before t2
		//What is yield, join and sleep. --https://www.geeksforgeeks.org/java-concurrency-yield-sleep-and-join-methods/
		t1.join();
		t2.join();
	}
}
