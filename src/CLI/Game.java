package CLI;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;
import TileClasses.Direction;
import TileClasses.Tile;

import java.io.PrintStream;

import static GlobalControllers.RoundController.getInstance;

/**
 * An instance of the game, starts the game when created
 * @author Zsófi
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
     * @author Zsófi
     */
    private Game(boolean _isDeterministic, int _playerNum) {
        isDeterministic = _isDeterministic;
        playerNum = _playerNum;
        PlayerContainer.Initialize(playerNum);
        getInstance(); //letrehoz
        PositionLUT.getInstance();
    }

    /**
     * Starts game, asks for a maximum of 2 arguments: is the game deterministic and if it is, how many players
     * Then initializes the game instance and sets the output log to the given stream
     * @author Zsófi
     */
    public static Game startGame(PrintStream out, boolean isDeterministic, int playerNum) {
        log = out;
        return new Game(isDeterministic, playerNum);
    }

    /**
     * @param dir Direction of step
     * Calls step of current player in the given direction
     */
    void step(Direction dir) {
        PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID()).step(dir);
    }

    /**
     * Writes out a map of players on the table
     * A 3x3 example of the representation of this map:
     * [E1, R2][][]
     * [][R3][B]
     * [][][E4]
     * @author Zsófi
     */
    public void printCharacterMap() {
        // TODO hardcodeolt 6x6-os pálya, ha ez változik, akkor itt is ki kell szedni a hardcodeot
        Tile bearTile = PositionLUT.getInstance().getPosition(getInstance().polarbear);
        for(int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                Tile currentTile = PositionLUT.getInstance().getTile(x, y);
                log.print("[ ");
                for(Player p : PositionLUT.getInstance().getPlayersOnTile(currentTile)) {
                    log.print(p.getShortName()+" ");
                }
                if(currentTile == bearTile) {
                    log.print(getInstance().polarbear.getShortName()+" ");
                }
                log.print("]");
            }
        }
    }
}
