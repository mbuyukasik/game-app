package com.mbuyukasik.game.app.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FileUtil is a class to keep commonly used utility methods that operate on files
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
public class FileUtil {

	private static Logger LOG = LoggerFactory.getLogger(FileUtil.class);

	public static void safeCloseBufferedReader(BufferedReader bufferedReader) {
		try {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		} catch (IOException e) {
			LOG.error("FileUtil-safeCloseBufferedReader() - Error Message: " + ExceptionUtils.getMessage(e), e);
		}
	}

	/**
	 * readFile - read file and return content as line list
	 * @param file : File to be read
	 * @return returns line(String) list of given file. If file not found returns null.
	 */
	public static List<String> readFile(File file) {
		List<String> lineList = null;
		if (file != null && file.exists()) {
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String line = br.readLine();

				lineList = new ArrayList<String>();
				while (line != null) {
					lineList.add(line);
					line = br.readLine();
				}
			} catch (Exception e) {
				LOG.error("FileUtil-readFile()- Error Message:", e);
				return null;
			} finally {
				safeCloseBufferedReader(br);
			}	
		} else {
			LOG.error("FileUtil-readFile()- Error Message: File not found. file_name: " + file != null ? file.getAbsolutePath() : "");
		}
		return lineList;
	}
	
	/**
	 * readFile - read file and return content as line list
	 * @param filePath : Full file path
	 */
	public static List<String> readFile(String filePath) {
		return readFile(new File(filePath));
	}
	
	/**
	 * readFile - read file from classpath
	 * @param file : File to be read
	 * @return returns line(String) list of given file. If file not found returns null.
	 */
	public static List<String> readClassPathFile(String fileName) {
		URL resource = FileUtil.class.getClassLoader().getResource(fileName);
		if (resource != null) {
			return readFile(resource.getFile());
		} else {
			return null;
		}
	}
	
	/**
	 * concatPath - read file and return content as line list
	 * @param path : current value of path
	 * @param subPathArr : Array of subPath
	 * @return concatenation of given sub paths
	 */
	public static String concatPath(String path, String... subPathArr) {
		for (String subPath : subPathArr) {
			if (!path.endsWith(File.separator)) {
				path += File.separator;
			}
			path += subPath;
		}
		
		return path;
	}

}
