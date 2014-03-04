package itb.rpl.ppl.tgs2.znake;

import itb.rpl.ppl.tgs2.znake.model.food.Food;
import itb.rpl.ppl.tgs2.znake.model.food.FoodFactory;
import itb.rpl.ppl.tgs2.znake.view.ZnakeView;

/**
 * Hello world!
 *
 */
public class Main {

    public static void main(String[] args) {
        // Coba create food pake teknik advance: Class.forName() dan Class.newInstance()
        // 3:)
//        Food f = FoodFactory.createDefaultFood();
//        System.out.println(f);
//
//        f = FoodFactory.createExtraFood();
//        System.out.println(f);

        // Run aplikasi yang sesungguhnya
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ZnakeView().setVisible(true);
            }
        });
    }
}
