package com.mbuyukasik.game.app.service.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * OutputServiceFactory class is responsible for creating concrete class for OUTPUT_TYPE defined in config parameters
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Service
public class OutputServiceFactory {

	private static Logger LOG = LoggerFactory.getLogger(OutputServiceFactory.class);
	
	private ConsoleOutputService consoleOutputService;
	private ExcelOutputService excelOutputService;
	
	@Value("${OUTPUT_TYPE}")
	private String paramOutputType;

	@Autowired
	public OutputServiceFactory(ConsoleOutputService consoleOutputService, ExcelOutputService excelOutputService) {
		this.consoleOutputService = consoleOutputService;
		this.excelOutputService = excelOutputService;
	}
	
	/**
	 * getOutputService - returns concrete output service class by checking OUTPUT_TYPE value
	 */
	public IOutputService getOutputService() {
		if (paramOutputType.equalsIgnoreCase(EnmOutputType.CONSOLE.getId())) {
			return this.consoleOutputService;
		} else if (paramOutputType.equalsIgnoreCase(EnmOutputType.EXCEL.getId())) {
			return this.excelOutputService;
		} else {
			LOG.error(String.format("Output type not supported. (%s)", this.paramOutputType));
			return null;
		}
	}
	
}
