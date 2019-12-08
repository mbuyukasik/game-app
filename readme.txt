  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.5.RELEASE)
 
 GAME APPLICATION
 
 @author 		: Mehmet Buyukasik
 @Date			: 06.12.2019
 @Reason		: ING bank assignment
 @Description	: This is a spring boot console application which is used to display players, matches and rank of players. 
 				  Application starts in GameBootApplication class and interacts with user via console. 
 				  When user types a command, GameBootApplication asks GameManager to take action. GameManager manager 
 				  keeps the main logic of each operation.

VALID COMMANDS:
***************
list-players					: Displays all players ordered by their names ASC. Command takes no argument
------------------------------------------------------------------------------------------------------------
list-player-matches	{playerID}	: Displays players' match results. Name of the opposer player and result 
								  of match is displayed as a list. playerId argument is optional. 
								  If specified, only matches of this player are listed.
------------------------------------------------------------------------------------------------------------
list-player-ranks				: Displays all players ordered with their rank ASC. name, rank, win count and loose count fields are displayed
------------------------------------------------------------------------------------------------------------
list-suggested-matches			: Generates a match list as suggestion and displays that list as "playerA Name" "playerB Name"
------------------------------------------------------------------------------------------------------------
help							: Displays all commands
------------------------------------------------------------------------------------------------------------
exit							: This command can be used to quit application

CONFIG
------
Main configurations of the application is located in resources\app-config.properties
REPOSITORY_TYPE=FILE|DATABASE
REPOSITORY_PATH= Directory of players and matches files
REPOSITORY_PLAYER_FILE_NAME= Name of player file (players.txt)
REPOSITORY_MATCH_FILE_NAME= Name of match file (matches.txt)
ELO_RATING_CONST_K= Constant value for ELO algorithm (20). Can be changed.
OUTPUT_TYPE=CONSOLE|EXCEL .Currently only console output service is implemented. But another output service can be added easily.

LOGGING
-------
Log configuration can be found under resources\logback.xml

REPOSITORY
----------
Application is designed to support multiple types of repository. Currently it is configured to read data from files(REPOSITORY_TYPE=FILE) and files are located under resources folder.
It can be easily switched to database. In order to support multiple repository types, classes with "Factory" suffix are implemented. 
These classes return suitable repository instances according to the specified repository type in config file.

OUTPUT
------
Application supports multiple output types. Currently it is configured to printout results on console.
By implementing ExcelOutputService and switching config parameter query results can be exported to excel files. 
Or any type of output service can be added.

