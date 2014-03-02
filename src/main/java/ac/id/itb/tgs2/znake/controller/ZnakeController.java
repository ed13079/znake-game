/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.controller;

import ac.id.itb.tgs2.znake.ZnakeConstants;
import ac.id.itb.tgs2.znake.model.*;
import ac.id.itb.tgs2.znake.utilities.*;
import ac.id.itb.tgs2.znake.view.*;
import info.clearthought.layout.TableLayout;
import java.awt.LayoutManager;
import javax.swing.*;

/**
 *
 * @author wirasta1330
 */
public class ZnakeController {
    
    private JPanel board;
    private LayoutManager layout;
    private SwingWorker<String, Void> threadMove;
    private Znake znake;
    private volatile boolean running;
    private volatile int direction;
    private int speed = 100;
    
    public ZnakeController() {
        initializeComponents();
    }
    
    private void initializeComponents() {
        znake = new Znake();
        znake.generateBody(6, 6);
        direction = ZnakeConstants.EAST;
        board = new JPanel();
        double[][] size = new double[2][];
        size[0] = new double[ZnakeConstants.BOARD_WIDTH];
        size[1] = new double[ZnakeConstants.BOARD_HEIGHT];
        for (int i = 0; i < ZnakeConstants.BOARD_WIDTH; i++) {
            size[0][i] = ZnakeConstants.CELL_WIDTH;
        }
        for (int i = 0; i < ZnakeConstants.BOARD_HEIGHT; i++) {
            size[1][i] = ZnakeConstants.CELL_HEIGHT;
        }
        layout = new TableLayout(size);
        board.setLayout(layout);
        
        threadMove = new SwingWorker<String, Void>() {
            @Override
            public String doInBackground() {
                while (running) {
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                    }
                    znake.move(direction);
                    board.removeAll();
                    for (int i = 0; i < znake.getZnakeBodyParts().size(); i++) {
                        ZnakeBodyPart zbp = znake.getZnakeBodyParts().get(i);
                        board.add(
                            zbp,
                            String.format("%s, %s", zbp.getPosition().x, zbp.getPosition().y)
                        );
                    }
                    board.repaint();
                    board.revalidate();
//                    System.out.println("========================================");
//                    znake.printBodyParts();
                }
                return null;
            }
        };
    }
    
    public JPanel getPanel() {
        return board;
    }
    
    public int getDirection() {
        return direction;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public void run() {
        running = true;
        threadMove.execute();
    }
}
