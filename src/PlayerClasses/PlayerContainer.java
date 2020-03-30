package PlayerClasses;

import GlobalControllers.RoundController;

import java.util.ArrayList;
import java.util.Random;

public class PlayerContainer {

    public static PlayerContainer pc = new PlayerContainer(4);

    public ArrayList<Player> players;

    public PlayerContainer(int num){
        players = new ArrayList<Player>();
        players.add(new Eskimo());
        players.add(new Eskimo());
        players.add(new Researcher());
        players.add(new Researcher());

    }

    public Player getPlayer(int pid) {  //voiddadl tert vissza
        System.out.print("PlayerContainer:");
        System.out.println("getPlayer("+pid+")");


        for (Player p:players) {
            if(p.ID == pid)
                return p;
        }
        return null;
    };
}
