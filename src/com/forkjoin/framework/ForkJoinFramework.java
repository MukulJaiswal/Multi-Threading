package com.forkjoin.framework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * As the name(fork-join) itself suggest it breaks the task and then collect the task to complete the process.
 * This works like a recursion tree that'why we have to extends the Recursive Tree.
 * @author Mukul
 */

/**
 * Alternative of Executor Framework.It differs from ExecutorService by virtue of employing
 * Work-stealing
 */
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

	/**
	 * This works exactly as recursion.Here we're giving base condition as when the
	 * size is less than 3 then don't further break the task
	 */

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

/**
 * Difference between ForkJoin framework and ExecutorService.
 * 
 * https://www.geeksforgeeks.org/difference-between-fork-join-framework-and-executorservice-in-java/
 * -> See last difference.
 * 
 * Fork Join is an implementation of ExecuterService. The main difference is
 * that this implementation creates a DEQUE worker pool. Executor service
 * creates asked number of thread, and apply a blocking queue to store all the
 * remaining waiting task.
 * 
 * 
 * Important** 
 * It differs from ExecutorService by virtue of employing
 * Work-stealing. i.e. if a worker thread has no tasks in the pipeline it will
 * take the task from the task queue of the other busy thread so that the
 * workload is efficiently balanced.
 * 
 * To access the pool, A static common pool is available for the application and
 * it can be accessed through commonPool() method of the ForkJoinPool class.
 * Using the commonPool is the preferred approach because creating multiple
 * thread pools might have an adverse impact on the performance of the
 * application eg. ForkJoinPool pool = ForkJoinPool.commonPool();
 * 
 * RecursiveTask or RecursiveAction needs to be extended for using this.
 * RecursiveTask returns the value whereas RecursiveAction doesn't returns the
 * value.
 */
