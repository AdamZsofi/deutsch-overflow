package GUI;

import GlobalControllers.PositionLUT;
import PlayerClasses.PlayerContainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel  {
    JButton minus, plus, startGame;
    JLabel numLabel;
    Integer number;

    Menu(){
        setLayout(new GridBagLayout());
        number = 4;
        numLabel = new JLabel(number.toString());
        minus = new JButton("-");
        minus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(number > 3) {
                    numLabel.setText((--number).toString());
                    if(number == 5) plus.validate();
                }
                else minus.invalidate();
            }
        });
        plus = new JButton("+");
        plus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(number < 6) {
                    numLabel.setText((++number).toString());
                    if(number == 4) minus.validate();
                }
                else plus.invalidate();
            }
        });
        startGame = new JButton("startGame");
        startGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerContainer.Initialize(number, 3);
                PositionLUT.getInstance().randInit();
                InGame.getInstance().initComponents(Integer.parseInt(numLabel.getText()));
                InGame.getInstance().changeScreen();
            }
        });
        add(minus);
        add(numLabel);
        add(plus);
        add(startGame);
        minus.setLocation(500,300);
        minus.setSize(50,50);
        Image img = null;
        try {
            img = ImageIO.read(new File("src\\GUI\\Pack\\ice.png")); // átmenetileg, ha lesz hozzá kép annak a path-ja mehet helyette
        } catch (IOException e) {
            e.printStackTrace();
        }
        minus.setIcon(new ImageIcon(img));
        plus.setLocation(600,300);
        plus.setSize(50,50);
        startGame.setLocation(500,350);
        startGame.setSize(150,80);
        numLabel.setLocation(565,300);
        numLabel.setSize(50,50);
        numLabel.setFont(new Font("Serif", Font.PLAIN, 34));
        startGame.setFont(new Font("Serif", Font.PLAIN, 24));
        plus.setFont(new Font("Serif", Font.PLAIN, 27)); // majd kép lesz helyette csak még nincs kép (+,- ról)
        setVisible(true);

    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage image = null;//new ImageIcon("GUI/Pack/start.jpg");
        try {
            image = ImageIO.read(new File("src\\GUI\\Pack\\start.jpg"));
        } catch (IOException e) {
        }
        g2d.drawImage(image, 0, 0,null);
        g2d.drawImage(image, 0, 0,500,600,null);
    }

}
