
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
    //internal variables
    private Map<String, DrawingGUI> icons = new HashMap<String, DrawingGUI>();

    private ArrayList<DrawingGUI> [][] elements = new ArrayList[6][6]; // on each Tile there's a ArrayList of Drawables
    private DrawingGUI [] [] tiles = new DrawingGUI[6][6];
    private DrawingGUI [] players = new DrawingGUI[6];
    private DrawingGUI [] activePlayers = new DrawingGUI[6];
    private ArrayList<DrawingGUI> [] hand  = new ArrayList[6];  //Collection of Drawables for each player hand
    // (Note: DivingSuit counts here=>ArrayList)
    private int [] bodyHeats = new int[6];
    private boolean messageBoxIsEnabled = false;
    private DrawingGUI messageBoxIcon;
    private String messageBoxMessage;
    //!!!!!!!!!!!!!!!!!!
    //amikor rajzolz hasznaldd relativan, ezekre a valtozokra hivatkozva a ikonok kirajzolasat
    //for more information OneDrive/GUI/sizes.png
    private int margin;
    private int tilePadding;
    private int tileSize;
    private int playerBoxX;
    private int playerBoxY;
    private int messageBoxX;
    private int messageBoxY;
    private int barPadding;
    private int textPadding;
    private int statusAndActionBoxY;
    private int statusBoxX;
    private int actionBoxX;



    // DRAWING GUI CLASSBAN a draw fuggveny(int size: parametere) => inkabb ott int lett,
    // mert szivecsket meg ilyenek is ki kell rajzolni nagyon sok lehetoseg lenne
    //viszont InGame-n belul meghivathjuk enummal ->enum intte castolodik, es mindegyik enumnak van egy konkret erteke
    //enumot pl hasznalhatsz Tilon levo elemek, playerek draw(enum size==>>int) fgvnel

    protected static InGame inGame;

    public static InGame getInstance() {
        if(inGame == null) {
            //playercount
            inGame = new InGame(6);
        }
        return inGame;
    }
    
    static GamePanel gamePanel = null;
    static Menu menu = new Menu();


    InGame(int playersCount){
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 850);
        //TODO PLAYERCONTAINER INIT (Végül maradt GAME-ben, mert az erre van)
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
        /*
        JFrame frame = new JFrame();
        frame.setTitle("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 850);
        frame.add(menu);
        frame.setVisible(true);
         */
        inGame = new InGame(4);
    }
}