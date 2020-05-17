package GUI;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import ItemClasses.Item;
import ItemClasses.ItemState;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;
import PlayerClasses.PolarBear;
import TileClasses.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel {

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
    }

    private void initIcons(int playerNum) {
        //loading players
        //in runtime revealed if a player is eskimo or researcher
        for (int i = 0; i < playerNum; i++) {
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
        icons.put("tileNoSnow", new DrawingGUI("ice"));
        icons.put("tileSnow", new DrawingGUI("ice"));
        icons.put("tileWater", new DrawingGUI("ice"));
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

        //loading tiles from positionLUT
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                DrawingGUI dgui = new DrawingGUI((PositionLUT.getTile(x, y).toString()));
                try {
                    dgui.texture = ImageIO.read(new File("src/GUI/Pack/ice"));
                } catch (IOException e) {
                }
                dgui.x = tileCorner + x * (tileSize + 1);
                dgui.y = tileCorner + y * (tileSize + 1);
                dgui.width = tileSize;
                dgui.height = tileSize;
                components.add(dgui);
            }
        }

        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                ArrayList<Player> players = PositionLUT.getPlayersOnTile(PositionLUT.getTile(x, y));
                for (Player p : players) {
                    DrawingGUI dgui = new DrawingGUI(p.toString());
                    try {
                        dgui.texture = ImageIO.read(new File("src/GUI/Pack/" + p.toString()));
                    } catch (IOException e) {
                    }
                    dgui.x = tileCorner + x * (tileSize + 1)+5;
                    dgui.y = tileCorner + y * (tileSize + 1)+70;
                    dgui.width = 25;
                    dgui.height = 25;
                    components.add(dgui);
                }
            }
        }

        //loading items from positionLUT
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                ArrayList<Item> iList = PositionLUT.getItemOnTile(PositionLUT.getTile(x, y));
                for (Item item : iList) {
                    if (item.getState() == ItemState.thrownDown) {
                        DrawingGUI dgui = new DrawingGUI(item.toString()); //lehet így, nem kell iconsokat hasznalni
                        dgui.x = tileCorner + x * (tileSize + 1) + 65;
                        dgui.y = tileCorner + y * (tileSize + 1) + 65;
                        dgui.width = 30;
                        dgui.height = 30;
                        components.add(dgui);
                    }
                }
            }
        }

        Tile tile = PositionLUT.getInstance().getPosition(RoundController.getInstance().polarbear);
        int x= tile.getX();
        int y = tile.getY();
        DrawingGUI polargui = new DrawingGUI(RoundController.getInstance().polarbear.toString()); //lehet így, nem kell iconsokat hasznalni
        polargui.x = tileCorner + x * (tileSize + 1) + 25;
        polargui.y = tileCorner + y * (tileSize + 1) + 25;
        polargui.width = 50;
        polargui.height = 50;
        components.add(polargui);


        //loop initializes player details
        int firstPlayerX = 2*margin + 5*tilePadding +6*tileSize;
        for(int i = 0; i <PlayerContainer.getInstance().getPlayerNum(); i++ ){
            DrawingGUI dgui = new DrawingGUI(PlayerContainer.getInstance().getPlayer(i).toString());
            dgui.x = firstPlayerX + (i%3)*playerBoxX;
            dgui.y = margin + (i/3)*(playerBoxY + playerPadding);
            dgui.width = 50;
            dgui.height = 50;
            components.add(dgui);

            DrawingGUI heartgui = new DrawingGUI("heart");
            heartgui.x = firstPlayerX + (i%3)*playerBoxX + 70;
            heartgui.y = margin + (i/3)*(playerBoxY + playerPadding);
            heartgui.width = 30;
            heartgui.height = 30;
            components.add(heartgui);

            JLabel label = new JLabel(Integer.toString(PlayerContainer.getInstance().getPlayer(i).getBodyHeat()));
            add(label);
            label.setSize(30,30);
            label.setLocation(heartgui.x+40,heartgui.y);



            if(PlayerContainer.getInstance().getPlayer(i).getItemsOnHand().size() == 1){
                DrawingGUI itemgui = new DrawingGUI(PlayerContainer.getInstance().getPlayer(i).getItemsOnHand().get(0).toString());
                itemgui.x = firstPlayerX + (i%3)*playerBoxX + 70;
                itemgui.y = margin + (i/3)*(playerBoxY + playerPadding) + 35;
                itemgui.width = 30;
                itemgui.height = 30;
                components.add(itemgui);
            }else if(PlayerContainer.getInstance().getPlayer(i).getItemsOnHand().size() == 2){
                DrawingGUI itemgui = new DrawingGUI(PlayerContainer.getInstance().getPlayer(i).getItemsOnHand().get(0).toString());
                itemgui.x = firstPlayerX + (i%3)*playerBoxX + 70;
                itemgui.y = margin + (i/3)*(playerBoxY + playerPadding) + 35;
                itemgui.width = 30;
                itemgui.height = 30;
                components.add(itemgui);

                DrawingGUI divingsuitgui = new DrawingGUI(PlayerContainer.getInstance().getPlayer(i).getItemsOnHand().get(1).toString());
                divingsuitgui.x = firstPlayerX + (i%3)*playerBoxX + 105;
                divingsuitgui.y = margin + (i/3)*(playerBoxY + playerPadding) + 35;
                divingsuitgui.width = 30;
                divingsuitgui.height = 30;
                components.add(divingsuitgui);
            }

        }

        Player activePlayer = PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
        DrawingGUI dguiP = new DrawingGUI(activePlayer.toString()+"-a");
        dguiP.x = 7*tileSize;
        dguiP.y = 5*tileSize+50;
        dguiP.width = 100;
        dguiP.height = 100;
        components.add(dguiP);

        DrawingGUI dguiS = new DrawingGUI("snow");
        dguiS.x = 8*tileSize+15;
        dguiS.y = 5*tileSize+70;
        dguiS.width = 30;
        dguiS.height = 30;
        components.add(dguiS);

        DrawingGUI dguiW = new DrawingGUI("workingPoints");
        dguiW.x = 8*tileSize+10;
        dguiW.y = 6*tileSize+10;
        dguiW.width = 40;
        dguiW.height = 40;
        components.add(dguiW);

        Label workingPointsLabel= new Label(Integer.toString(activePlayer.workPoints));
        this.add(workingPointsLabel);
        workingPointsLabel.setLocation(8*tileSize+60,6*tileSize+15);
        workingPointsLabel.setSize(30,30);
        workingPointsLabel.setFont(new Font("Serif", Font.PLAIN, 34));


        Label snowLabel= new Label("-");
        this.add(snowLabel);
        snowLabel.setLocation(8*tileSize+60,5*tileSize+70);
        snowLabel.setSize(30,30);
        snowLabel.setFont(new Font("Serif", Font.PLAIN, 34));


    }


    public void paintComponent (Graphics g)
    {
        refreshComponents();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage image = null;//new ImageIcon("GUI/Pack/start.jpg");
        try {
            image = ImageIO.read(new File("src/GUI/Pack/start.jpg"));
        } catch (IOException e) {
        }
        for (DrawingGUI dg:components) {
            g2d.drawImage(dg.texture, dg.x, dg.y, dg.width, dg.height, null);
        }
        setVisible(true);

    }

}
