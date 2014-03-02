/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.model;

/**
 *
 * @author edbert
 */
public class ExtraFood extends Food {
    
    protected Effect effect;
    
    public ExtraFood() {
        effect = new Effect();
    }
    
    @Override
    public Effect getEffect() {
        return effect;
    }
    
    public void setEffect(Effect effect) {
        this.effect = effect;
    }
    
    @Override
    public String getName() {
        return "Extra Food";
    }
}
