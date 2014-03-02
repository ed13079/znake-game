/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.view;

import java.awt.Color;
import java.awt.Point;
import javax.swing.*;

/**
 *
 * @author edbert
 */
public class ZnakeBodyPart extends JPanel {
    
    private Point position;
    private int from; // asal arah gerakan
    private int to; // tujuan arah gerakan
    
    public ZnakeBodyPart() {
        this.setBackground(Color.red);
    }
    
    public Point getPosition() {
        return position;
    }
    
    public void setPosition(Point position) {
        this.position = position;
    }
    
    public void setPosition(int x, int y) {
        setPosition(new Point(x, y));
    }
    
    public void setFrom(int from) {
        this.from = from;
    }
    
    public void setTo(int to) {
        this.to = to;
    }
    
}
