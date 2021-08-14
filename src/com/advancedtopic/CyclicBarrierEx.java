package com.advancedtopic;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// CyclicBarrier

// A and B are two participants in a game where they should start
// at once when both are ready. (Rendezvous Pattern)

class A extends Thread {
	CyclicBarrier barrier;
	
	public A(CyclicBarrier barrier) {
		this.barrier = barrier;
	}
	
	public void run() {
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		System.out.println("A begins...");
	}
}

class B extends Thread {
	CyclicBarrier barrier;
	
	public B(CyclicBarrier barrier) {
		this.barrier = barrier;
	}
	
	public void run() {
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		System.out.println("B begins...");
	}
}

public class CyclicBarrierEx {

	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(2);
		new A(barrier).start();
		
		try { Thread.sleep(5000); } catch(InterruptedException e) {}
		
		new B(barrier).start();
	}

}
