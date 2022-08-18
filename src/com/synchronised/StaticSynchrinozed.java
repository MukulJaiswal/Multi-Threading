package com.synchronised;

public class StaticSynchrinozed {

	static int a = 5;
	int b = 10;

	public synchronized void increment() {
		a++;
		b++;
	}
}

/**
 * The above method is not synchronized because of the static variable.
 * 
 */

/**
 * Inorder to do synchronization on static variable, we have to do provide a
 * synchronized block and lock the Class object.
 *
 */
class Check {

	static int a = 5;
	int b = 10;

	public void increment() {
		// lock the Class object before modifying
		// static content.
		synchronized (Check.class) {
			a++;
		}

		// lock the object before modifying
		// instance members.
		synchronized (this) {
			b++;
		}
	}

	/**
	 * We can apply synchronized on static method as well so that in order to
	 * achieve sync on static variable. 
	 * public static synchronized increment() {
	 * 	  a++; 
	 * }
	 * 
	 */

	/**
	 * Synchronized can be done by using synchronized method as well as in synchronized block.
	 * Synchronized block is preferred because it allows us to synchorized only that
	 * peace of code which we want instead of whole function.
	 */
}
