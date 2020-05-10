package GUI;

import PlayerClasses.PlayerContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu  {
    JPanel menuPanel = new JPanel(new GridBagLayout());
    JButton minus, plus, startGame;
    JLabel numLabel;
    Integer number;

    Menu(){
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
            }
        });
        menuPanel.add(minus);
        menuPanel.add(numLabel);
        menuPanel.add(plus);
        menuPanel.add(startGame);
        menuPanel.setVisible(true);
    }


}
