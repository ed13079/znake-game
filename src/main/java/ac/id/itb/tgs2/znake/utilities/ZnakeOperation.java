/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.utilities;

import ac.id.itb.tgs2.znake.model.Znake;

/**
 *
 * @author edbert
 */
public interface ZnakeOperation {
    
    void plusScore(int score);
    void increaseSpeed(int speed);
    void decreaseSpeed(int speed);
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
}
