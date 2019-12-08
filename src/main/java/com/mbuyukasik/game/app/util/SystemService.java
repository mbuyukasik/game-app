package com.mbuyukasik.game.app.util;

import org.springframework.stereotype.Service;

/**
 * SystemService is a class to access system level features
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */

@Service
public class SystemService {

	/**
	 * 
	 * @param argName
	 * @return JVM argument with name argName
	 */
	public String getVmArgument(String argName) {
		return System.getProperty(argName);
	}
	
}
