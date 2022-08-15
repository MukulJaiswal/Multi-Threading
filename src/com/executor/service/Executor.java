
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
}
