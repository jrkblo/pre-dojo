package dev.amil.cubo.framework;

/**
 * Enum dos tipos de comandos que podem ser enviados ao servidor
 * @author junior
 *
 */
public enum GameCommand {
	CREATE(),
	CONNECT(),
	STOP(),	
	START(),
	RESTART(),
	MOVE(),
	SHOT(),
	KILL();
}
