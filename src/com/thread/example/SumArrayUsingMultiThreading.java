package com.thread.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * This class adds the sum of the assigned partition of the array.
 */
class SumCalculatorTask implements Callable<Integer> {

	private int arr[];
	private int start;
	private int end;

	public SumCalculatorTask(int arr[], int start, int end) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	/*
	 * Calculate the sum of the elements of the given partition i.e. from the given
	 * start index to end index.
	 */
	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum += arr[i];
		}
		return sum;
	}

}

public class SumArrayUsingMultiThreading {

	public static void main(String[] args) {
		// Example Data
		int arr[] = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };

		// Size of each block.
		int blockSize = 3;

		// Number of blocks
		// If say the value for noOfBlocks is 3.3,
		// we know that there are 4 blocks.
		// Hence took the ceil value i.e. 4.
		int noOfBlocks = (int) Math.ceil((double) arr.length / blockSize);

		// A fixed thread pool of size 3 to calculate the sum.
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		// Future objects are saved to fetch the results after completion.
		List<Future<Integer>> futureObjList = new ArrayList<Future<Integer>>();

		// For each partition.
		int start = 0, end;
		for (int i = 1; i <= noOfBlocks; i++) {

			// Calculate the starting and ending index positions
			// of the i th partition.
			start = (i - 1) * blockSize;
			end = start + blockSize - 1;

			// Check if end crosses the actual array size,
			// if yes take the last
			// element index.
			if (end >= arr.length) {
				end = arr.length - 1;
			}

			// Submit the SumCalculatorTask a Callable task
			// which is responsible
			// for calculating the partition sum.
			Future<Integer> future = executorService.submit(new SumCalculatorTask(arr, start, end));

			// We need it to fetch the computed sum,
			// hence added to the list.
			futureObjList.add(future);
		}

		// Query each Future object and fetch the computed result,
		// then add it to the totalSum.

		int totalSum = 0;
		for (Future<Integer> future : futureObjList) {
			totalSum += getComputedValue(future);
		}

		// Display the computed total.
		System.out.println("Final Sum " + totalSum);
		executorService.shutdown();

	}

	private static int getComputedValue(Future<Integer> future) {

		// Waiting for future object to complete
		while (!future.isDone()) {
			// Pass control to other threads;
			// if any; waiting for CPU.
			Thread.yield();
		}

		try {
			// Get the computed result.
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return 0; // DANGEROUS: Ignored exceptions.
		}
	}

}
