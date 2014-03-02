/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.model.factory;

import ac.id.itb.tgs2.znake.ZnakeConstants;
import static ac.id.itb.tgs2.znake.ZnakeConstants.DEFAULT_SNAKE_LENGTH;
import ac.id.itb.tgs2.znake.model.Znake;
import ac.id.itb.tgs2.znake.model.ZnakeBodyPart;
import java.awt.Point;

/**
 *
 * @author edbert
 */
public class ZnakeFactory {
    
    private ZnakeFactory() { }
    
    public static Znake createZnake(int x, int y) {
        return createZnake(x, y, ZnakeConstants.DEFAULT_SNAKE_LENGTH);
    }
    
    public static Znake createZnake(int x, int y, int znakeLength) {
        Znake znake = new Znake();
        if (znakeLength < DEFAULT_SNAKE_LENGTH) {
            znakeLength = DEFAULT_SNAKE_LENGTH;
        }

        znake.clearBodyPart();

        ZnakeBodyPart bodyPart;
        Point position = new Point(x, y);

        for (int i = 0; i < znakeLength; i++) {
            bodyPart = new ZnakeBodyPart();
            znake.justifyZnakePosition(position);
            bodyPart.setPosition((Point) position.clone()); // Di-clone biar ngaish value-nya
            znake.addBodyPart(bodyPart);
            position.x--;
        }
        
        return znake;
    }
    
}
