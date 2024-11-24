/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pebble.agents;

/**
 * This class represent the Play Object
 * @author Nguyen Kim Hai, Bui
 */
public class Player {
    /**
     * player's name
     */
    public final String name;
    
    /**
     * player's color
     */
    public final int color;
    
    /**
     * Init the Player with given name and his Pebble color
     * @param name username
     * @param color pebble's color
     */
    public Player(String name, int color) {
        this.name = name;
        this.color = color;
    }
}
