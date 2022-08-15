package com.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class GetStockQuoteTask implements Callable<Double> {

	private String stockSymbol;

	public GetStockQuoteTask(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public Double call() {
		// Write some logic to fetch the stock price
		// for the given symbol and return it.
		return 0.0;
	}
}

public class StockQuoteCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		String symbol = "Prince Pipes";
		ExecutorService executor = Executors.newFixedThreadPool(1);

		GetStockQuoteTask task = new GetStockQuoteTask(symbol);
		Future<Double> future = executor.submit(task); //Return type is stored in Future Object
		Double price = future.get();
	}
}
