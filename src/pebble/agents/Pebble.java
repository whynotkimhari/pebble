/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pebble.agents;

import javax.swing.JButton;

/**
 * UI of pebble, self-awareness of its position
 * @author Nguyen Kim Hai, Bui
 */
public class Pebble extends JButton {
    /**
     * pebble's row index
     */
    public final int i;
    
    /**
     * pebble's column index
     */
    public final int j;
    
    /**
     * Create a Pebble at a given position
     * @param i row index
     * @param j column index
     */
    public Pebble(int i, int j) {
        super();
        this.i = i;
        this.j = j;
    }
}
