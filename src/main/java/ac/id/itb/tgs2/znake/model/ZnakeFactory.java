/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.model;

import ac.id.itb.tgs2.znake.model.Znake;
import java.awt.Point;

/**
 *
 * @author edbert
 */
public class ZnakeFactory {
    
    private ZnakeFactory() { }
    
    public static Znake createSnake(Point headPosition) {
        Znake snake = new Znake();
        
        return snake;
    }
}
