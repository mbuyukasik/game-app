package com.mbuyukasik.game.app.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.mbuyukasik.game.app.util.FileUtil;

/** 
 * Common behavior of file repository classes will be defined here
 * @author: mehmet buyukasik
 * @version 1.0
 */
public abstract class BaseFileRepository<T> extends BaseRepository<T> {

	private static Logger LOG = LoggerFactory.getLogger(BaseFileRepository.class);
	
	@Value("${REPOSITORY_PATH}")
	private String paramRepositoryPath;
	
	/**
	 * This method is used to read a specific repository file
	 * if file is not found in specified paramRepositoryPath method will search it in classpath 
	 * @param fileName: Name of the file. Eg: players.txt
	 */
	protected List<String> readFileRepository(String fileName) {
		String filePath = FileUtil.concatPath(this.paramRepositoryPath, fileName);
		if (new File(filePath).exists()) {
			return FileUtil.readFile(filePath);
		} else {
			List<String> fileLineList = FileUtil.readClassPathFile(fileName);
			if (fileLineList != null) {
				return fileLineList;
	        } else {
	        	LOG.error("BaseFileRepository-readFileRepository-" + fileName + " does not exist in repository directories. dir path: " + paramRepositoryPath);
	        	return new ArrayList<String>();
	        }
		}
	}
	
}
