/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.model.factory;

import ac.id.itb.tgs2.znake.ZnakeConstants;
import ac.id.itb.tgs2.znake.model.*;

/**
 *
 * @author Edbert
 */
public class FoodFactory {
    
    public static final String FOOD_PACKAGE_NAME = "ac.id.itb.tgs2.znake.model";
    
    private static Food createFood(String className) {
        Food food = null;
        try {
            
            if (!className.contains(FOOD_PACKAGE_NAME)) {
                className = FOOD_PACKAGE_NAME + "." + className;
            } else if (className.contains(".")) {
                String[] splitedClassName = className.split(".");
                className = FOOD_PACKAGE_NAME + "." + splitedClassName[splitedClassName.length - 1];
            }
            
            Class cls = Class.forName(className);
            food = (Food) cls.newInstance();
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace(System.err);
        }
        
        return food;
    }
    
    public static Food createDefaultFood() {
        return createFood("DefaultFood");
    }
    
    public static Food createExtraFood() {
        return createExtraFood(null);
    }
    public static Food createExtraFood(Effect effect) {
        ExtraFood food = (ExtraFood) createFood("ExtraFood");
        if (effect == null) {
            effect = new Effect();
            effect.setScore((ZnakeConstants.DEFAULT_EXTRA_TIME_REMAINING / 1000) * 5);
        }
        food.setEffect(effect);
        return food;
    }
}
