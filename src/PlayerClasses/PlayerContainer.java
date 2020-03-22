package PlayerClasses;

import java.util.ArrayList;
import java.util.Random;

public class PlayerContainer {

    public ArrayList<Player> players;

    public PlayerContainer(int num){
        players = new ArrayList<Player>();
        Random random = new Random();
        for(int i = 0; i<num; i ++){
            if(random.nextInt(100) + 1  <= 70 )//70%os esellyel lesz kutato (random szam 1-100 <= 70)
                players.add(new Researcher());
            else
                players.add(new Eskimo());
        }
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
