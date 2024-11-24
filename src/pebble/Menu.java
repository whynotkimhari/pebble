/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pebble;

import pebble.agents.Model;
import pebble.agents.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import static utils.Utility.isEmptyString;

/**
 * The Menu of the game
 * @author Nguyen Kim Hai, Bui
 */

public class Menu extends BaseWindow {
    private final List<Game> games = new ArrayList<>();
        
    /**
     * Create the menu window for the game with Welcome message, Input boxes and level Buttons
     */
    public Menu() {
        getContentPane().setLayout(new GridLayout(4, 1));
        getContentPane().add(createWelcomePanel());
        getContentPane().add(createInputPanel("Player 1"));
        getContentPane().add(createInputPanel("Player 2"));
        getContentPane().add(createGameLevelPanel());
    }
    
    /**
     * Create the welcome Panel
     * @return JPanel
     */
    private JPanel createWelcomePanel() {
        StringBuilder sb = new StringBuilder();
        sb
            .append("<html>")
                .append("<div align='center'>")
                    .append("<h1>PEBBLE</h1>")
                    .append("<p>Hi players! Welcome to Pebble!</p>")
                    .append("<p>Please type your names, choose a level, and ready to play!</p>")
                    .append("<h2>Note</h2>")
                    .append("<p>Player1: White Pebble</p>")
                    .append("<p>Player2: Black Pebble</p>")
                .append("</div>")
            .append("</html>");
        
        JPanel welcomePanel = new JPanel();
        welcomePanel.add(new JLabel(sb.toString()));
        return welcomePanel;
    }
    
    /**
     * Create the input Panel
     * @param title the title for the input
     * @return JPanel
     */
    private JPanel createInputPanel(String title) {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel titleLabel = new JLabel(title + "'s name: ");
        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        
        JTextField input = new JTextField(20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(titleLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(input, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(errorLabel, gbc);
        
        return inputPanel;
    }
    
    /**
     * Check if the input from user is good
     * @param id the id of input panel
     * @return pair of Boolean and String
     */
    private Map.Entry<Boolean, String> isGoodInput(int id) {
        JPanel inputPanel = (JPanel) getContentPane().getComponent(id);
        JTextField input = (JTextField) inputPanel.getComponent(1);
        JLabel error = (JLabel) inputPanel.getComponent(2);
        String value = input.getText().strip();
        
        Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);
        Border defaultBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        
        if (isEmptyString(value)) {
            input.setBorder(redBorder);
            error.setText("The name can not be an empty string!");
            
            return Map.entry(false, value);
        }

        else if (value.length() > 6) {
            input.setBorder(redBorder);
            error.setText("The name must have maximum 6 characters!");
            
            return Map.entry(false, value);
        }

        else {
            input.setBorder(defaultBorder);
            error.setText("");
            
            return Map.entry(true, value);
        }
    }
    
    /**
     * Create the level button with given size
     * @param size the size of the game: 3, 4, 6
     * @return JButton
     */
    private JButton createLevelButton(int size) {
        JButton button = new JButton();
        button.setText("%d x %d".formatted(size, size));
        button.setPreferredSize(new Dimension(150, 50));
        
        button.addActionListener((ActionEvent e) -> {
            Map.Entry<Boolean, String> result1 = isGoodInput(1);
            Map.Entry<Boolean, String> result2 = isGoodInput(2);
                        
            if (!result1.getKey() || !result2.getKey()) return;
            
            Game window = new Game(
                Menu.this,
                new Model(
                    size, 
                    new Player(result1.getValue(), 1),
                    new Player(result2.getValue(), -1)
                )
            );
            
            window.setVisible(true);
            games.add(window);
        });
        return button;
    }
    
    /**
     * Create the panel contains level buttons
     * @return JPanel
     */
    private JPanel createGameLevelPanel() {
        JPanel gameLevelPanel = new JPanel(new FlowLayout());
        
        gameLevelPanel.add(createLevelButton(3));
        gameLevelPanel.add(createLevelButton(4));
        gameLevelPanel.add(createLevelButton(6));
        
        return gameLevelPanel;
    }
    
    
    
    /**
     * Get all the current game windows
     * @return List
     */
    public List<Game> getGames() {
        return games;
    }
    
    /**
     * Terminate the program
     */
    @Override
    protected void doUponExit() {
        System.exit(0);
    }

}
