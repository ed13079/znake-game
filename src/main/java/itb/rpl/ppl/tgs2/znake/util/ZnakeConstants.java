/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.util;

/**
 *
 * @author : 
 */
public final class ZnakeConstants {
    
    // Board
    public static final int BOARD_WIDTH = 50;
    public static final int BOARD_HEIGHT = 25;
    public static final int CELL_WIDTH = 10;
    public static final int CELL_HEIGHT = 10;
    
    // Znake
    public static final int DEFAULT_SNAKE_LENGTH = 4;
    public static final int DEFAULT_SPEED_1 = 500;
    public static final int DEFAULT_SCORE = 5;
    public static final int DEFAULT_EXTRA_SCORE = 10;
    public static final int DEFAULT_EXTRA_TIME_REMAINING = 10000; // in miliseconds
    public static final int INIT_POS_X = 7;
    public static final int INIT_POS_Y = 7; 
    
    // Direction
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    
    // efect type
    public static final String NORMAL_EFFECT = "Normal effect";
    public static final String INCREASE_SPEED_EFFECT = "Increase Speed effect";
    public static final String DECREASE_SPEED_EFFECT = "Decrease Speed effect";
    public static final String SUB_BODY_EFFECT = "Sub Body effect";
    public static final String ADD_BODY_EFFECT = "Add Body effect";
    public static final String REVERSE_DIRECTION_EFFECT = "Reverse direction effect";
    
    // foodName
    public static final String DEFAULT_FOOD = "Default Food";
    public static final String EXTRA_FOOD = "Extra Food";
    
}
