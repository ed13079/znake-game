package itb.rpl.ppl.tgs2.znake.model.food;

import itb.rpl.ppl.tgs2.znake.util.ZnakeConstants;
import java.awt.Point;

/**
 *
 * @author edbert
 */
public class ExtraFood implements Food {
    private Effect effect;
    private String foodName;
    private Point position;
    
    public ExtraFood(String effectName) {
        this.effect = new Effect(ZnakeConstants.DEFAULT_SCORE, effectName);
        this.foodName = ZnakeConstants.EXTRA_FOOD;
        this.position = new Point();
            // random position 
        /*this.position = 
            new Point(
                (int)(Math.random() * ZnakeConstants.BOARD_WIDTH), // value random for x (beetween 0 and limit max board WIDTH)
                (int)(Math.random() * ZnakeConstants.BOARD_HEIGHT) // value random for y (beetween 0 and limit max board HEIGHT)
            );*/
    
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
    public void setPosition(int x, int y) {
        this.position.setLocation(x, y);
    }
    
}
