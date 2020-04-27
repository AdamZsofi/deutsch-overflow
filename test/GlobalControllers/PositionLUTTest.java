package GlobalControllers;

import CLI.Game;
import ItemClasses.*;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;
import TileClasses.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionLUTTest {
    static PositionLUT pl;
    @Before
    public static void init(){
        Game.log=System.out;
        Game.isDeterministic=true;
        PlayerContainer.Initialize(6, 0);
        PositionLUT.Initialize(true, 0);
        pl=PositionLUT.getInstance();
    }

    @Test
    public void itemPositionTest(){
        Tile t1=pl.getTile(0, 5);
        Tile t2=pl.getTile(4, 4);
        Item i=pl.getItemOnTile(t1).get(0);

        assertEquals(t1, pl.getPosition(i));

        pl.setPosition(i, t2);

        assertEquals(t2, pl.getPosition(i));
        assertTrue(pl.getItemOnTile(t2).contains(i));
    }

    @Test
    public void playerPositionTest(){
        Tile t1=pl.getTile(0, 5);
        Tile t2=pl.getTile(4, 4);
        Player p=pl.getPlayersOnTile(t1).get(0);

        assertEquals(t1, pl.getPosition(p));

        pl.setPosition(p, t2);

        assertEquals(t2, pl.getPosition(p));
        assertTrue(pl.getPlayersOnTile(t2).contains(p));
    }

}