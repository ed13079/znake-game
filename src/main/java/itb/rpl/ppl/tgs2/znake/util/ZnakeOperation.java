/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.util;

import itb.rpl.ppl.tgs2.znake.util.ZnakeConstants;
import itb.rpl.ppl.tgs2.znake.controller.ZnakeController;
import itb.rpl.ppl.tgs2.znake.model.player.Player;
import itb.rpl.ppl.tgs2.znake.model.snake.Znake;
import itb.rpl.ppl.tgs2.znake.model.snake.ZnakeBodyPart;
import java.awt.Point;

/**
 *
 * @author edbert
 */
public class ZnakeOperation {
    
    private final ZnakeController engine;
    private Znake znake = Znake.getInstance();
    private Player playerObj = Player.getInstance();
    
    public ZnakeOperation(ZnakeController controller) {
        this.engine = controller;
    }
    
    public void plusScore(int score) {
        playerObj.addScore(score);
    }
    
    public void increaseSpeed() {
        znake.setSpeed(Znake.getInstance().getSpeed()-ZnakeConstants.DELTA_SPEED);
    }
    
    public void decreaseSpeed() {
        znake.setSpeed(Znake.getInstance().getSpeed()+ZnakeConstants.DELTA_SPEED);
    }
    
    public void addBody() {
        znake.grow();
    }
    
    public void subBody() {
        znake.diet();
    }
    
    public void reverseDirection() {
        znake.reverseDirection();
    }
    
    public void clearEffect() {
        znake.clearEffect();
    }
    
    public void moveUp() {
        if (znake.getDirection() != ZnakeConstants.NORTH && 
            znake.getDirection() != ZnakeConstants.SOUTH) {
            
            if (znake.isConfuse()) znake.setDirection(ZnakeConstants.SOUTH);
            else znake.setDirection(ZnakeConstants.NORTH);
            
        }
    }
    
    public void moveDown() {
        if (znake.getDirection() != ZnakeConstants.NORTH &&
            znake.getDirection() != ZnakeConstants.SOUTH) {
            if (znake.isConfuse()) znake.setDirection(ZnakeConstants.NORTH);
            else znake.setDirection(ZnakeConstants.SOUTH);
        }
    }
    
    public void moveLeft() {
        if (znake.getDirection() != ZnakeConstants.EAST &&
            znake.getDirection() != ZnakeConstants.WEST) {
            if (znake.isConfuse()) znake.setDirection(ZnakeConstants.EAST);
            else znake.setDirection(ZnakeConstants.WEST);
        }
    }
    
    public void moveRight() {
        if (znake.getDirection() != ZnakeConstants.EAST &&
            znake.getDirection() != ZnakeConstants.WEST) {
            if (znake.isConfuse()) znake.setDirection(ZnakeConstants.WEST);
            else znake.setDirection(ZnakeConstants.EAST);
        }
    }
    
}
