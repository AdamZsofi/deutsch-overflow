package SnowStorm;

import GlobalControllers.PositionLUT;
import PlayerClasses.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SnowStorm {
    /**
     * Called by the RoundController after a player round
     * Based randomly => befall:
     * On some Tile selected randomly
     * 1) destroys Igloo on them
     * 2) hurts player on them (BodyHeat of players will decreased)
     * 3) puts snow on them
     */
    public void tryStorm() {
        PositionLUT pL = PositionLUT.getInstance();
        System.out.println("Snowstorm.SnowStorm.tryStorm()");

        Random rand = new Random();
        int number= rand.nextInt(100);

        if(number>30) {// ~70%
            System.out.println(number);
            return;
        }

        ArrayList<Integer[]> values = new ArrayList<>(); // coordinates of tiles
        int x,y;
        boolean flag=true;
        do{
            x= rand.nextInt(4);
            y=rand.nextInt(3);
            for(int i=0;i<values.size();i++){
                if(values.get(i)[0] ==x && values.get(i)[1]==y)
                    flag=false;
            }
            if(flag)
                values.add(new Integer[] { x,y});
            flag=true;
        } while(values.size()<4);

        for(int i=0;i<4;i++){
            int x_coord=values.get(i)[0];
            int y_coord= values.get(i)[1];

            pL.getTile(x_coord,y).destroyIgloo(); //destroy igloo
            for (Player p : pL.getPlayersOnTile(pL.getTile(x_coord,y_coord))) {
                p.changeBodyHeat(-1); //change bodyheat of players, who stand on this tile
            }
            pL.getTile(x_coord,y_coord).changeSnow(1);
        }
    }
}
