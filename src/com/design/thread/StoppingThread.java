package com.design.thread;

class Mythread extends Thread {

	public void run() {

		while (true) {
			if (interrupted()) {
				System.out.println("Thread is Interrupted, hence stopping.");
				System.out.println(
						"Here roll back code is given because after thread interrupt, all process should be rollback");
				break;
			}
			System.out.println("T");
		}
	}
}

class CopyTask implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.print("C");
		}
	}
}

class ProgressTask implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.print("-");
		}
	}
}

public class StoppingThread {
	public static void main(String[] args) {

		Mythread mythread = new Mythread();
		mythread.start();

		try {
			/**
			 * This method belongs to the Thread class and it doesn't releases the lock unlike wait(). This method
			 * throws InterruptedException if the thread is interrupted while it is in
			 * sleep.
			 */
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		/**
		 * stop() method of the Thread class could be used to stop the thread in the
		 * middle. But this is the dangerous thing to do as it leaves the system in
		 * inconsistent state, because we are not giving the opportunity to the thread
		 * to rollback or reverse the actions that it has taken. And hence the stop
		 * method is deprecated and should not be used.
		 * 
		 * Correct approach would be to call the interrupt() method on the thread and
		 * then it is up to the thread to consider whether to stop or not.
		 */

//		mythread.stop(); //This should not be used.
		mythread.interrupt();

		/************************************************************
		 * Thread Priority
		 ************************************************************/

		/**
		 * MIN_PRIORITY - 1 being the minimum priority 
		 * NORM_PRIORITY - 5 is the normally priority, this is the default priority value. 
		 * MAX_PRIORITY - 10 being the max priority.
		 */
		CopyTask copyTask = new CopyTask();
		Thread copyThread = new Thread(copyTask);
		copyThread.setPriority(Thread.NORM_PRIORITY + 3);
		copyThread.start();
		

		ProgressTask progressTask = new ProgressTask();
		Thread progressThread = new Thread(progressTask);
		progressThread.start();

	}
}
