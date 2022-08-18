package com.synchronised;

public class CounterExample implements Runnable {
    static int counter = 1; // a global counter

    public CounterExample() {
    }

    static synchronized void incrementCounter() {
    		System.out.println(Thread.currentThread().getName() + ": " + counter);
//    		Thread.yield();
    		counter++;			
    }

    @Override
    public void run() {
         while(counter<10){
              incrementCounter();
//              try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
         }
    }

    public static void main(String[] args) throws InterruptedException {
         CounterExample te = new CounterExample();
         Thread thread1 = new Thread(te);
         Thread thread2 = new Thread(te);

         thread1.start();
         thread2.start();    
    }
}