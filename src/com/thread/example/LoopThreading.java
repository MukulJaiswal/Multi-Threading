package com.thread.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoopThreading {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		System.out.println(Runtime.getRuntime().availableProcessors());

		ExecutorService executorService = Executors.newFixedThreadPool(1);
		int count = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				for (int j2 = 0; j2 < 100; j2++) {
					executorService.execute(new Runnable() {


						@Override
						public void run() {
//							synchronized (this) {
//							}
						}
					});
				}
			}
		}
		executorService.shutdown();
		System.out.println(count);
		System.out.println(System.currentTimeMillis() - start);
	}
}
