/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.model.food;

import itb.rpl.ppl.tgs2.znake.util.ZnakeConstants;
import itb.rpl.ppl.tgs2.znake.model.food.*;
import java.awt.Point;
import java.util.HashMap;

/**
 *
 * @author Edbert
 */
public class FoodFactory {
    
//    public static final String FOOD_PACKAGE_NAME = "itb.rpl.ppl.tgs2.znake.model.food";
    
    // Implementasi pattern flyweight
    //private static HashMap<String, Food> feedMap = new HashMap<String, Food>();
    private static HashMap<String, Food> feedMap = new HashMap<String, Food>();
   
    public static Food getFoodSnake (String foodType){
        Food foodObject = feedMap.get(foodType);
        //Food foodObject;

        if (foodObject == null) { // check apakah objek food sudah dibuat sebelumnya
            // belum di buat akan di buat objek baru dan put ke hash map
            if (foodType.equalsIgnoreCase(ZnakeConstants.NORMAL_EFFECT)){
                foodObject = new DefaultFood();
            } else {
                foodObject = new ExtraFood(foodType);
            }
            feedMap.put(foodType, foodObject);
        }else {
            // kl sudah ada ambil obej dan ubah posisinya dari objek sebelumnya biarga sama posisinya dengan yang lama
            foodObject.setPosition((int)(Math.random() * ZnakeConstants.BOARD_WIDTH), // value random for x (beetween 0 and limit max board WIDTH)
                             (int)(Math.random() * ZnakeConstants.BOARD_HEIGHT)); // value random for y (beetween 0 and limit max board HEIGHT)
        }
        return foodObject;
    } 
    // coment dibawah ini proses buat apa yah? harus setiing class dan package
    // hmmm.. pake pattern apa yah?
    
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
//    private static Food createFood(int x, int y, String className) {
//        Food food = null;
//        try {
//            
//            if (!className.contains(FOOD_PACKAGE_NAME)) {
//                className = FOOD_PACKAGE_NAME + "." + className;
//            } else if (className.contains(".")) {
//                String[] splitedClassName = className.split(".");
//                className = FOOD_PACKAGE_NAME + "." + splitedClassName[splitedClassName.length - 1];
//            }
//            
//            Class cls = Class.forName(className);
//            food = (Food) cls.newInstance();
//            food.setPosition(new Point(x, y));
//            
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            e.printStackTrace(System.err);
//        }
//        
//        return food;
//    }
//  
    
    // method ini dipakai buat apa yah? qo g ada yg manggil yah hehe
    public static Food createDefaultFood(int x, int y) {
        String key = String.format("DefaultFood[%s, %s]", x, y);
        Food food = feedMap.get(key);
        
        if (food == null) {
            food = new DefaultFood();
            feedMap.put(key, food);
        }
        
        ((DefaultFood)food).setPosition(x, y);
        
        return food;
    }
//    
//    public static Food createExtraFood(int x, int y) {
//        return createExtraFood(x, y, null);
//    }
//    public static Food createExtraFood(int x, int y, Effect effect) {
//        ExtraFood food = (ExtraFood) createFood(x, y, "ExtraFood");
//        if (effect == null) {
//            effect = new Effect();
//            effect.setScore((ZnakeConstants.DEFAULT_EXTRA_TIME_REMAINING / 1000) * 5);
//        }
//        food.setEffect(effect);
//        return food;
//    }
}
