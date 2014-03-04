package itb.rpl.ppl.tgs2.znake.model.food;

import java.awt.Point;

/**
 *
 * @author Edbert
 */
public interface Food {
    
    Effect getEffect();
    String getFoodName();
    Point getPosition();
    void setPosition(int x, int y);

}
