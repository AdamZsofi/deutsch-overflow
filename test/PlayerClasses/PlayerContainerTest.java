package PlayerClasses;

import CLI.Game;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerContainerTest {
    static PlayerContainer pc;

    @BeforeClass
    public static void init(){
        Game.log=System.out;
        Game.isDeterministic=true;
        PlayerContainer.Initialize(6, 0);
        pc = PlayerContainer.getInstance();
    }

    @Test
    public void getPlayerTest() {

        assertEquals(6, pc.getPlayerNum());
        for(int i=0;i<pc.getPlayerNum();i++){
            pc.getPlayer(i);
        }
    }

    @Test(expected=NullPointerException.class)
    public void getPlayerExceptionTest(){
        pc.getPlayer(pc.getPlayerNum());
    }
}