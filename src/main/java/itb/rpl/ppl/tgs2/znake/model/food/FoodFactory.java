package itb.rpl.ppl.tgs2.znake.model.food;

import itb.rpl.ppl.tgs2.znake.util.ZnakeConstants;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Edbert
 */
public class FoodFactory {
    
//    public static final String FOOD_PACKAGE_NAME = "itb.rpl.ppl.tgs2.znake.model.food";
    
    // Implementasi pattern flyweight
    private static HashMap<String, Food> feedMap = new HashMap<String, Food>();
   
    public static Food getFoodSnake (String foodType){
        Food foodObject = feedMap.get(foodType);

        if (foodObject == null) { // check apakah objek food sudah dibuat sebelumnya
            // belum di buat akan di buat objek baru dan put ke hash map
            if (foodType.equalsIgnoreCase(ZnakeConstants.NORMAL_EFFECT)){
                foodObject = new DefaultFood();
            } else {
                foodObject = new ExtraFood(foodType);
            }
            feedMap.put(foodType, foodObject);
        }// else {
            // klo sudah ada ambil objek dan ubah posisinya dari objek sebelumnya biarga sama posisinya dengan yang lama
            foodObject.setPosition(
                (int) (Math.random() * ZnakeConstants.BOARD_WIDTH), // value random for x (beetween 0 and limit max board WIDTH)
                (int) (Math.random() * ZnakeConstants.BOARD_HEIGHT) // value random for y (beetween 0 and limit max board HEIGHT)
            );
        //}
        return foodObject;
    }
    
}
