package com.executor.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* A simple utility to copy the content 
 * of the given source file to the given 
 * destination file. */
public class IOUtils {

	/*
	 * Copies the given source stream i.e. src to the given destination stream i.e.
	 * dest.
	 */
	public static void copy(FileInputStream src, FileOutputStream dest) throws IOException {
		int value;
		while ((value = src.read()) != -1) {
			dest.write(value);
		}
	}

	/*
	 * Copies the given srcFile to the destFile.
	 */
	public static void copyFile(String srcFile, String destFile) throws IOException {
		FileInputStream fin = new FileInputStream(srcFile);
		FileOutputStream fout = new FileOutputStream(destFile);

		copy(fin, fout);

		fin.close();
		fout.close();
	}
}