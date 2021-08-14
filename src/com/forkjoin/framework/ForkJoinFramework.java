package com.forkjoin.framework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SearchTask extends RecursiveTask<Integer> {

	int arr[];
	int start, end;
	int searchEle;

	public SearchTask(int arr[], int start, int end, int searchEle) {
		this.arr = arr;
		this.start = start;
		this.end = end;
		this.searchEle = searchEle;
	}

	@Override
	protected Integer compute() {
		System.out.println(Thread.currentThread());
		int size = end - start + 1;
		if (size > 3) {
			int mid = (end + start) / 2;
			SearchTask task1 = new SearchTask(arr, start, mid, searchEle);
			SearchTask task2 = new SearchTask(arr, mid + 1, end, searchEle);
			task1.fork();
			task2.fork();
			int result = task1.join() + task2.join();
			return result;
		} else {
			return processSearch();
		}
	}

	private Integer processSearch() {
		int count = 0;
		for (int i = start; i <= end; i++) {
			if (arr[i] == searchEle) {
				count++;
			}
		}
		return count;
	}
}

public class ForkJoinFramework {

	public static void main(String[] args) {
		int arr[] = { 6, 2, 6, 4, 5, 6, 7, 8, 6, 10, 11, 6 };
		int searchEle = 6;
		int start = 0;
		int end = arr.length - 1;

		ForkJoinPool pool = ForkJoinPool.commonPool();
		SearchTask task = new SearchTask(arr, start, end, searchEle);
		int result = pool.invoke(task);

		System.out.printf("%d found %d times", searchEle, result);
	}
}