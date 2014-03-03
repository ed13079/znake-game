/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itb.rpl.ppl.tgs2.znake.controller;

import itb.rpl.ppl.tgs2.znake.model.snake.ZnakeBodyPart;
import itb.rpl.ppl.tgs2.znake.util.ZnakeConstants;
import itb.rpl.ppl.tgs2.znake.model.food.*;
import itb.rpl.ppl.tgs2.znake.model.snake.*;
import itb.rpl.ppl.tgs2.znake.model.food.FoodFactory;
import itb.rpl.ppl.tgs2.znake.util.ZnakeOperation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author wirasta1330
 */
public class ZnakeController implements ActionListener {
    
    private JPanel board, foodView;
    private SwingWorker<String, Void> threadMove;
    private Znake znake;
    private ZnakeOperation operation;
    private FoodFactory foodFactory;
    private Food defaultFood;
    private Random random;
    private Timer timer;
    
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
        board = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }
        };
        
        board.setLayout(null);
        // set snake in board
//        for (ZnakeBodyPart zbp : znake.getZnakeBodyParts()) {
//            zbp.setBounds(getActualBounds(zbp.getPosition()));
//            board.add(zbp);
//        }
        // set food in board
//        foodView = new JPanel();
//        foodView.setBounds(drawFood(createDefaultFood()));
//        foodView.setBackground(Color.BLUE);
//        board.add(foodView);
        
//        threadMove = new SwingWorker<String, Void>() {
//            @Override
//            public String doInBackground() {
//                while (running) {
//                    try {
//                        Thread.sleep(znake.getSpeed());
//                    } catch (InterruptedException e) {
//                    }
//                    znake.move(getDirection());
//                    for (int i = 0; i < znake.getZnakeBodyParts().size(); i++) {
//                        ZnakeBodyPart zbp = znake.getZnakeBodyParts().get(i);
//                        zbp.setBounds(getActualPosition(zbp.getPosition()));
//                    }
//                    board.revalidate();
//                }
//                return null;
//            }
//        };
        
        defaultFood = createDefaultFood();
    }
    
    /**
     * Menjalankan engine Znake.
     */
    public void run() {
        running = true;
        //threadMove.execute();
        timer = new Timer(ZnakeConstants.DEFAULT_SPEED_1, this);
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            checkDefaultFood();
            
            znake.move(direction);
        } else {
            timer.stop();
        }
        board.repaint();
    }
    
    private void doDrawing(Graphics g) {
        if (running) {

            Point p = getActualPosition(defaultFood.getPosition());
            //g.drawImage(apple, apple_x, apple_y, this);
            g.setColor(Color.red);
            g.fillRect(p.x, p.y, ZnakeConstants.DOT_WIDTH, ZnakeConstants.DOT_HEIGHT);

            for (int i = 0; i < znake.length(); i++) {
                ZnakeBodyPart zbp = znake.getZnakeBodyPart(i);
                p = getActualPosition(zbp.getPosition());
                
                if (i == 0) {
                    //g.drawImage(head, x[z], y[z], this);
                    g.setColor(Color.green);
                    
                } else {
                    //g.drawImage(ball, x[z], y[z], this);
                    g.setColor(Color.yellow);
                }
                
                g.fillRect(p.x, p.y, ZnakeConstants.DOT_WIDTH, ZnakeConstants.DOT_HEIGHT);
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        } else {

            //gameOver(g);
        }   
    }
    
    // create default food dipakai ketika initsiaisasi dab makanan sudah dimakan ular
    private Food createDefaultFood() {
        Food food;
        food = FoodFactory.getFoodSnake(ZnakeConstants.NORMAL_EFFECT);
        return food;
    }
    
    private void checkDefaultFood() {
        ZnakeBodyPart head = znake.getZnakeBodyPart(0);
        
        if (head.getPosition().equals(defaultFood.getPosition())) {
            znake.grow();
            defaultFood = createDefaultFood();
        }
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
     * @param position Elemen Znake yang mau dicari posisi Actualnya
     * @return Lokasi dan panjang-lebar elemen
     */
    public Rectangle getActualBounds(Point position) {
        return new Rectangle(
            position.x * ZnakeConstants.DOT_WIDTH,
            position.y * ZnakeConstants.DOT_HEIGHT,
                ZnakeConstants.DOT_WIDTH,
                ZnakeConstants.DOT_HEIGHT
        );
    }
    
    public Point getActualPosition(Point position) {
        return new Point(
            position.x * ZnakeConstants.DOT_WIDTH,
            position.y * ZnakeConstants.DOT_HEIGHT
        );
    }
    
//     public Rectangle drawFood(Food food) {
//        return new Rectangle(
//            food.getPosition().x * ZnakeConstants.DOT_WIDTH,
//            food.getPosition().y * ZnakeConstants.DOT_HEIGHT,
//                ZnakeConstants.DOT_WIDTH,
//                ZnakeConstants.DOT_HEIGHT
//        );
//    }
    
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
