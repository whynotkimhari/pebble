/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility contains helper methods to support the game logic
 * @author Nguyen Kim Hai, Bui
 */
public class Utility {
    /**
     * This method transform 2D matrix into 1D list, shuffle the list and return
     * @param size the length of the matrix
     * @return a list of indices
     */
    public static List<Integer> getRandomIndicesIn1DFormat(int size) {
        List<Integer> indices = new ArrayList<>();
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                indices.add(i * size + j);
            }
        }

        // Shuffle the list of indices
        Collections.shuffle(indices);
        return indices;
    }
    
    /**
     * This method check if the str is empty or not
     * @param str input string
     * @return a boolean
     */
    public static boolean isEmptyString(String str) {
        return str.equals("");
    }
    
    /**
     * Convert integer to color
     * @param i integer number
     * @return Color
     */
    public static Color intToColor(int i) {
        if (i == -1) return Color.BLACK;
        
        if (i == 1) return Color.WHITE;
        
        return Color.LIGHT_GRAY;
    }
    
    /**
     * Return the text representation of the color
     * @param color BLACK: -1 / WHITE: 1
     * @return a String
     */
    public static String getColorText(int color) {
        return color == -1 ? "BLACK" : "WHITE";
    }
}
