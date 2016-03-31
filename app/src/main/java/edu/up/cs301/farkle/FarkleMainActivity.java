package edu.up.cs301.farkle;

import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;

/**
 * This contains the activity for the Farkle game.
 *
 * @author Alexa Baldwin
 * @author Levi Banks
 * @author Sara Perkins
 * @author Briahna Santillana
 * @version 15 March 2016
 */
public class FarkleMainActivity extends GameMainActivity {
	// for networked play
	private static final int PORT_NUMBER = 2234;

	/**
	 * Create the default configuration for this game:
	 * - one human player vs. one computer player
	 * - minimum of 1 player, maximum of 2
	 * - one kind of computer player and one kind of human player available
	 *
	 * @return the new configuration object, representing the default configuration
	 */
	@Override
	public GameConfig createDefaultConfig() {

		// Define the allowed player types
		ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

		// Pig has two player types:  human and computer
		playerTypes.add(new GamePlayerType("Local Human Player") {
			public GamePlayer createPlayer(String name) {
				return new FarkleHumanPlayer(name);
			}});
		playerTypes.add(new GamePlayerType("Computer Player") {
			public GamePlayer createPlayer(String name) {
				return new FarkleDumbComputerPlayer(name);
			}});
		playerTypes.add(new GamePlayerType("Hard Computer Player") {
			public GamePlayer createPlayer(String name) {
				return new FarkleSmartComputerPlayer(name);
			}});


		// Create a game configuration class for Pig:
		GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Farkle", PORT_NUMBER);
		defaultConfig.addPlayer("Human", 0); // player 1: a human player
		defaultConfig.addPlayer("Computer Smart", 2); // player 2: a computer player
		defaultConfig.setRemoteData("Remote Human Player", "", 0);

		return defaultConfig;
	}

	/**
	 * create a local game
	 *
	 * @return the local farkle game
	 */
	@Override
	public LocalGame createLocalGame() {
		return new FarkleLocalGame();
	}
}
