package ac.id.itb.tgs2.znake;

import ac.id.itb.tgs2.znake.model.Food;
import ac.id.itb.tgs2.znake.model.factory.FoodFactory;
import ac.id.itb.tgs2.znake.view.ZnakeView;

/**
 * Hello world!
 *
 */
public class Main {

    public static void main(String[] args) {
        // Coba body
//        Znake z = new Znake();
//        z.generateBody(0, 0, 4);
//        z.printBodyParts();
//        z.move(ZnakeConstants.EAST);
//        z.printBodyParts();
        
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
