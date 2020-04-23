package PlayerClasses;

import CLI.Game;
import GlobalControllers.RoundController;
import ItemClasses.*;

import java.util.ArrayList;
import java.util.Random;

public class PlayerContainer {
    private int playerNum;

    private static PlayerContainer pc;

    /**
     * Gives back a reference to the PlayerContainer
     * @return
     */
    public static PlayerContainer getInstance() {
        if(pc == null) {
            Game.log.format("! PlayerContainer>getInstance : Sorry, instance does not exist THROWN NULLPOINTER EXCEPTION");
            throw new NullPointerException("PlayerContainer should be initialized");
        }
        return pc;
    }

    /**
     * Initialisation of PlayerContainer, creates a new PlayerContainer (if it's not created)
     * @param num
     */
    public static void Initialize(int num) {
        if(pc == null) {
            pc = new PlayerContainer(num);
        }
    }



    private ArrayList<Player> players;

    /**
     * Gives back the numOfPlayer, quantity (count) of all player in game
     * @return numOfPlayer
     */
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * Private constructor for initialising
     * @param num quantity (count) of all player
     */
    private PlayerContainer(int num){
        detInit();
    }

    private void detInit(){
        playerNum = 6;
        players = new ArrayList<Player>();
        players.add(new Eskimo());
        players.add(new Eskimo());
        players.add(new Researcher());
        players.add(new Researcher());
        players.add(new Researcher());
        players.add(new Researcher());

        players.get(1).inHand = new Rope();
        players.get(3).inHand = new Tent();
        players.get(5).inHand = new Shovel();
    }

    private void putTogetherInit(){
        players = new ArrayList<Player>();
        players.add(new Eskimo());
        players.add(new Eskimo());
        players.add(new Researcher());
        players.get(0).inHand = RoundController.getInstance().sg.signalFlareParts.get(0);
        players.get(1).inHand = RoundController.getInstance().sg.signalFlareParts.get(1);
        players.get(2).inHand = RoundController.getInstance().sg.signalFlareParts.get(2);
    }

    /**
     * Gives back a Player based on PlayerId (pid)
     * ERROR HANDLING: throws NullPointerException by outindexing => SHOULD BE CHECKED BY CALLER
     * @param pid id of a Player
     * @return player of pid
     */
    public Player getPlayer(int pid) { // pid = players-ben az adott player indexe
        if(pid>playerNum) {
            Game.log.format("! PlayerContainer>getPlayer : Sorry, that playerID does not exist THROWN NULLPOINTER EXCEPTION", pid);
            throw new NullPointerException("Player with that pid does not exist");
        }
        else return players.get(pid);
    }
}
