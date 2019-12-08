package com.mbuyukasik.game.app;

/**
 * EnmCommand keeps all commands that are supported by this console application
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
public enum EnmCommand {

	LIST_ALL_PLAYERS("list-players", "List all players"),
	LIST_PLAYER_MATCHES("list-player-matches",
			"List all matches of a player. It takes player id as argument (optional). Sample usage: report-player-matches {1}"),
	LIST_SORTED_PLAYER_RANKS("list-player-ranks", "List all players' score and rank sorted by score."),
	LIST_SUGGESTED_MATCHES("list-suggested-matches", "List suggested matches."), HELP("help", "List all commands"),
	EXIT("exit", "Exit application");

	private final String command;
	private final String description;

	EnmCommand(String command, String description) {
		this.command = command;
		this.description = description;
	}

	public String getCommand() {
		return command;
	}

	public String getDescription() {
		return description;
	}

	public static EnmCommand getCommand(String command) {
		EnmCommand enmCommand = null;
		if (command != null && !command.isBlank()) {
			for (EnmCommand tmpCommand : EnmCommand.values()) {
				if (tmpCommand.getCommand().equalsIgnoreCase(command)) {
					enmCommand = tmpCommand;
				}
			}
		}
		return enmCommand;
	}
	
}
