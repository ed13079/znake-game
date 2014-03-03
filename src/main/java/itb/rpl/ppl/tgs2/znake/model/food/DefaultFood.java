/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.model.food;

import itb.rpl.ppl.tgs2.znake.util.ZnakeConstants;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author edbert
 */
public class DefaultFood implements Food {
    private Effect effect;
    private String foodName;
    private Point position;
  
    public DefaultFood() {
        this.effect = new Effect(ZnakeConstants.DEFAULT_SCORE, ZnakeConstants.NORMAL_EFFECT);
        this.foodName = ZnakeConstants.DEFAULT_FOOD;
        // random position 
        this.position= new Point((int)(Math.random() * ZnakeConstants.BOARD_WIDTH), // value random for x (beetween 0 and limit max board WIDTH)
                             (int)(Math.random() * ZnakeConstants.BOARD_HEIGHT)); // value random for y (beetween 0 and limit max board HEIGHT)
    }
    
    @Override
    public Effect getEffect() {
        return effect;
    }
    
    @Override
    public String getFoodName() {
        return foodName;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.effect != null ? this.effect.hashCode() : 0);
        hash = 23 * hash + (this.foodName != null ? this.foodName.hashCode() : 0);
        hash = 23 * hash + (this.position != null ? this.position.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultFood other = (DefaultFood) obj;
        return true;
    }
    
    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }
}
