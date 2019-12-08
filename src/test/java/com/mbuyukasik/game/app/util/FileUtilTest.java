package com.mbuyukasik.game.app.util;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mbuyukasik.game.app.GameAppTest;
import com.mbuyukasik.game.app.util.FileUtil;

/**
 * FileUtilTest - Test class of FileUtil
 * 
 * @author TTMBUYUKASIK
 *
 */
public class FileUtilTest extends GameAppTest {

	private final String LINE_1 = "This file is";
	private final String LINE_2 = "used for test";
	
	@Test
	public void testReadFile() {
		
		List<String> expectedList = new ArrayList<String>();
		expectedList.add(LINE_1);
		expectedList.add(LINE_2);
		
		List<String> lineList = FileUtil.readFile("src/test/resources/test-file.txt");
		assertLinesMatch(expectedList, lineList, "Error in FileUtil.readFile, result list size is incorrect");
		if (lineList.size() == 2) {
			boolean isContentCorrect = LINE_1.equals(lineList.get(0)) && LINE_2.equals(lineList.get(1));
			assertTrue("Error in FileUtil.readFile, result list size is incorrect", isContentCorrect);
		}
		
		assertNull(FileUtil.readFile("src/test/resources/not-found.txt"), "File does not exist, should return null!");
		
	}
	
	@Test
	public void testConcatPath() {
		
		String rootPath = File.separator + "test" + File.separator;
		String expectedPath = rootPath + "sub1" + File.separator + "sub2";
		String genaratedPath = FileUtil.concatPath(rootPath, "sub1", "sub2");
		
		assertEquals(expectedPath, genaratedPath, 
				"Path concatenation is incorrect. genarated : " + genaratedPath + ", expected: " + expectedPath);
		
		rootPath = File.separator + "test";
		genaratedPath = FileUtil.concatPath(rootPath, "sub1", "sub2");
		
		assertEquals(expectedPath, genaratedPath, 
				"Path concatenation is incorrect. genarated : " + genaratedPath + ", expected: " + expectedPath);
	}

}
