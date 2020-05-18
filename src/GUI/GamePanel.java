package GUI;

import Control.Game;
import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import ItemClasses.Item;
import ItemClasses.ItemState;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;
import TileClasses.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel {

    public static boolean capacityEnabled = false;
    public static int capacotyOnTile;
    public static int capacotyX;
    public static int capacotyY;
    int panelHeight = getHeight();
    int panelWidth = getWidth();

    int tileCorner = 50;
    int tileSize = 100;
    int tilePadding = 5;
    int margin = 50;
    int playerPadding = 10;
    int playerBoxX = 200;
    int playerBoxY = 100;
   //int messageBox = ;
   //int messageBoxY = ;


    private Map<String, DrawingGUI> icons = new HashMap<String, DrawingGUI>();
    /**
     * collection of components from positionLUT
     */
    ArrayList<DrawingGUI> components = new ArrayList<>();

    GamePanel(){
        setLayout(new GridBagLayout());
        initIcons();
    }

    private void initIcons() {
        //loading players
        //in runtime revealed if a player is eskimo or researcher
        for (int i = 0; i < PlayerContainer.getInstance().getPlayerNum(); i++) {
            String p = PlayerContainer.getInstance().getPlayer(i).toString();
            icons.put(p, new DrawingGUI(p));
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
        icons.put("signalflarePart0", new DrawingGUI("signalflarePart0"));
        icons.put("signalflarePart0F", new DrawingGUI("signalflarePart0F"));
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


    /**
     * clears former list elements
     * fills up components from positionLUT
     *
     */
    void refreshComponents() {
        components.clear();
        removeAll();
        //revalidate();
        i++;
        System.out.println(i.toString());

        //loading tiles from positionLUT
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                DrawingGUI dgui = icons.get(PositionLUT.getTile(x, y).toString())
                        .getImage(tileCorner + x * (tileSize + 1), tileCorner + y * (tileSize + 1), tileSize);

                components.add(dgui);
            }
        }

        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                ArrayList<Player> players = PositionLUT.getPlayersOnTile(PositionLUT.getTile(x, y));
                int count=0;
                int count2=0;
                for (Player p : players) {
                    DrawingGUI dgui = icons.get(p.toString())
                            .getImage(tileCorner + x * (tileSize + 1)+5+count2*25, tileCorner + y * (tileSize + 1)+70-count*30, 25);
                    components.add(dgui);
                        count++;
                    if(count%3==0){
                        count2=1;
                        count=0;
                    }
                }
            }
        }

        //loading items from positionLUT
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                ArrayList<Item> iList = PositionLUT.getItemOnTile(PositionLUT.getTile(x, y));
                for (Item item : iList) {
                    //if ((item.getState() == ItemState.thrownDown || item.getState() == ItemState.frozen) && PositionLUT.getTile(x, y).getSnow() == 0 ) {
                        DrawingGUI dgui = icons.get(item.toString())
                                        .getImage(tileCorner + x * (tileSize + 1) + 65, tileCorner + y * (tileSize + 1) + 65, 30);
                        components.add(dgui);
                    //}
                }
            }
        }

        //loading shelters
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                if(PositionLUT.getTile(x,y).getIglooOn()){
                    DrawingGUI dgui = icons.get("igloo")
                            .getImage(tileCorner + x * (tileSize + 1) +tileSize/4, tileCorner + y * (tileSize + 1) + tileSize/4, tileSize/2);
                    components.add(dgui);
                }else if(PositionLUT.getTile(x,y).tentOn){
                    DrawingGUI dgui = icons.get("tent")
                            .getImage(tileCorner + x * (tileSize + 1) + tileSize/4, tileCorner + y * (tileSize + 1) + tileSize/4, tileSize/2);
                    components.add(dgui);
                }
            }
        }

        Tile tile = PositionLUT.getInstance().getPosition(RoundController.getInstance().polarbear);
        int x= tile.getX();
        int y = tile.getY();
        DrawingGUI polargui = icons.get(RoundController.getInstance().polarbear.toString())
                .getImage(tileCorner + x * (tileSize + 1) + 25, tileCorner + y * (tileSize + 1) + 25, 50);
        components.add(polargui);


        //loop initializes player details
        int firstPlayerX = 2*margin + 5*tilePadding +6*tileSize;
        for(int i = 0; i <PlayerContainer.getInstance().getPlayerNum(); i++ ){
            DrawingGUI dgui = icons.get(PlayerContainer.getInstance().getPlayer(i).toString())
                    .getImage(firstPlayerX + (i%3)*playerBoxX, margin + (i/3)*(playerBoxY + playerPadding), 50);
            components.add(dgui);

            DrawingGUI heartgui = icons.get("heart")
                    .getImage(firstPlayerX + (i%3)*playerBoxX + 70, margin + (i/3)*(playerBoxY + playerPadding), 30);
            components.add(heartgui);

            Label label = new Label(Integer.toString(PlayerContainer.getInstance().getPlayer(i).getBodyHeat()));
            this.add(label);
            label.setSize(30,30);
            label.setLocation(heartgui.x+40,heartgui.y);



            if(PlayerContainer.getInstance().getPlayer(i).getItemsOnHand().size() == 1){
                DrawingGUI itemgui = icons.get(PlayerContainer.getInstance().getPlayer(i).getItemsOnHand().get(0).toString())
                        .getImage(firstPlayerX + (i%3)*playerBoxX + 70, margin + (i/3)*(playerBoxY + playerPadding) + 35, 30);
                components.add(itemgui);
            }else if(PlayerContainer.getInstance().getPlayer(i).getItemsOnHand().size() == 2){
                DrawingGUI itemgui = icons.get(PlayerContainer.getInstance().getPlayer(i).getItemsOnHand().get(0).toString())
                        .getImage(firstPlayerX + (i%3)*playerBoxX + 70, margin + (i/3)*(playerBoxY + playerPadding) + 35, 30);
                components.add(itemgui);

                DrawingGUI divingsuitgui = icons.get(PlayerContainer.getInstance().getPlayer(i).getItemsOnHand().get(1).toString())
                        .getImage(firstPlayerX + (i%3)*playerBoxX + 105, margin + (i/3)*(playerBoxY + playerPadding) + 35, 30);
                components.add(divingsuitgui);
            }

        }

        Player activePlayer = PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
        DrawingGUI dguiP = icons.get(activePlayer.toString()+"-a").getImage(7*tileSize, 5*tileSize+50, 100);
        components.add(dguiP);

        DrawingGUI dguiS = icons.get("snow").getImage(8*tileSize+15, 5*tileSize+70, 30);
        components.add(dguiS);

        DrawingGUI dguiW = icons.get("workingPoints").getImage(8*tileSize+10, 6*tileSize+10, 40);
        components.add(dguiW);

        DrawingGUI capacitygui = icons.get("capacity").getImage(8*tileSize+15, 5*tileSize, 30);
        components.add(capacitygui);

        Label workingPointsLabel= new Label(Integer.toString(activePlayer.workPoints));
        this.add(workingPointsLabel);
        workingPointsLabel.setLocation(8*tileSize+60,6*tileSize+15);
        workingPointsLabel.setSize(30,30);
        workingPointsLabel.setFont(new Font("Serif", Font.PLAIN, 34));


        Label snowLabel= new Label(Integer.toString(PositionLUT.getPosition(activePlayer).standingHere));
        this.add(snowLabel);
        snowLabel.setLocation(8*tileSize+60,5*tileSize+70);
        snowLabel.setSize(30,30);
        snowLabel.setFont(new Font("Serif", Font.PLAIN, 34));

        String capacity = "-";
        if(capacityEnabled){
            capacity = "Capacity on Tile (" +Integer.toString(capacotyX) + ";" +Integer.toString(capacotyY) + " ) is "+ Integer.toString(capacotyOnTile) + ".";
        }
        Label capacityLabel= new Label(capacity);
        this.add(capacityLabel);
        capacityLabel.setLocation(8*tileSize+60,5*tileSize);
        capacityLabel.setSize(200,30);


    }

    Integer i = 0;

    @Override
    public void paint(Graphics g)
    {

        if(Game.dirty){
        refreshComponents();
        }
        Game.dirty = false;
        Graphics2D g2d = (Graphics2D) g;
        for (DrawingGUI dg : components) {
            g2d.drawImage(dg.texture, dg.x, dg.y, dg.width, dg.height, null);
        }
        System.out.println("Szia");
        setVisible(true);

    }

}
