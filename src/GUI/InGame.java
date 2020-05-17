package GUI;

import GlobalControllers.PositionLUT;
import ItemClasses.Item;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;
import TileClasses.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
            inGame = new InGame(6);
        }
        return inGame;
    }
    public void initComponents(int playerNum) {
        initIcons(playerNum);

        //loading Tile Icons
        for (int i = 0 ; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                tiles[i][j] = icons.get("tileSnow");
            }
        }

        //loading Player and Active Players Icons
        for (int i = 0; i < playerNum; i++) {
            String p = PlayerContainer.getInstance().getPlayer(i).toString();
            players[i] = icons.get(p);
            activePlayers[i] = icons.get(p+ "-a");
        }

        //loading elements on Tile (Items+Player) Icons
        /*for (int i = 0 ; i < 6; i++) { // TODO: null-t ad vissza az icons.get() valamiért, egyenlőre kikommenteltem, valaki pls nézzen rá hátha tudja miert
            for (int j = 0; j < 6; j++) {
                Tile t =PositionLUT.getTile(i, j);
                for (Item it : PositionLUT.getItemOnTile(t)) {
                    System.out.println(it);
                    System.out.println(icons.get(it.toString()));
                    elements[i][j].add(icons.get(it.toString())); // HIBÁS TODO*
                }
                for (Player p : PositionLUT.getPlayersOnTile(t)) {
                    System.out.println(icons.get(p));
                    elements[i][j].add(icons.get(p.toString())); // HIBÁS //TODO*
                }
            }
        }*/


        //loading bodyHeats for all players => for PlayerBar (right-upper corner)
        for (int i = 0; i < playerNum; i++) {
            bodyHeats[i] = PlayerContainer.getInstance().getPlayer(i).getBodyHeat();
        }
        //loading the handItem Icons for all players => for PlayerBar (right-upper corner)
        for (int i = 0; i < playerNum; i++) {
            ArrayList<Item> items = PlayerContainer.getInstance().getPlayer(i).getItemsOnHand();
            for(Item it : items) {
                hand[i].add(icons.get(it.toString()));
            }
        }

    }

    private void initIcons(int playerNum) {
        //loading players
        //in runtime revealed if a player is eskimo or researcher
        for (int i = 0; i < playerNum; i++) {
            String p = PlayerContainer.getInstance().getPlayer(i).toString();
            icons.put(p, new DrawingGUI(p ));
            icons.put(p +"-a", new DrawingGUI(p + "-a"));
        }

        //loading polarbear
        icons.put("P", new DrawingGUI("P"));
        icons.put("P-a", new DrawingGUI("P-a"));

        //loading items
        icons.put("divingSuit", new DrawingGUI("divingSuit"));
        icons.put("divingSuitF", new DrawingGUI("divingSuitF"));
        icons.put("food", new DrawingGUI("food"));
        icons.put("foodF", new DrawingGUI("foodF"));
        icons.put("fragileShovel", new DrawingGUI("fragileShovel"));
        icons.put("fragileShovelF", new DrawingGUI("fragileShovelF"));
        icons.put("signalflarePart1", new DrawingGUI("signalflarePart1"));
        icons.put("signalflarePart1F", new DrawingGUI("signalflarePart1F"));
        icons.put("signalflarePart2", new DrawingGUI("signalflarePart2"));
        icons.put("signalflarePart2F", new DrawingGUI("signalflarePart2F"));
        icons.put("signalflarePart3", new DrawingGUI("signalflarePart0"));
        icons.put("signalflarePart3F", new DrawingGUI("signalflarePart0F"));
        icons.put("rope", new DrawingGUI("rope"));
        icons.put("ropeF", new DrawingGUI("ropeF"));
        icons.put("shovel", new DrawingGUI("shovel"));
        icons.put("shovelF", new DrawingGUI("shovelF"));

        //other actions
        icons.put("capacity", new DrawingGUI("capacity"));
        icons.put("clearSnow", new DrawingGUI("clearSnow"));
        icons.put("digUp", new DrawingGUI("digUp"));
        icons.put("igloo", new DrawingGUI("igloo"));
        icons.put("passRound", new DrawingGUI("passRound"));
        icons.put("pickUp", new DrawingGUI("pickUp"));
        icons.put("tent", new DrawingGUI("tent"));

        //loading tiles
        icons.put("tileNoSnow", new DrawingGUI("tileNoSnow"));
        icons.put("tileSnow", new DrawingGUI("tileSnow"));
        icons.put("tileWater", new DrawingGUI("tileWater"));
        //other icons
        icons.put("snow", new DrawingGUI("snow"));
        icons.put("inWater", new DrawingGUI("inWater"));
        icons.put("heart", new DrawingGUI("heart"));
        icons.put("warning", new DrawingGUI("warning"));
        icons.put("workingPoints", new DrawingGUI("workingPoints"));
    }


    static GamePanel gamePanel = null;
    static Menu menu = new Menu();
    InGame(int playersCount){
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 850);
        //TODO PLAYERCONTAINER INIT (MENÜ-be lett áttéve mert nullptr ex.)
        this.getContentPane().add(menu);
        setVisible(true);
    }

    public void changeScreen(){
        System.out.println("h3ge");
        this.getContentPane().removeAll();
        //frame.getContentPane().add(new JPanel());
        gamePanel = new GamePanel();
        add(gamePanel);
        gamePanel.refreshComponents();
        revalidate();
    }
    public static void main(String[] args){
        inGame= new InGame(6);
    }
}
