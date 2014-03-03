/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.model.player;

import itb.rpl.ppl.tgs2.znake.model.snake.Znake;

/**
 *
 * @author 
 */
public class Player {
    private String nama;
    private int score;
    
    // singleton
    private static Player instance = new Player();

    private Player() {
        score = 0;
    }

    public static Player getInstance(){
        return instance;
    }
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score = this.score + score ;
    }
    
    
}
