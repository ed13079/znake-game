/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.model;

import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Edbert
 */
public abstract class Food extends JPanel implements ZnakeElement {
    
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
    public abstract String getFoodName();
    
//    @Override
//    public String toString() {
//        return "Food { Name: " + getFoodName() + ", Score: " + getScore() + " }";
//    }
    
}
