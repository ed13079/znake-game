/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake;

/**
 *
 * @author edbert
 */
public class ZnakeEngine {
    
    private volatile boolean running;
    private Znake znake;
    
    public ZnakeEngine() {
        //running = true;
        Znake znake = new Znake();
        znake.generateBody(null);
    }
}
