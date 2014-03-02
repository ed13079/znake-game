/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.id.itb.tgs2.znake.model;

import ac.id.itb.tgs2.znake.ZnakeConstants;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author edbert
 */
public class Znake implements ZnakeConstants {

    private List<ZnakeBodyPart> bodyParts = new ArrayList<>();

    public Znake() {

    }

    public Znake(Point headPosition, int direction) {

    }

    public void generateBody(Point headPosition) {

    }

    public void generateBody(Point headPosition, int znakeLength) {
    }

    public void generateBody(int x, int y, int znakeLength) {
        if (znakeLength < DEFAULT_SNAKE_LENGTH) {
            znakeLength = DEFAULT_SNAKE_LENGTH;
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
    
    public void move(int direction) {
        int dirX = 0;
        int dirY = 0;
        
        switch (direction) {
            case ZnakeConstants.NORTH: dirY = -1; break;
            case ZnakeConstants.SOUTH: dirY = 1; break;
            case ZnakeConstants.WEST: dirX = -1; break;
            case ZnakeConstants.EAST: dirX = 1; break;
        }
        
        //if (not touch a food) {
        bodyParts.get(bodyParts.size() - 1).setPosition(bodyParts.get(bodyParts.size() - 2).getPosition());
        //}
        
        for (int i = bodyParts.size() - 2; i > 0; i--) {
            bodyParts.get(i).setPosition(bodyParts.get(i - 1).getPosition());
        }
        
        Point point = new Point(bodyParts.get(0).getPosition());
        point.x += dirX;
        point.y += dirY;
        justifyZnakePosition(point);
        bodyParts.get(0).setPosition(point);
    }

    /*
    public void justifyZnakePosition(ZnakeBodyPart bodyPart) {
        justifyZnakePosition(bodyPart.getPosition());
    }
    */
    
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
    
    public List<ZnakeBodyPart> getZnakeBodyParts() {
        return bodyParts;
    }
}
