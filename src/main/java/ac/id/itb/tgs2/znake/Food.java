/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake;

import java.awt.Point;

/**
 *
 * @author Edbert
 */
public class Food implements ZnakeElement {
    
    private Point position;
    
    public Food() {
    }
    
    @Override
    public Point getPosition() {
        return position;
    }
    
    public void setPosition(Point position) {
        this.position = position;
    }
}
