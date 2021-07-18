package com.synchronised;

class Sample {

	private int x;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	/*
	 * Try removing synchronized.
	 */
	public synchronized void incr() {

		// read the value of x.
		int y = getX();

		// Increment the value
		y++;

		/**
		 * Just assume if control is switched to some other thread and it too looks at
		 * the old value of x and proceeds with modification. Such scenarios lead to in
		 * consistent results. To simulate such scenarios lets us just pass the control
		 * to some other thread.
		 * 
		 * with sleep this thread will go to blocked state for the given time interval,
		 * hence other thread will get a chance.
		 */

		try {
			Thread.sleep(1);//By this other thread will get a chance to set the value.
		} catch (Exception e) {
		}

		// set x to new value.
		setX(y);
	}
}

class MyThread extends Thread {

	Sample obj;

	public MyThread(Sample obj) {
		this.obj = obj;
	}

	public void run() {
		obj.incr();
	}

}

public class Synchronized {

	public static void main(String[] args) {

		Sample obj = new Sample();
		obj.setX(10);

		// In this case both the threads t1 and t2
		// are sharing the same Sample object obj.
		// Both the threads will try to perform the
		// increment operation simultaneously.

		MyThread t1 = new MyThread(obj);
		MyThread t2 = new MyThread(obj);

		t1.start();
		t2.start();

		// Here main thread called the join operation
		// on t1 and t2. join() operations waits for
		// thread to complete before returning.
		
		//When t1 completes the processing then only t2 starts the processing. This is joins on thread.
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(obj.getX());
	}
}

/**
 * Reentrant Locks is preferred over Synchronized lock. Because this prevents the thread from starvation
 * //https://www.geeksforgeeks.org/reentrant-lock-java/
 */

