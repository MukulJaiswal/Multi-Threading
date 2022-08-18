
package com.executor.service;

public class Executor {
	
	/**
	 * java.util.concurrent package contains the following important utilities
	 * 
	 * 1.ExecutorService
	 * 2.Callable Interface
	 * 3.Future Object
	 * 4.Lock
	 * 
	 * In case of Runnale Interface, use execute method of ExecutorService
		 * 	ExecutorService executorService = Executors.newFixedThreadPool(5);
			executorService.execute(new CopyTask(sourceFile1, destFile1));
		
		In case of Callable Interface, use submit method of ExecutorService
			ExecutorService executorService = Executors.newFixedThreadPool(5);
		 *  Future<Integer> future = executorService.submit(new MyAddthread(x, y));
	 */
	
	/**
	 * Instead of making individual thread, we can use executor service create a thread pool for us.
	 * 
	 * For Example
	 * Thread thread1 = new Thread(new Runnable() {public void run() {});
	 * Thread thread2 = new Thread(new Runnable() {public void run() {});
	 * Thread thread3 = new Thread(new Runnable() {public void run() {});
	 * 
	 * Above we're making a 3 thread to execute the task. This can be replaced with ExecutorFramework
	 * 
	 * ExecutorService executorService = Executors.newFixedThreadPool(3);
	 * executorService.execute(new Runnable() {public void run() {});
	 * 
	 */
}
