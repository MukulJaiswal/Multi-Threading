package com.synchronised;

import java.util.HashMap;
import java.util.Map;

public class TrainReservationExample {

	public static void main(String[] args) throws InterruptedException {

		TicketReservationSystem reservationSystem = new TicketReservationSystem();
		String trainName = "a";
		int ticketCount = 50;

		// These two threads are trying to book 50 tickets for train "a".
		// After the operation expected available tickets are 0.
		Thread t1 = new Thread(new TicketBookingTask(reservationSystem, trainName, ticketCount));

		Thread t2 = new Thread(new TicketBookingTask(reservationSystem, trainName, ticketCount));
		
		Thread t3 = new Thread(new TicketBookingTask(reservationSystem, trainName, ticketCount));

		t1.start();
		t2.start();
		t3.start();

		// Wait for both the threads to complete.
		t1.join();
		t2.join();
		t3.join();

		// Expected result is 0, but you might find 50.
		System.out.println("Available tickets for train - " + trainName + " are "
				+ reservationSystem.getAvailableTickets(trainName));

	}
}

/*
 * Class that is responsible for reserving/booking tickets.
 */
class TicketReservationSystem {

	Map<String, Integer> reservationInfo = new HashMap<String, Integer>();

	public TicketReservationSystem() {
		init();
	}

	private void init() {
		reservationInfo.put("a", 100);
		reservationInfo.put("b", 100);
	}

	/*
	 * Reserves the tickets for the given train, if there are sufficient tickets
	 * available.
	 */
	public synchronized void reserveTickets(String trainName, int ticketCount) {
		Integer available = reservationInfo.get(trainName);

		if (available == null) {
			System.out.println("Invalid train name " + trainName);
			return;
		}

		if (available < ticketCount) {
			System.out.println("Could not reserve, requested seats not available " + Thread.currentThread().getName());
			return;
		}

		// For demo purpose.
		// Intentionally passed on control to other thread
		// before updating available tickets.
		// This is to demonstrate that what if control is switched here..
		Thread.yield();

		// Reduce the available ticket count.
		available -= ticketCount;

		// Update the Map.
		reservationInfo.put(trainName, available);
	}

	/*
	 * Returns available tickets associated with the given trainName.
	 */
	public Integer getAvailableTickets(String trainName) {
		return reservationInfo.get(trainName);
	}
}

/*
 * A Runnable task class that enables parallel ticket booking.
 */
class TicketBookingTask implements Runnable {

	private TicketReservationSystem reservationSystem;
	private String trainName;
	private int ticketCount;

	public TicketBookingTask(TicketReservationSystem reservationSystem, String trainName, int ticketCount) {
		this.reservationSystem = reservationSystem;
		this.trainName = trainName;
		this.ticketCount = ticketCount;
	}

	@Override
	public void run() {
		reservationSystem.reserveTickets(trainName, ticketCount);
	}

}