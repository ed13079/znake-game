/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.tgs2.znake.controller;

import ac.id.itb.tgs2.znake.ZnakeConstants;
import ac.id.itb.tgs2.znake.command.MoveUpCommand;
import ac.id.itb.tgs2.znake.model.*;
import ac.id.itb.tgs2.znake.model.factory.FoodFactory;
import ac.id.itb.tgs2.znake.model.factory.ZnakeFactory;
import ac.id.itb.tgs2.znake.utilities.*;
import ac.id.itb.tgs2.znake.view.*;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author wirasta1330
 */
public class ZnakeController {
    
    private JPanel board;
    private SwingWorker<String, Void> threadMove;
    private Znake znake;
    private ZnakeOperation operation;
    private FoodFactory foodFactory;
    //private ZnakeElement[][] virtualBoard;
    private Random random;
    
    private volatile boolean running;
    private volatile int direction;
    
    private final Object dirObj = new Object();
    
    public ZnakeController() {
        initializeComponents();
    }
    
    /**
     * Inisialisasi komponen-komponen controller
     */
    private void initializeComponents() {
        operation = new ZnakeOperation(this);
        znake = ZnakeFactory.createZnake(7, 7);
        direction = ZnakeConstants.EAST;
        board = new JPanel();
//        virtualBoard = 
//            new ZnakeElement[ZnakeConstants.BOARD_HEIGHT][ZnakeConstants.BOARD_WIDTH];
//        
//        double[][] size = new double[2][];
//        size[0] = new double[ZnakeConstants.BOARD_WIDTH];
//        size[1] = new double[ZnakeConstants.BOARD_HEIGHT];
//        for (int i = 0; i < ZnakeConstants.BOARD_WIDTH; i++) {
//            size[0][i] = ZnakeConstants.CELL_WIDTH;
//        }
//        for (int i = 0; i < ZnakeConstants.BOARD_HEIGHT; i++) {
//            size[1][i] = ZnakeConstants.CELL_HEIGHT;
//        }
//        layout = new TableLayout(size);
//        board.setLayout(layout);
        
        board.setLayout(null);
        for (ZnakeBodyPart zbp : znake.getZnakeBodyParts()) {
            zbp.setBounds(getActualPosition(zbp));
            board.add(zbp);
        }
        
        threadMove = new SwingWorker<String, Void>() {
            @Override
            public String doInBackground() {
                while (running) {
                    try {
                        Thread.sleep(znake.getSpeed());
                    } catch (InterruptedException e) {
                    }
                    znake.move(getDirection());
//                    board.removeAll();
                    for (int i = 0; i < znake.getZnakeBodyParts().size(); i++) {
                        ZnakeBodyPart zbp = znake.getZnakeBodyParts().get(i);
//                        board.add(zbp, String.format(
//                            "%s, %s",
//                            zbp.getPosition().x,
//                            zbp.getPosition().y));
                        zbp.setBounds(getActualPosition(zbp));
//                        board.add(zbp);
                    }
                    //board.repaint();
                    board.revalidate();
//                    System.out.println("========================================");
//                    znake.printBodyParts();
                }
                return null;
            }
        };
    }
    
    /**
     * Menjalankan engine Znake.
     */
    public void run() {
        running = true;
        threadMove.execute();
    }
    
    public void generateFood() {
        int foodXPos = random.nextInt(ZnakeConstants.BOARD_WIDTH);
        int foodYPos = random.nextInt(ZnakeConstants.BOARD_HEIGHT);
        Food food;
        boolean addFoodAllowed = false;
        
        do {
            food = FoodFactory.createDefaultFood(foodXPos, foodYPos);
        } while (!addFoodAllowed);
    }
    
    /**
     * Oleh karena posisi-posisi elemen ZnakeElement pake posisi tidak sebenarnya,
     * maka untuk ditampilkan ke layar harus pake ukuran dalam pixel. Di sinilah
     * method itu berguna.
     * 
     * @param znakeElement Elemen Znake yang mau dicari posisi Actualnya
     * @return Lokasi dan panjang-lebar elemen
     */
    public Rectangle getActualPosition(ZnakeElement znakeElement) {
        return new Rectangle(
            znakeElement.getPosition().x * ZnakeConstants.CELL_WIDTH,
            znakeElement.getPosition().y * ZnakeConstants.CELL_HEIGHT,
                ZnakeConstants.CELL_WIDTH,
                ZnakeConstants.CELL_HEIGHT
        );
    }
    
    /*
     * Getter and setter
     */
    
    public int getDirection() {
        synchronized (dirObj) {
            return direction;
        }
    }
    
    public void setDirection(int direction) {
        synchronized (dirObj) {
            this.direction = direction;
        }
    }
    
    public ZnakeOperation getZnakeOperation() {
        return operation;
    }
    
    public void setZnakeOperation(ZnakeOperation operation) {
        this.operation = operation;
    }
    
    public JPanel getPanel() {
        return board;
    }
    
}
