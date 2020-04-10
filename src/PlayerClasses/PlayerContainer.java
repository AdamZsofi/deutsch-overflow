package PlayerClasses;

import GlobalControllers.RoundController;

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
        if(pc == null) throw new NullPointerException("PlayerContainer should be initialized");
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
        players = new ArrayList<Player>();
        players.add(new Eskimo());
        players.add(new Eskimo());
        players.add(new Researcher());
        players.add(new Researcher());
        playerNum = num;

    }

    /**
     * Gives back a Player based on PlayerId (pid)
     * @param pid id of a Player
     * @return player of pid
     */
    public Player getPlayer(int pid) { // pid = players-ben az adott player indexe
        System.out.print("PlayerContainer:");
        System.out.println("getPlayer("+pid+")");

        if(pid>playerNum) throw new NullPointerException("Player with that pid does not exist");
        else return players.get(pid);
    }
}
