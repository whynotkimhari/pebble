/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pebble.agents;

/**
 * Model store the information of the current game
 * @author Nguyen Kim Hai, Bui
 */
public class Model {
    // These 2 fields do not need to be private
    // Their own class has restriction when assign their fields
    // So to avoid lengthy code, I will use them as public final fields
    
    /**
     * Two players
     */
    public final Player[] players;
    
    /**
     * Game table
     */
    public final Table table;
    
    /**
     * The side length of the table
     */
    public final int size;
    
    // currentPlayerID, numTurns must be private
    // but not be final sice they will change each turn
    private int currentPlayerID;
    private int numTurns;
    
    /**
     * Construct to create a new state instance. NumTurns is computed by 5 * size
     * @param size the length of the matrix game (3, 4, 6)
     * @param player1 Player 1
     * @param player2 Player 2
     */
    public Model(int size, Player player1, Player player2) {
        this.size = size;
        this.numTurns = 5 * size;
        this.players = new Player[] {player1, player2};
        this.table = new Table(size);
        this.currentPlayerID = 0;
    }

    /**
     * This method switch to the next player. Reduce number of turn left.
     */
    public void nextTurn() {
        currentPlayerID = (currentPlayerID + 1) % 2;
        --numTurns;
    }
    
    /**
     * Get the current player
     * @return Player
     */
    public Player getCurrentPlayer() {
        return players[currentPlayerID];
    }
    
    public int getNumTurns() {
        return numTurns;
    }
    
    /**
     * Get the winner
     * @return Player
     */
    public Player getWinner() {
        
        if (isGameFinished()) {
            final int numWhites = table.count(1);
            final int numBlacks = table.count(-1);
            
            if (numWhites > numBlacks) return players[0];
            
            if (numWhites < numBlacks) return players[1];
        }
        
        return null;
    }

    /**
     * Check if game is finished
     * @return boolean
     */
    public boolean isGameFinished() {
        final int numWhites = table.count(1);
        final int numBlacks = table.count(-1);
        
        return numTurns == 0 || numWhites == 0 || numBlacks == 0;
    }
}
