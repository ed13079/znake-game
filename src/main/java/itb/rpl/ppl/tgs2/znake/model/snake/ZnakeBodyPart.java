package itb.rpl.ppl.tgs2.znake.model.snake;

import java.awt.Point;

/**
 *
 * @author edbert
 */
public class ZnakeBodyPart implements Cloneable {
    
    private Point position;
    private int from; // asal arah gerakan
    private int to; // tujuan arah gerakan
    
    public ZnakeBodyPart() { }
    
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
    
    @Override
    public ZnakeBodyPart clone() {
        ZnakeBodyPart z = null;
        try {
            z = (ZnakeBodyPart) super.clone();
            z.setPosition((Point)position.clone());
        } catch (CloneNotSupportedException cnse) {
            
        }
        return z;
    }
}
