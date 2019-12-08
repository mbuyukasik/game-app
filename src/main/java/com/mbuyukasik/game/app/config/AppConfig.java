package com.mbuyukasik.game.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * This class will be used to access configuration parameters on demand
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Service
public class AppConfig {
	
	// All configuration parameter names are listed below
	public static final String PARAM_REPOSITORY_TYPE = "REPOSITORY_TYPE";
	public static final String PARAM_REPOSITORY_PATH = "REPOSITORY_PATH";
	public static final String PARAM_REPOSITORY_PLAYER_FILE_NAME = "REPOSITORY_PLAYER_FILE_NAME";
	public static final String PARAM_REPOSITORY_MATCH_FILE_NAME = "REPOSITORY_MATCH_FILE_NAME";
	public static final String PARAM_ELO_RATING_CONST_K = "ELO_RATING_CONST_K";
	public static final String PARAM_OUTPUT_TYPE = "OUTPUT_TYPE";

	@Autowired
	private Environment env;

	/**
	 * method retrieves configuration value for given key
	 * 
	 * @param: paramName is the key value of configuration parameter
	 */
	public String getConfigValue(String paramName) {
		return env.getProperty(paramName);
	}

}
