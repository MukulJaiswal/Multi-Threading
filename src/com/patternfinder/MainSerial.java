package com.patternfinder;

import java.io.File;
import java.util.List;

public class MainSerial {

	public static void main(String[] args) throws Exception {

		// pattern to search
		String pattern = "public";

		// Directory or folder to search
		File dir = new File("./src/sample");

		// list all the files present in the folder.
		File[] files = dir.listFiles();

		PatternFinder finder = new PatternFinder();

		long startTime = System.currentTimeMillis();

		// for each file in the list of files

		for (File file : files) {

			List<Integer> lineNumbers = finder.find(file, pattern);

			if (!lineNumbers.isEmpty()) {
				System.out.println(pattern + "; found at " + lineNumbers + " in the file - " + file.getName());
			}

		}

		System.out.println(" Time taken for search - " + (System.currentTimeMillis() - startTime));

	}
}