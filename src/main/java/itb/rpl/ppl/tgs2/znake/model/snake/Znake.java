package itb.rpl.ppl.tgs2.znake.model.snake;

import itb.rpl.ppl.tgs2.znake.util.ZnakeConstants;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author edbert
 */
public class Znake {

    private List<ZnakeBodyPart> bodyParts = new ArrayList<ZnakeBodyPart>();
    private int speed = ZnakeConstants.DEFAULT_SPEED_1;
    private static Znake instance = new Znake();
    private int directionFlag = ZnakeConstants.DIRECTION_NORMAL;
//    private boolean grow = false;
    
    private Znake() {
    }
    
    public static Znake getInstance(){
        return instance;
    }
    
    public void generateBody(int x, int y) {
        generateBody(x, y, ZnakeConstants.DEFAULT_SNAKE_LENGTH);
    }

    public void generateBody(int x, int y, int znakeLength) {
        // ini buat generate body snake
        // tetapi kalo ada penambahan body atau pengurangan body 
        // body di remove dulu baru di buat lagi dari awal
        
        // baiknya generate body ketika inisialasi
        // tetapi kalo ada add body cukup -> add list
        // kemudian kalo kurang body cukup -> remove body by index
        
        if (znakeLength < ZnakeConstants.DEFAULT_SNAKE_LENGTH) {
            znakeLength = ZnakeConstants.DEFAULT_SNAKE_LENGTH;
        }

        bodyParts.clear();

        ZnakeBodyPart bodyPart;
        Point position = new Point(x, y);

        for (int i = 0; i < znakeLength; i++) {
            bodyPart = new ZnakeBodyPart();
            justifyZnakePosition(position);
            bodyPart.setPosition((Point) position.clone()); // Di-clone biar ngaish value-nya
            bodyParts.add(bodyPart);
            position.x--;
        }
    }
    
    
    /**
     * Menjalankan si ular.
     * 
     * @param direction Arah ular: ke atas, kanan, bawah atau kiri.
     */
    public void move(int direction) {
        int dirX = 0;
        int dirY = 0;
        
        switch (direction) {
            case ZnakeConstants.NORTH: dirY = -1 * directionFlag; break;
            case ZnakeConstants.SOUTH: dirY = 1 * directionFlag; break;
            case ZnakeConstants.WEST: dirX = -1 * directionFlag; break;
            case ZnakeConstants.EAST: dirX = 1 * directionFlag; break;
        }
        
//        if (grow) {
//            grow = false;
//            ZnakeBodyPart zbp = new ZnakeBodyPart();
//            zbp.setPosition(
//                new Point(bodyParts.get(bodyParts.size() - 1).getPosition())
//            );
//            bodyParts.add(bodyParts.size() - 1, zbp);
//        } else {
//            bodyParts.get(bodyParts.size() - 1).setPosition(bodyParts.get(bodyParts.size() - 2).getPosition());
//        }
//        bodyParts.get(bodyParts.size() - 1).setPosition(bodyParts.get(bodyParts.size() - 2).getPosition());
        
        for (int i = bodyParts.size() - 1; i > 0; i--) {
            bodyParts.get(i).setPosition(bodyParts.get(i - 1).getPosition());
        }
        
        Point point = new Point(bodyParts.get(0).getPosition());
        point.x += dirX;
        point.y += dirY;
        justifyZnakePosition(point);
        bodyParts.get(0).setPosition(point);
    }
    
    /**
     * Menambah panjang si ular.
     */
    public void grow() {
        ZnakeBodyPart zbp = new ZnakeBodyPart();
        zbp.setPosition(
            new Point(bodyParts.get(bodyParts.size() - 1).getPosition())
        );
        bodyParts.add(bodyParts.size() - 1, zbp);
    }
    
    /**
     * Ngurangin panjang si ular.
     */
    public void diet() {
        bodyParts.remove(bodyParts.size() - 1);
    }
    
    public void clearEffect() {
        speed = ZnakeConstants.DEFAULT_SPEED_1;
        directionFlag = ZnakeConstants.DIRECTION_NORMAL;
    }
    
//    public void abnormalDirectionEffect() {
//        directionFlag = ZnakeConstants.DIRECTION_ABNORMAL;
//    }
    
    /**
     * Mastiin posisi bagian tubuh si ular biar ga menyimpang dari board yang
     * benar.
     * 
     * @param position Posisi si body part
     */
    public void justifyZnakePosition(Point position) {
        if (position.x < 0) {
            position.x = ZnakeConstants.BOARD_WIDTH - 1;
        }
        
        if (position.x > ZnakeConstants.BOARD_WIDTH - 1) {
            position.x = 0;
        }
        
        if (position.y < 0) {
            position.y = ZnakeConstants.BOARD_HEIGHT - 1;
        }
        
        if (position.y > ZnakeConstants.BOARD_HEIGHT - 1) {
            position.y = 0;
        }
    }

    public void printBodyParts() {
        for (ZnakeBodyPart bp : bodyParts) {
            System.out.println(bp.getPosition());
        }
    }
    
    /*
     * Method tambahan untuk znake body parts
     */
    
    public void addBodyPart(ZnakeBodyPart bodyPart) {
        justifyZnakePosition(bodyPart.getPosition());
        bodyParts.add(bodyPart);
    }
    
    public void clearBodyPart() {
        bodyParts.clear();
    }
    
    public int length() {
        return bodyParts.size();
    }
    
    public ZnakeBodyPart getZnakeBodyPart(int i) {
        return bodyParts.get(i).clone();
    }
    
    /*
     * Getter and setter 
     */
    
    // Bahaya kali diginikan, tr kelas lain bisa semena-mena manipulasi objek ular
//    public List<ZnakeBodyPart> getZnakeBodyParts() {
//        return bodyParts;
//    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirectionFlag() {
        return directionFlag;
    }

    public void setDirectionFlag(int directionFlag) {
        this.directionFlag = directionFlag;
    }
    
    
}
