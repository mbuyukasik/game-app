package com.mbuyukasik.game.app.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.mbuyukasik.game.app.GameAppTest;
import com.mbuyukasik.game.app.config.AppConfig;

/**
 * AppConfigTest - Test class of AppConfig
 * 
 * @author TTMBUYUKASIK
 *
 */
public class AppConfigTest extends GameAppTest {

	@Autowired
	private Environment env;
	
	@Autowired
	private AppConfig appConfig;
	
	@Before
	public void setup() {
	}
	
	@Test
	public void testGetConfigValue() {
		
		String configValue = env.getProperty(AppConfig.PARAM_ELO_RATING_CONST_K);
		
		assertEquals(configValue, appConfig.getConfigValue(AppConfig.PARAM_ELO_RATING_CONST_K), 
				String.format("Return value of getConfigValue for %s is incorrect. It should be '%s'", AppConfig.PARAM_ELO_RATING_CONST_K, configValue));
		
		System.out.println(env.getProperty("test"));
		
	}

}
