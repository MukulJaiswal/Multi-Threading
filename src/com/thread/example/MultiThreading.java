package com.thread.example;

class Mythread extends Thread {

	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.print("T");
		}
	}
}

/**
 * Always use below method to create a thread because working on Interface is always recommended.
 * Also in Java we can extend only one class but implements multiple Interfaces. So this method gives us more flexity.
 *
 */
class MyTask implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.print("-");
		}	
	}
}

public class MultiThreading {
	public static void main(String[] args) {
		Mythread mythread = new Mythread();
		mythread.start();
		
		MyTask mytask = new MyTask();
		
		Thread thread = new Thread(mytask);
//		thread.setDaemon(true); This is the low priority thread which doesn't prevent the JVM from existing --https://www.geeksforgeeks.org/daemon-thread-java/
		thread.start();
		
		for (int i = 0; i < 1000; i++) {
			System.out.print("M");
		}
	}
}
