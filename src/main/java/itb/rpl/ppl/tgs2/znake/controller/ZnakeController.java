/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.controller;

import itb.rpl.ppl.tgs2.znake.view.ZnakeBodyPart;
import itb.rpl.ppl.tgs2.znake.util.ZnakeConstants;
import itb.rpl.ppl.tgs2.znake.util.command.MoveUpCommand;
import itb.rpl.ppl.tgs2.znake.model.food.*;
import itb.rpl.ppl.tgs2.znake.model.player.*;
import itb.rpl.ppl.tgs2.znake.model.snake.*;
import itb.rpl.ppl.tgs2.znake.model.food.FoodFactory;
//import itb.rpl.ppl.tgs2.znake.model.factory.ZnakeFactory;
import itb.rpl.ppl.tgs2.znake.util.ZnakeOperation;
import itb.rpl.ppl.tgs2.znake.view.*;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author wirasta1330
 */
public class ZnakeController {
    
    private JPanel board, foodView;
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
        znake = Znake.getInstance();
        znake.generateBody(ZnakeConstants.INIT_POS_X, ZnakeConstants.INIT_POS_Y);
        direction = ZnakeConstants.EAST;
        board = new JPanel();
        
        board.setLayout(null);
        // set snake in board
        for (ZnakeBodyPart zbp : znake.getZnakeBodyParts()) {
            zbp.setBounds(getActualPosition(zbp.getPosition()));
            board.add(zbp);
        }
        // set food in board
        foodView = new JPanel();
        foodView.setBounds(drawFood(createDefaultFood()));
        foodView.setBackground(Color.BLUE);
        board.add(foodView);
        
        threadMove = new SwingWorker<String, Void>() {
            @Override
            public String doInBackground() {
                while (running) {
                    try {
                        Thread.sleep(znake.getSpeed());
                    } catch (InterruptedException e) {
                    }
                    znake.move(getDirection());
                    for (int i = 0; i < znake.getZnakeBodyParts().size(); i++) {
                        ZnakeBodyPart zbp = znake.getZnakeBodyParts().get(i);
                        zbp.setBounds(getActualPosition(zbp.getPosition()));
                    }
                    board.revalidate();
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
    
    // create default food dipakai ketika initsiaisasi dab makanan sudah dimakan ular
    private Food createDefaultFood() {
//        int foodXPos = random.nextInt(ZnakeConstants.BOARD_WIDTH);
//        int foodYPos = random.nextInt(ZnakeConstants.BOARD_HEIGHT);
        Food food;
//        boolean addFoodAllowed = false;
        
//        do {
            food = FoodFactory.getFoodSnake(ZnakeConstants.NORMAL_EFFECT);
//        } while (!addFoodAllowed);
        return food;
    }
    
    // create ketika sudah mencapai score habis dibagi 50
    private Food createExtraFood() {
//        int foodXPos = random.nextInt(ZnakeConstants.BOARD_WIDTH);
//        int foodYPos = random.nextInt(ZnakeConstants.BOARD_HEIGHT);
        Food food;
//        boolean addFoodAllowed = false;
        
//        do {
            food = FoodFactory.getFoodSnake(ZnakeConstants.NORMAL_EFFECT);
//        } while (!addFoodAllowed);
            return food;
    }
    
    /**
     * Oleh karena posisi-posisi elemen ZnakeElement pake posisi tidak sebenarnya,
     * maka untuk ditampilkan ke layar harus pake ukuran dalam pixel. Di sinilah
     * method itu berguna.
     * 
     * @param znakeElement Elemen Znake yang mau dicari posisi Actualnya
     * @return Lokasi dan panjang-lebar elemen
     */
    public Rectangle getActualPosition(Point position) {
        return new Rectangle(
            position.x * ZnakeConstants.CELL_WIDTH,
            position.y * ZnakeConstants.CELL_HEIGHT,
                ZnakeConstants.CELL_WIDTH,
                ZnakeConstants.CELL_HEIGHT
        );
    }
    
     public Rectangle drawFood(Food food) {
        return new Rectangle(
            food.getPosition().x * ZnakeConstants.CELL_WIDTH,
            food.getPosition().y * ZnakeConstants.CELL_HEIGHT,
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
    
    // belum ada yg manggil
    public void setDirection(int direction) {
        synchronized (dirObj) {
            this.direction = direction;
        }
    }
    
    // belum ada yg manggil
    public ZnakeOperation getZnakeOperation() {
        return operation;
    }
    
    // belum ada yg manggil
    public void setZnakeOperation(ZnakeOperation operation) {
        this.operation = operation;
    }
    
    // belum ada yg manggil
    public JPanel getPanel() {
        return board;
    }
    
}
