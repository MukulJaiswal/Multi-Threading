package com.advancedtopic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// CountDownLatch
/**
 * To understand its application, let us consider a server where the main task
 * can only start when all the required services have started.
 *
 */

/**
 *In this example, support we have to migrate the data in the batch size of 100 and
 *display the message "User data migration completed" when data is successfully migrated.
 */
class UserMigrateTask implements Runnable {

	public void run() {
		int recordCount = 320;
		int batchSize = 100;
		int nPages = (int) Math.ceil((double) recordCount / batchSize);

		ExecutorService executor = Executors.newFixedThreadPool(2);
		CountDownLatch latch = new CountDownLatch(nPages);

		for (int i = 1; i <= nPages; i++) {
			final int pageNo = i;
			executor.submit(new Runnable() {
				public void run() {
//					try {Thread.sleep(3000);}catch(Exception e) {};
					System.out.println("Migrating page - " + pageNo);
					latch.countDown();
				}
			});
		}
		executor.shutdown();

		boolean success = false;
		try {
			success = latch.await(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (success) {
			System.out.println("User data migration completed.");
		} else {
			System.out.println("Timed out while waiting for User data migration to completed.");
		}
	}
}

public class CountDownLatchEx {
	public static void main(String[] args) {
		new Thread(new UserMigrateTask()).start();
	}
}
