
package GUI;

import Control.KeyboardInterpreter;
import GlobalControllers.PositionLUT;
import ItemClasses.Item;
import Main.Main;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;
import TileClasses.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Main.Main.interpreter;


public class InGame extends JFrame {
    protected static InGame inGame;

    public static InGame getInstance() {
        if(inGame == null) {
            inGame = new InGame();
        }
        return inGame;
    }

    public static GamePanel gamePanel = null;
    static Menu menu = new Menu();


    InGame(){
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 850);
        this.getContentPane().add(menu);
        setVisible(true);
    }


    public void changeToGameScreen(int playerNum){
        this.getContentPane().removeAll();
        //frame.getContentPane().add(new JPanel());
        gamePanel = new GamePanel();

        gamePanel.addKeyListener(Main.interpreter);
        gamePanel.setFocusable(true);
        Main.interpreter.startGame(playerNum);

        add(gamePanel);
        gamePanel.refreshComponents();
        revalidate();
    }

    public static void createWelcomeScreen() {
        inGame = new InGame();
    }
}