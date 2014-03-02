/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.util;

import itb.rpl.ppl.tgs2.znake.util.ZnakeConstants;
import itb.rpl.ppl.tgs2.znake.controller.ZnakeController;
import itb.rpl.ppl.tgs2.znake.model.snake.Znake;

/**
 *
 * @author edbert
 */
public class ZnakeOperation {
    
    private final ZnakeController engine;
    
    public ZnakeOperation(ZnakeController controller) {
        this.engine = controller;
    }
    
    void plusScore(int score) {
        
    }
    
    void increaseSpeed(int speed) {
        
    }
    
    void decreaseSpeed(int speed) {
        
    }
    
    public void moveUp() {
        if (engine.getDirection() != ZnakeConstants.NORTH && 
            engine.getDirection() != ZnakeConstants.SOUTH) {
            engine.setDirection(ZnakeConstants.NORTH);
        }
    }
    
    public void moveDown() {
        if (engine.getDirection() != ZnakeConstants.NORTH &&
            engine.getDirection() != ZnakeConstants.SOUTH) {
            engine.setDirection(ZnakeConstants.SOUTH);
        }
    }
    
    public void moveLeft() {
        if (engine.getDirection() != ZnakeConstants.EAST &&
            engine.getDirection() != ZnakeConstants.WEST) {
            engine.setDirection(ZnakeConstants.WEST);
        }
    }
    
    public void moveRight() {
        if (engine.getDirection() != ZnakeConstants.EAST &&
            engine.getDirection() != ZnakeConstants.WEST) {
            engine.setDirection(ZnakeConstants.EAST);
        }
    }
    
}
