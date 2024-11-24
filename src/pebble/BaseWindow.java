/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pebble;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 * This is the template window class
 * @author Nguyen Kim Hai, Bui
 */
public abstract class BaseWindow extends JFrame {
    
    /**
     * The constructor initial a centered square window whose side length is half of the screen width size
     */
    protected BaseWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setTitle("Pebble");
        setSize(screenSize.width / 2, screenSize.width / 2);

        // center the jframe on screen
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }
        });
    }
    
    /**
     * Close the window
     */
    protected void doUponExit() {
        this.dispose();
    }
    
    /**
     * The Window will ask for the confirmation when user want to close it
     */
    private void showExitConfirmation() {
        int userOption = JOptionPane.showConfirmDialog(
            this, 
            "Do you really want to quit?",
            "Really?", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (userOption == JOptionPane.YES_OPTION)
            doUponExit();
        
    }
}
