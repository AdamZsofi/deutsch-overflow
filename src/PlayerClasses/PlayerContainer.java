package PlayerClasses;

import GlobalControllers.RoundController;

import java.util.ArrayList;
import java.util.Random;

public class PlayerContainer {
    private int playerNum;

    private static PlayerContainer pc;

    public static PlayerContainer getInstance() {
        if(pc == null) throw new NullPointerException("PlayerContainer should be initialized");
        return pc;
    }

    public static void Initialize(int num) {
        if(pc == null) {
            pc = new PlayerContainer(num);
        }
    }



    private ArrayList<Player> players;

    public int getPlayerNum() {
        return playerNum;
    }

    private PlayerContainer(int num){
        players = new ArrayList<Player>();
        players.add(new Eskimo());
        players.add(new Eskimo());
        players.add(new Researcher());
        players.add(new Researcher());
        playerNum = num;

    }

    public Player getPlayer(int pid) { // pid = players-ben az adott player indexe
        System.out.print("PlayerContainer:");
        System.out.println("getPlayer("+pid+")");

        if(pid>playerNum) throw new NullPointerException("Player with that pid does not exist");
        else return players.get(pid);
    }
}
