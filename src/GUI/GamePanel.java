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

import static GUI.Size.small;

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
                DrawingGUI dgui = icons.get(PositionLUT.getTile(x, y).toString())
                        .getImage(tileCorner + x * (tileSize + 1), tileCorner + y * (tileSize + 1), tileSize);

                components.add(dgui);
            }
        }

        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                ArrayList<Player> players = PositionLUT.getPlayersOnTile(PositionLUT.getTile(x, y));
                for (Player p : players) {
                    DrawingGUI dgui = icons.get(p.toString())
                            .getImage(tileCorner + x * (tileSize + 1)+5, tileCorner + y * (tileSize + 1)+70, 25);
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
                        DrawingGUI dgui = icons.get(item.toString())
                                        .getImage(tileCorner + x * (tileSize + 1) + 65, tileCorner + y * (tileSize + 1) + 65, 30);
                        components.add(dgui);
                    }
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

            JLabel label = new JLabel(Integer.toString(PlayerContainer.getInstance().getPlayer(i).getBodyHeat()));
            add(label);
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
