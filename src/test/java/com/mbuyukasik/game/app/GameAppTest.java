package com.mbuyukasik.game.app;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.mbuyukasik.game.app.config.GameBootConfig;

/**
 * Base of test classes
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameBootConfig.class)
public class GameAppTest {
	
	
}
