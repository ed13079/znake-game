/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.model.food;

import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Edbert
 */
public interface Food {
    
    Effect getEffect();
    String getFoodName();
    Point getPosition();
    void setPosition(int x, int y);
//    @Override
//    public String toString() {
//        return "Food { Name: " + getFoodName() + ", Score: " + getScore() + " }";
//    }
    
}
