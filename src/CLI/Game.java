package CLI;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import ItemClasses.Item;
import ItemClasses.ItemState;
import PlayerClasses.Eskimo;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;
import PlayerClasses.Researcher;
import TileClasses.Direction;
import TileClasses.Tile;
import javafx.geometry.Pos;

import java.io.PrintStream;
import java.util.ArrayList;

import static GlobalControllers.RoundController.getInstance;
import static ItemClasses.ItemState.frozen;

/**
 * An instance of the game, starts the game when created
 * @author Zsófi
 * @noinspection ALL
 */
public class Game {
    /**
     * Stores, if this game is deterministic or random.
     */
    static public boolean isDeterministic;
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
        RoundController.getInstance(); //letrehoz
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
                if(currentTile.equals(bearTile)) {
                    log.print(getInstance().polarbear.getShortName()+" ");
                }
                log.print("]");
            }
            log.println();
        }
    }

    /**
     * Writes out a map of items on the table
     * @author Ádám
     */
    public void printItemMap() {
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 6; y++){
                Tile currentTile = PositionLUT.getInstance().getTile(x,y);
                log.print("[ ");
                for(Item item : PositionLUT.getInstance().getItemOnTile(currentTile)){
                    log.print(item.getShortName()+" ");
                }
                log.print("]");
            }
            log.println();
        }
    }

    /**
     * Writes out the shelter on the table
     * @author Ádám
     */
    public void printShelterMap() {
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 6; y++){
                Tile currentTile = PositionLUT.getInstance().getTile(x,y);
                log.print("[ ");
                if(currentTile.getIglooOn()){
                    log.print("I");
                }
                else if(currentTile.tentOn){
                    log.print("T");
                }
                log.print("]");
            }
            log.println();
        }
    }

    /**
     * Writes out the thickness of snow on the table
     * @author Ádám
     */
    public void printSnowTileMap() {
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 6; y++){
                Tile currentTile = PositionLUT.getInstance().getTile(x,y);
                log.print("[ ");
                log.print(currentTile.getSnow());
                log.print("]");
            }
            log.println();
        }
    }

    /**
     * Writes out the Information about tables
     * @author Ádám
     */
    public void printTile() {
        Tile bearTile = PositionLUT.getInstance().getPosition(getInstance().polarbear);
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 6; y++){
                Tile currentTile = PositionLUT.getInstance().getTile(x,y);
                log.print("[ C:");
                log.print(currentTile.getCapacity());
                log.print(", S:"+currentTile.getSnow());
                int count=0;
                for(Player p : PositionLUT.getInstance().getPlayersOnTile(currentTile)) {
                    count++;
                }
                log.print(", SumP:"+count);
                if(currentTile.equals(bearTile))
                    log.print(", "+getInstance().polarbear.getShortName());
                log.print("]");
            }
            log.println();
        }
    }

    /**
     * Writes out the Information about items.
     * @author Ádám
     */
    public void printItem() {
        for(int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                Tile currentTile = PositionLUT.getInstance().getTile(x,y);
                for(Item item : PositionLUT.getInstance().getItemOnTile(currentTile)){
                    log.print("[ ");
                    log.print(item.getShortName()+", ");
                    log.println("]");
                }
            }
        }
    }

    /**
     * Writes out the Information about players.
     * @author Ádám
     */
    public void printPlayer() {
        for(int i=0;i<PlayerContainer.getInstance().getPlayerNum();i++) {
            Player p = PlayerContainer.getInstance().getPlayer(i);
            log.println(p.getInformation());
        }
    }
    /**
     * The player pick item up
     * @author Ádám
     */
    public void pickUp(Item i){
        PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID()).pickUp(i);
    }

    /**
     * The player dig item up
     * @author Ádám
     */
    public void digItemUp(Item i) {
        PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID()).digItemUp(i);
    }

    /**
     * Eskimo build igloo
     * @author Ádám
     */
    public void buildIgloo(){
        Eskimo e= (Eskimo) PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
        e.buildIgloo();
    }
    /**
     * Researcher detect the capacity of table
     * @author Ádám
     */
    public void detectCapacity(Direction dir){
        Researcher r = (Researcher) PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
        r.detectCapacity(dir);
    }
    /**
     * Player clear snow.
     * @author Ádám
     */
    public void clearSnow(){
        PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID()).clearSnow();
    }
    /**
     * Player saves her pal
     * @author Ádám
     */
    public void savePlayers(Direction dir){
        PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID()).savePlayers(dir);
    }
    /**
     * Player build tent
     * @author Ádám
     */
    public void buildTent(){
        Player p= PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
        Tile t= PositionLUT.getInstance().getPosition(p);
        t.putOnTent();
    }
    /**
     * Player put signalflare together
     * @author Ádám
     */
    public void putSignalTogether(){
        Player p= PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
        p.putSignalTogether(RoundController.getInstance().sg);
    }
    /**
     * Player pass round
     * @author Ádám
     */
    public void passRound(){
        PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID()).passRound();
    }
}
