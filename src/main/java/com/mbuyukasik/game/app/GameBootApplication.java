package com.mbuyukasik.game.app;

import java.util.Scanner;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import com.mbuyukasik.game.app.config.GameBootConfig;

/**
 * GameBootApplication is the main class of spring boot application. It is
 * responsible for managing console interaction.
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
public class GameBootApplication extends GameBootConfig implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(GameBootApplication.class);

	@Autowired
	private GameManager gameManager;

	public static void main(String[] args) {
		SpringApplication.run(GameBootApplication.class, args);
	}

	/**
	 * Listens the System.in stream, and takes action for typed commands
	 * There is no logic in this class. GameManager is called for operations.
	 * 
	 * @param args : standard main method input
	 */
	@Override
	public void run(String... args) {
		LOG.info("Game application is started!");
		
		this.displayCommands();
		Scanner scanner = null;
		try {
			while (true) {
				System.out.print("Enter command : ");
				scanner = new Scanner(System.in);
				String inputString = scanner.nextLine();

				boolean isCommandValid = true;

				if (inputString != null && !inputString.isBlank()) {
					String command = inputString;
					if (inputString.contains(" ")) {
						command = command.substring(0, command.indexOf(" "));
					}
					EnmCommand enmCommand = EnmCommand.getCommand(command);
					if (enmCommand != null) {
						switch (enmCommand) {
						case EXIT:
							return;
						case HELP:
							this.displayCommands();
							break;
						case LIST_ALL_PLAYERS:
							this.gameManager.listPlayers();
							break;
						case LIST_SORTED_PLAYER_RANKS:
							this.gameManager.listPlayerRanks();
							break;
						case LIST_PLAYER_MATCHES:
							Long playerId = null;
							if (inputString.contains(" ")) {
								String strPlayerId = inputString.split(" ")[1].trim();
								if (strPlayerId != null && !strPlayerId.isBlank()) {
									if (NumberUtils.isDigits(strPlayerId)) {
										playerId = Long.parseLong(strPlayerId.trim());
									} else {
										isCommandValid = false;
									}
								}
							}
							if (isCommandValid) {
								this.gameManager.listPlayerMatches(playerId);
							}
							break;
						case LIST_SUGGESTED_MATCHES:
							this.gameManager.listSuggestedMatches();
							break;
						default:
							break;
						}
					} else {
						isCommandValid = false;
					}
				} else {
					isCommandValid = false;
				}

				if (!isCommandValid) {
					String message = "Invalid command, please check command list.";
					System.out.println(message);
					LOG.warn(message);
					this.displayCommands();
				}
			}
		} catch (Exception e) {
			LOG.error("GameAppBootApplication-start- Error Message : " + ExceptionUtils.getMessage(e), e);
			System.out.println("An unexpected exception occured.");
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		LOG.info("Game application is stopped!");
	}
	
	/**
	 * displayCommands - Displays all supported commands with their descriptions
	 */
	public void displayCommands() {
		LOG.info("Displaying command list.");
		for (EnmCommand tmpCommand : EnmCommand.values()) {
			String commandInfo = String.format("%-20s : %s", tmpCommand.getCommand(), tmpCommand.getDescription());
			System.out.println(commandInfo);
		}
		LOG.info("Displaying command list completed.");
	}

}
