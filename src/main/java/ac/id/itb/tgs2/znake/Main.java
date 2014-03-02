package ac.id.itb.tgs2.znake;

import ac.id.itb.tgs2.znake.view.ZnakeView;

/**
 * Hello world!
 *
 */
public class Main {

    public static void main(String[] args) {
        /*Znake z = new Znake();
        z.generateBody(0, 0, 4);
        z.printBodyParts();
        z.move(ZnakeConstants.EAST);
        z.printBodyParts();*/
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ZnakeView().setVisible(true);
            }
        });
    }
}
