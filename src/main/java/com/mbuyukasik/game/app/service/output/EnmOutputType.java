package com.mbuyukasik.game.app.service.output;

/**
 * All supported output types are defined in this enum
 * equivalent OutputService class should be implemented to support this type
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
public enum EnmOutputType {

	CONSOLE("CONSOLE"),
	EXCEL("EXCEL");

	private final String id;

	EnmOutputType(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public static EnmOutputType getOutputType(String strOutputType) {
		EnmOutputType enmOutputType = null;
		if (strOutputType != null && !strOutputType.isBlank()) {
			for (EnmOutputType outputType : EnmOutputType.values()) {
				if (outputType.getId().equalsIgnoreCase(strOutputType)) {
					enmOutputType = outputType;
				}
			}
		}
		return enmOutputType;
	}
	
}
