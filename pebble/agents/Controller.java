/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pebble.agents;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import pebble.Game;

/**
 * Controller allow players to move their pebbles
 * @author Nguyen Kim Hai, Bui
 */
public class Controller extends JDialog {
    private final JButton[] buttons = new JButton[5];
    private int i = -1;
    private int j = -1;
    
    /**
     * Set the current pebble indices which is under control of the controller
     * @param i row index
     * @param j column index
     */
    public void setIJ(int i, int j) {
        this.i = i;
        this.j = j;
    }
    
    /**
     * Create a new controller on a owner (Game)
     * @param owner 
     */
    public Controller(JFrame owner) {
        super(owner, "Choose a direction", true);
        setSize(200, 200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);
        
        Game game = (Game) owner;
        
        buttons[0] = new JButton("North");
        buttons[1] = new JButton("West");
        buttons[2] = new JButton("X");
        buttons[3] = new JButton("East");
        buttons[4] = new JButton("South");
        
        for (JButton button : buttons) {
            button.addActionListener(e -> {
                JButton btn = (JButton) e.getSource();

                if (!btn.getText().equals("X")) {
                    game.pushForward(i, j, btn.getText());

                    // Move to next player turn
                    game.model.nextTurn();
                    game.updateLabelText();

                    // Check if game is Finished
                    game.showMsgIfFinished(game.model);
                }

                // Close the controller window
                dispose();
            });
        }
        
        // Add buttons to the dialog
        add(buttons[0], BorderLayout.NORTH);
        add(buttons[1], BorderLayout.WEST);
        add(buttons[2], BorderLayout.CENTER);
        add(buttons[3], BorderLayout.EAST);
        add(buttons[4], BorderLayout.SOUTH);
    }
}
