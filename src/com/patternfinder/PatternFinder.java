package com.patternfinder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
 
/* 
 * Below utility searches the given pattern in the file.
 */
public class PatternFinder {
	
    /* 
     * Looks for the given pattern in the file,
     * and returns the list of line numbers 
     * in which the pattern is found.
     */
	public List<Integer> find(File file, String pattern) {
		
	List<Integer> lineNumbers = new ArrayList<Integer>();
		
	// Open the file for reading.
	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			
	    int lineNo = 1;
	    String line;
 
	    // for each line in the file.
	    while ( (line = br.readLine()) != null) {
 
		if (line.contains(pattern)) {
		    // capture the lineNo where the pattern is found.
		    lineNumbers.add(lineNo);
		}
 
		lineNo++;
	    }
 
	} catch(Exception e) {
	    e.printStackTrace();
	}
		
	// Just introduced the delay for demo.
	try { Thread.sleep(1000); } catch(Exception e) {}
		
	return lineNumbers;
    }
}







