/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake;

import ac.id.itb.tgs2.znake.model.Znake;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edbert
 */
public class ZnakeTest {
    
    public ZnakeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void znakeMove1Test() {
        Znake z1 = new Znake();
        z1.generateBody(0, 0, 3);
        z1.move(ZnakeConstants.EAST);
        Znake z2 = new Znake();
        z2.generateBody(1, 0, 3);
        for (int i = 0; i < 3; i++) {
            assertEquals(z2.getZnakeBodyParts().get(i).getPosition(), z1.getZnakeBodyParts().get(i).getPosition());
        }
    }
}
