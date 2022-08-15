package com.executor.service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * A Runnable task to copy the given source file 
 * to the given destination file.
 */
class CopyTask implements Runnable {

	String sourceFile;
	String destFile;

	public CopyTask(String sourceFile, String destFile) {
		this.sourceFile = sourceFile;
		this.destFile = destFile;
	}

	/*
	 * Initiate the copy once thread execution begins.
	 */
	public void run() {
		try {
			IOUtils.copyFile(sourceFile, destFile);
			System.out.println("Copied from - " + sourceFile + " to " + destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class ExecutorServiceExample {

	public static void main(String[] args) throws IOException {

		String sourceFile1 = "a.txt";
		String sourceFile2 = "b.txt";

		String destFile1 = "c.txt";
		String destFile2 = "d.txt";

		// A new thread is created to initiate copy
		// from a.txt to c.txt
		// Thread-1

		/**
		 * Executing first thread. thread.start() will execute whatever we pass in the
		 * run() method
		 */
//		new Thread(new CopyTask(sourceFile1, destFile1)).start();

		// A new thread to initiate copy from
		// b.txt to d.txt
		// Thread-2

//		new Thread(new CopyTask(sourceFile2, destFile2)).start();

		/**
		 * 
		 * Assume you are submitting 100 copy tasks,then executor service uses a fixed
		 * thread pool of size 5 to execute them.
		 * 
		 * Thread creation is a costly activity as it includes creating a separate
		 * execution context, stack etc.. Hence we should refrain from creating too many
		 * threads. And also creating a thread for each task is not a good idea, instead
		 * we can create a pool of threads and effectively utilise them in executing all
		 * the task.
		 */

		ExecutorService executorService = Executors.newFixedThreadPool(5);
		executorService.execute(new CopyTask(sourceFile1, destFile1));
		executorService.execute(new CopyTask(sourceFile2, destFile2));		
	}
}
