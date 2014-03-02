/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.model.factory;

import ac.id.itb.tgs2.znake.ZnakeConstants;
import ac.id.itb.tgs2.znake.model.*;
import java.awt.Point;
import java.util.HashMap;

/**
 *
 * @author Edbert
 */
public class FoodFactory {
    
    public static final String FOOD_PACKAGE_NAME = "ac.id.itb.tgs2.znake.model";
    
    // Implementasi pattern flyweight
    // Saat ini masih belum diimplementasikan
    public static final HashMap<String, Food> feedMap = new HashMap<>();
    
    /**
     * Buat instance food secara default. Di-private-kan biar yang diakses itu
     * cuma <code>createDefaultFood()</code> sama <code>createExtraFood()</code>,
     * karena si param <code>className</code>-nya ini yang bahaya kali klo sampe salah.
     * @param x posisi x food
     * @param y posisi y food
     * @param className Nama kelas yang akan dibuat: 
     * <code>ac.id.itb.tgs2.znake.model.DefaultFood</code> atau
     * <code>ac.id.itb.tgs2.znake.model.ExtraFood</code>
     * @return <code>Food</code> yang akan menjadi santapan <code>Znake</code>
     */
    private static Food createFood(int x, int y, String className) {
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
            food.setPosition(new Point(x, y));
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace(System.err);
        }
        
        return food;
    }
    
    public static Food createDefaultFood(int x, int y) {
        return createFood(x, y, "DefaultFood");
    }
    
    public static Food createExtraFood(int x, int y) {
        return createExtraFood(x, y, null);
    }
    public static Food createExtraFood(int x, int y, Effect effect) {
        ExtraFood food = (ExtraFood) createFood(x, y, "ExtraFood");
        if (effect == null) {
            effect = new Effect();
            effect.setScore((ZnakeConstants.DEFAULT_EXTRA_TIME_REMAINING / 1000) * 5);
        }
        food.setEffect(effect);
        return food;
    }
}
