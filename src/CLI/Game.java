package CLI;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import PlayerClasses.PlayerContainer;

import java.io.PrintStream;

/**
 * An instance of the game, starts the game when created
 */
public class Game {
    /**
     * Stores, if this game is deterministic or random.
     */
    public boolean isDeterministic;
    /**
     * Stores the number of players.
     * The number of players is asked as input and
     * is given to the game instance in its c'tor
     */
    int playerNum;

    /**
     * Every function of the game logs its output to this stream
     */
    public static PrintStream log;

    /**
     * @param _isDeterministic
     * @param _playerNum
     * Initializes the game instance and starts the game
     */
    private Game(boolean _isDeterministic, int _playerNum) {
        isDeterministic = _isDeterministic;
        playerNum = _playerNum;
        PlayerContainer.Initialize(playerNum);
        RoundController.getInstance(); //letrehoz
        PositionLUT.getInstance();
    }

    /**
     * Starts game, asks for a maximum of 2 arguments: is the game deterministic and if it is, how many players
     * Then initializes the game instance and sets the output log to the given stream
     */
    public static Game startGame(PrintStream out, boolean isDeterministic, int playerNum) {
        log = out;
        return new Game(isDeterministic, playerNum);
    }
}
