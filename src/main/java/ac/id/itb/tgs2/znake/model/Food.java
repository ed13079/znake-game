/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.model;

import java.awt.Point;

/**
 *
 * @author Edbert
 */
public abstract class Food implements ZnakeElement {
    
    protected Point position;
    
    public Food() {
    }
    
    @Override
    public Point getPosition() {
        return position;
    }
    
    public void setPosition(Point position) {
        this.position = position;
    }
    
    public int getScore() {
        return getEffect().getScore();
    }
    
    public abstract Effect getEffect();
    public abstract String getName();
    
    @Override
    public String toString() {
        return "Food { Name: " + getName() + ", Score: " + getScore() + " }";
    }
}
