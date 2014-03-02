/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake;

/**
 *
 * @author edbert
 */
public interface ZnakeConstants {
    
    // Board
    static final int BOARD_WIDTH = 50;
    static final int BOARD_HEIGHT = 25;
    static final int CELL_WIDTH = 10;
    static final int CELL_HEIGHT = 10;
    
    // Znake
    static final int DEFAULT_SNAKE_LENGTH = 5;
    static final int DEFAULT_SPEED_1 = 500;
    static final int DEFAULT_SCORE = 5;
    static final int DEFAULT_EXTRA_TIME_REMAINING = 10000; // in miliseconds
    
    // Direction
    static final int NORTH = 0;
    static final int EAST = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;
    
}
