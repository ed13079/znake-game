/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.model;

import ac.id.itb.tgs2.znake.ZnakeConstants;
import java.awt.Color;

/**
 *
 * @author edbert
 */
public class DefaultFood extends Food {
    
    protected Effect effect;
    
    public DefaultFood() {
        effect = new Effect();
        effect.setScore(ZnakeConstants.DEFAULT_SCORE);
        setBackground(Color.gray);
    }
    
    @Override
    public Effect getEffect() {
        return effect;
    }
    
    @Override
    public String getFoodName() {
        return "Default Food";
    }
}
