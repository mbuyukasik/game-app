package com.mbuyukasik.game.app.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * GameBootConfig is the main configuration file of the boot application
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = "com.mbuyukasik.game.app")
@Configuration
@PropertySource("classpath:app-config.properties")
public class GameBootConfig {

}
