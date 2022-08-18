package com.waitnotify.producerconsumer.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Producer Consumer problem with blocking queue.
 * @author Mukul
 * 
 * BlockingQueue is an Interface. This provides a thead safe implementation. BlockingQueue implementations are : 
 * 1.ArrayBlockingQueue
 * 2.LinkedBlockingQueue
 * 3.PriorityBlockingQueue
 * 
 * BlockingQueue has following methods
 * 
 * >Operations that throws exception:
 * 		1.add()
 * 		2.remove()
 * 		3.element()
 * >Operations that returns the boolean value.
 * 		1.Offer()
 * 		2.Poll()
 * 		3.peek()
 * 
 * Difference between add() and offer() is that add throws an exception incase of inadequate capacity 
 * whereas offer() returns false. So offer() is preferred. 
 */

/**
 * How BlockingQueue is beneficial for Producer-Consumer problem. It has two important functions: 
 * 
 * 1.put() : It will add the element in the queue, but if the queue is full, it will block the thread till the space is available.
 * 2.take() : Returns the head element of the queue, if queue is empty this method will block the thread till an element is available.
 * 
 * 3.offer(o, timeout, timeunit) : offer and poll is overloaded with timeout
 * 4.poll(timeout, timeunit) : offer and poll is overloaded with timeout
 *
 */

/**
 * If we are using BlockingQueue then wait() and notify() is not used as it takes care of this 
 * already by using put() and take() functions
 * @author Mukul
 *
 */

class ProducerThread extends Thread {
	BlockingQueue<String> queue;

	public ProducerThread(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			String msg = "Hello-" + i;

			// Blocks the thread until the space is available.
			try {
				queue.put(msg);
				System.out.println("Produced - " + msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

class ConsumerThread extends Thread {
	BlockingQueue<String> queue;

	public ConsumerThread(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			String message = null;

			// Blocks the thread until the element is available.
			try {
				message = queue.take();
				System.out.println("Consumed - " + message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

public class BlockingQueueImpl {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
		new ProducerThread(queue).start();
		new ConsumerThread(queue).start();
	}
}











