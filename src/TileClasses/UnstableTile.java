package TileClasses;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import PlayerClasses.Player;

import java.util.ArrayList;
import java.util.Random;

public class UnstableTile extends Tile {

    private int capacity;  ///sztem jobb lenne a Tile ban, igy egyszerubb, overridekj nelkul és a kutató is egyszerubben nezheti meg az egeszet
    public int standingHere;//ennyi erovel ez is mehetne oda.

    public UnstableTile(int x, int y) {
        super(x, y);
        Random r = new Random();
        capacity = r.nextInt(RoundController.rc.pc.players.size()) + 1 ;
    }

    @Override
    public void steppedOff(Direction dir) {//todo ha sokan beleestek es megmentettek oket, visszalehet ide jonni? nincs lekezelve
        super.steppedOff(dir);
        standingHere--;
    }

    @Override
    public void steppedOn(Player p) {
        super.steppedOn(p);
        if(capacity-standingHere<0){
            Tile t = PositionLUT.pLUT.playerTileMap.get(p);
            ArrayList<Player> players = PositionLUT.pLUT.tilePlayerMap.get(t);
            for (Player player: players) {
                player.fallInWater();
            }
        }
    }
}
