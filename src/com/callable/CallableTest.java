package com.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyAddthread implements Callable<Integer> {

	int x;
	int y;

	public MyAddthread(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * The Callable interface returns the value from call() where as Runnable/Thread
	 * does returns a value using call()
	 */

	@Override
	public Integer call() throws Exception {
		int sum = x + y;
		return sum;
	}

}

public class CallableTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		int x = 10;
		int y = 20;

		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Future<Integer> future = executorService.submit(new MyAddthread(x, y));

		
		//This can also be done with the help of inner class.
		
//		ExecutorService executor = Executors.newFixedThreadPool(1);
//		Future<Integer> future1 = executor.submit(new Callable<Integer>() {
//			public Integer call() {
//				return MyMath.add(x, y);
//			}
//		});

		while (future.isDone())
			;

		int result = future.get();
		System.out.println("The result is : " + result);
	}
}












