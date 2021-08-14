package com.advancedtopic;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

class SharedService {
	
	static AtomicBoolean initialized = new AtomicBoolean(false);
	
	public void init() {
		if ( ! initialized.compareAndSet(false, true)) {
			System.out.println("already initialized");
			return;
		}
		System.out.println("initializing.....");
	}
	
	public void service() {
		init();
		System.out.println("perform some task");
	}
}

public class AtomicTypesEx {

	public static void main(String[] args) {
		SharedService service = new SharedService();
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (int i = 1; i <= 3; i++) {
			executor.submit(new Runnable() {
				public void run() {
					service.service();
				}
			});
		}
		executor.shutdown();
	}

}
