package itb.rpl.ppl.tgs2.znake.controller;

import itb.rpl.ppl.tgs2.znake.model.snake.ZnakeBodyPart;
import itb.rpl.ppl.tgs2.znake.util.*;
import itb.rpl.ppl.tgs2.znake.model.food.*;
import itb.rpl.ppl.tgs2.znake.model.snake.*;
import itb.rpl.ppl.tgs2.znake.model.food.FoodFactory;
import itb.rpl.ppl.tgs2.znake.model.player.Player;
import itb.rpl.ppl.tgs2.znake.util.command.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author wirasta1330
 */
public class ZnakeController implements ActionListener {
    
    private JPanel container;
    private JPanel board;
    private JPanel scorePanel;
    private JPanel northPanel;
    private JLabel scoreLabel;
    private JPanel snakePanel;

    private SwingWorker<String, Void> threadMove;
    private Znake znake;
    private ZnakeOperation operation;
    private ZBroker broker;
    private Food food;
    private Food extraFood;
    //private Random random;
    private Timer timer;
    private Player player;
            
    private volatile boolean running;
    private volatile int direction;
    
    //private final Object dirObj = new Object();
    
    public ZnakeController() {
        initializeComponents();
    }
    
    /**
     * Inisialisasi komponen-komponen controller
     */
    private void initializeComponents() {
        container = new JPanel();
        northPanel = new JPanel();
        scorePanel = new JPanel();
        scoreLabel = new JLabel();
        
        container.setLayout(new BorderLayout());
        
        northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        northPanel.setPreferredSize(new Dimension(20, 30));
        container.add(northPanel, BorderLayout.NORTH);
        
        scorePanel.setLayout(null);
        scorePanel.setPreferredSize(new Dimension(80, 30));
        northPanel.add(scorePanel);
        
        scoreLabel.setText("Score: 1000");
        scoreLabel.setBounds(12, 3, 70, 21);
        scorePanel.add(scoreLabel);
        
        player = Player.getInstance();
        broker = new ZBroker();
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
        
        board.setBackground(Color.white);
        board.setLayout(new CardLayout());
        container.add(board, BorderLayout.CENTER);
        
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
        
        createDefaultFood();
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
            checkFood();
            timer.setDelay(znake.getSpeed() - 100);
            znake.move(direction);
        } else {
            timer.stop();
        }
        scoreLabel.setText("Score : " + player.getScore());
        board.repaint();
    }
    
    private void doDrawing(Graphics g) {
        if (running) {

            Point p = getActualPosition(food.getPosition());
            //g.drawImage(apple, apple_x, apple_y, this);
            g.setColor(Color.red);
            g.fillRect(p.x, p.y, ZnakeConstants.DOT_WIDTH, ZnakeConstants.DOT_HEIGHT);
            
            if (extraFood != null) {
                p = getActualPosition(extraFood.getPosition());
                g.setColor(Color.blue);
                g.fillRect(p.x, p.y, ZnakeConstants.DOT_WIDTH, ZnakeConstants.DOT_HEIGHT);
            }
            
            znake.getZnakeBodyPart(1).getPosition().x = 11;

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
    
    /**
     * Create default food dipakai ketika inisialisasi dan makanan sudah dimakan ular.
     */
    private void createDefaultFood() {
        //Food food;
        food = FoodFactory.getFoodSnake(ZnakeConstants.NORMAL_EFFECT);
        //return food;
    }
    
    private void createExtraFood() {
        extraFood = FoodFactory.getFoodSnake(ZnakeConstants.EXTRA_FOOD);
    }
    
    private void checkFood() {
        ZnakeBodyPart head = znake.getZnakeBodyPart(0);
        
        if (head.getPosition().equals(food.getPosition())) {
            if (food.getEffect().getEffectName().equalsIgnoreCase(ZnakeConstants.NORMAL_EFFECT)){
                broker.addCommand( new AddBodyCommand(operation));
            } else if (food.getEffect().getEffectName().equalsIgnoreCase(ZnakeConstants.ADD_BODY_EFFECT)){
                broker.addCommand( new AddBodyCommand(operation));
            } else if (food.getEffect().getEffectName().equalsIgnoreCase(ZnakeConstants.INCREASE_SPEED_EFFECT)){
                broker.addCommand( new IncreaseSpeedCommand(operation)); // 
            } else if (food.getEffect().getEffectName().equalsIgnoreCase(ZnakeConstants.DECREASE_SPEED_EFFECT)){
                broker.addCommand( new DecreaseSpeedCommand(operation));
            } else if (food.getEffect().getEffectName().equalsIgnoreCase(ZnakeConstants.REVERSE_DIRECTION_EFFECT)){
                broker.addCommand( new ReverseDirection(operation));
            } else if (food.getEffect().getEffectName().equalsIgnoreCase(ZnakeConstants.SUB_BODY_EFFECT)){
                broker.addCommand( new SubBodyCommand(operation));
            }
            broker.addCommand(new PlusScoreCommand(operation, food));
            broker.executeCommand(); // execute command
            
            createDefaultFood();
            if (player.getScore() % 50 == 0) {
                createExtraFood();
            }
        }
        if (extraFood != null && head.getPosition().equals(extraFood.getPosition())) {
            extraFood = null;
        }
    }
    
//    public Rectangle getActualBounds(Point position) {
//        return new Rectangle(
//            position.x * ZnakeConstants.DOT_WIDTH,
//            position.y * ZnakeConstants.DOT_HEIGHT,
//                ZnakeConstants.DOT_WIDTH,
//                ZnakeConstants.DOT_HEIGHT
//        );
//    }
    
    public Point getActualPosition(Point position) {
        return new Point(
            position.x * ZnakeConstants.DOT_WIDTH,
            position.y * ZnakeConstants.DOT_HEIGHT
        );
    }
    
    /*
     * Getter and setter
     */
    
    public int getDirection() {
        return direction;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    // belum ada yg manggil
    public ZnakeOperation getZnakeOperation() {
        return operation;
    }
    
    // belum ada yg manggil
    public void setZnakeOperation(ZnakeOperation operation) {
        this.operation = operation;
    }
    
    // Dipanggil di view
    public JPanel getPanel() {
        return container;
    }

    
    
}
