/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pebble;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import pebble.agents.*;
import static utils.Utility.*;

/**
 * The Game
 * @author Nguyen Kim Hai, Bui
 */

public class Game extends BaseWindow {
    private final Menu menu;
    private final Pebble[][] pebbles;
    private final Controller controller;
    public final Model model;
    
    /**
     * Create a game with given game state over the main window
     * @param menu the main window (menu)
     * @param model the object store all the game details
     */
    public Game(Menu menu, Model model) {
        this.model = model;
        this.menu = menu;
        this.controller = new Controller(this);
        this.pebbles = new Pebble[model.size][model.size];
                        
        menu.getGames().add(this);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(createTopPanel(), BorderLayout.NORTH);
        getContentPane().add(createMainPanel(), BorderLayout.CENTER);
        updateLabelText();
    }
    
    /**
     * Create the top panel
     * @return JPanel
     */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("", SwingConstants.CENTER));
        topPanel.add(createNewGameButton());
        topPanel.setLayout(new GridLayout(1, 2));
        return topPanel;
    }
    
    /**
     * Create the main panel
     * @return JPanel
     */
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(model.size, model.size));
        
        // Get the indicies in 1D format to leverage the comparison of .contains method
        List<Integer> indices = getRandomIndicesIn1DFormat(model.size);
        List<Integer> blackIndices = indices.subList(0, model.size);
        List<Integer> whiteIndices = indices.subList(model.size, 2 * model.size);

        // Set the color for each cell
        for (int i = 0; i < model.size; ++i) {
            for (int j = 0; j < model.size; ++j) {
                int color = 0; int currentIndex = i * model.size + j;
                
                if (blackIndices.contains(currentIndex)) color = -1;
                else if (whiteIndices.contains(currentIndex)) color = 1;
                
                // Add UI
                this.pebbles[i][j] = new Pebble(i, j);
                pebbles[i][j].setBackground(intToColor(color));
                mainPanel.add(pebbles[i][j]);
                
                // Add Data
                model.table.setColorAt(i, j, color);
                
                // Event listener
                pebbles[i][j].addActionListener(e -> {
                    Pebble pebble = (Pebble) e.getSource();
                    if (model.table.getColorAt(pebble.i, pebble.j) == model.getCurrentPlayer().color) {
                        controller.setIJ(pebble.i, pebble.j);
                        controller.setVisible(true);
                    }
                });
            }
        }
        
        return mainPanel;
    }
    
    /**
     * Create a 'New Game' button
     * @return JButton
     */
    private JButton createNewGameButton() {
        JButton newGameButton = new JButton();
        newGameButton.setText("New game");
        newGameButton.addActionListener(e -> startNewGame());
        return newGameButton;
    }
    
    /**
     * Delete the current game state, close the current game window, create a new game window
     */
    private void startNewGame() {
        Game newWindow = new Game(
            menu, 
            new Model(
                model.size, 
                model.players[0], 
                model.players[1]
            )
        );
        newWindow.setVisible(true);
        
        this.dispose();
        menu.getGames().remove(this);
    }
    
    /**
     * Close the game window, delete it from the list of opened games
     */
    @Override
    protected void doUponExit() {
        super.doUponExit();
        menu.getGames().remove(this);
    }
    
    /**
     * Update the information of the game (Turn, Who has current turn and his/her color)
     */
    public void updateLabelText() {
        Player player = model.getCurrentPlayer();
        JLabel topLabel = (JLabel) ((JPanel) getContentPane().getComponent(0)).getComponent(0);
        
        topLabel.setText(
            ("<html>"
                + "<div align='center'>"
                    + "<h1>Turn: <span style='color: red;'>%d</span>/%d</h1>"
                    + "<h1>Current player: <span style='color: red;'>%s</span> [%s]</h1>"
                + "</div>"
            + "</html>").formatted(
                5 * model.size - model.getNumTurns() + 1,
                5 * model.size,
                player.name,
                getColorText(player.color)
            )
        );
    }
    
    /**
     * Logic of pushing the pebble
     * @param i pebble row index
     * @param j pebble column index
     * @param direction pushing way
     */
    public void pushForward(int i, int j, String direction) {
        int lastColor = model.table.getColorAt(i, j);
                
        // Put the current pebble out of the table
        model.table.setColorAt(i, j, 0);
        pebbles[i][j].setBackground(Color.LIGHT_GRAY);
        
        // Get the direction to move the pebble
        int dI = 0, dJ = 0;
        switch (direction) {
            case "North" -> dI = -1;
            case "South" -> dI = 1;
            case "West" -> dJ = -1;
            default -> dJ = 1;
        }

        i += dI;
        j += dJ;

        // Put the pebble into the right place
        // Move others if needed
        while (dI != 0 || dJ != 0) {
            try {
                
                int currentColor = model.table.getColorAt(i, j);
                model.table.setColorAt(i, j, lastColor);
                pebbles[i][j].setBackground(intToColor(lastColor));
                lastColor = currentColor;

                i += dI;
                j += dJ;
                
                if (currentColor == 0) break;
            }

            catch (IndexOutOfBoundsException e) {
                break;
            }
        }
    }
    
    /**
     * Create a pop-up window if the game is finished, then start a new game
     * @param model the current model
     */
    public void showMsgIfFinished(Model model) {
        if (model.isGameFinished()) {
            Player winner = model.getWinner();
            String msg = 
                winner == null ? 
                "Game is over. Nobody wins!" : 
                "Game is over. Winner: %s [%s]".formatted(winner.name, getColorText(winner.color));
                        
            JOptionPane.showMessageDialog(this, msg);
            startNewGame();
        }
    }
}
