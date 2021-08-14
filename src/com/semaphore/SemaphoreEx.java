package com.semaphore;
import java.util.concurrent.Semaphore;

/**
 * Semaphore allows the multiple threads to enter into the critical section simultaneously.
 * If we have used synchronised block then only one thread can enter into the critical section
 * But what if we need more thread to access the resource. 
 * Semaphore is used in that case.
 * 
 * @author Mukul
 *
 */
class PrintThread extends Thread {
  int id;
  Semaphore semaphore;
  
  PrintThread(int id, Semaphore semaphore) {
    this.id = id;
    this.semaphore = semaphore;
  }

  public void run() {
	  try {
		  semaphore.acquire();
		  
		  // critical sections starts
	      System.out.println("Printer " + id + " is printing...");
	      Thread.sleep(2000);
	      //critical sections ends
	      
	      semaphore.release();
	  } catch(InterruptedException e) {
		  e.printStackTrace();
	  }
  }
}

public class SemaphoreEx {
	
  public static void main(String [] args) {
	  
	  //Here 2 means two threads can enter into the critical section
	  Semaphore semaphore = new Semaphore(2);
	  
	  //The same semaphore object needs to be passed to the constructor
      new PrintThread(1, semaphore).start();
      new PrintThread(2, semaphore).start();
      new PrintThread(3, semaphore).start();
      new PrintThread(4, semaphore).start();
  }
}


















