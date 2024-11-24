/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pebble.agents;

/**
 * The backed of the game
 * @author Nguyen Kim Hai, Bui
 */
public class Table {
    private final int[][] table;
    
    /**
     * Init a new table with given size
     * @param size size
     */
    public Table(int size) {
        this.table = new int[size][size];
    }
    
    /**
     * Set the new Color at (i, j)
     * @param i row index
     * @param j column index                        
     * @param color given color BLACK: -1 / WHITE: 1
     */
    public void setColorAt(int i, int j, int color) throws IndexOutOfBoundsException, IllegalArgumentException {
        
        if (color < - 1 || color > 1)
            throw new IllegalArgumentException();
        
        table[i][j] = color;
    }
    
    public int getColorAt(int i, int j) throws IndexOutOfBoundsException {
        return table[i][j];
    }
    
    /**
     * This method counts how many given color pebbles are on the table
     * @param color BLACK: -1 / WHITE: 1
     * @return an amount of pebble with given color
     */
    public int count(int color) {
        int cnt = 0;
        for (int i = 0; i < table[0].length; i++)
            for (int j = 0; j < table[0].length; j++)
                if (table[i][j] == color)
                    ++cnt;
        
        return cnt;
    }
}
