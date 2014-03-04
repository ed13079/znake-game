package itb.rpl.ppl.tgs2.znake;

import itb.rpl.ppl.tgs2.znake.view.ZnakeView;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Hello world!
 *
 */
public class Main {

    public static void main(String[] args) {
        // Run aplikasi yang sesungguhnya
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ZnakeView zv = new ZnakeView();
                zv.setVisible(true);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    SwingUtilities.updateComponentTreeUI(zv);
                } catch (Exception e) { }
            }
        });
    }
}
