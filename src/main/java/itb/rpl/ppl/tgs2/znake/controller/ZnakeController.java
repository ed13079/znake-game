package itb.rpl.ppl.tgs2.znake.controller;

import itb.rpl.ppl.tgs2.znake.model.snake.ZnakeBodyPart;
import itb.rpl.ppl.tgs2.znake.util.*;
import itb.rpl.ppl.tgs2.znake.model.food.*;
import itb.rpl.ppl.tgs2.znake.model.snake.*;
import itb.rpl.ppl.tgs2.znake.model.food.FoodFactory;
import itb.rpl.ppl.tgs2.znake.model.player.Player;
import itb.rpl.ppl.tgs2.znake.util.command.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author wirasta1330
 */
public class ZnakeController {
//implements ActionListener {
    
    private JPanel container;
    private JPanel board;
    private JPanel scorePanel;
    private JPanel northPanel;
    private JLabel scoreLabel;

    private SwingWorker<String, Void> threadMove;
    private Znake znake;
    private ZnakeOperation operation;
    private ZBroker broker;
    private Food food;
    private Food extraFood;
    //private Random random;
    private Timer timer;
    private Timer extraFoodTimer;
    private Timer effectTimer;
    private Player player;
    private int foodCount;
            
    private volatile boolean running;
    //private volatile int direction;
    
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
        
        scoreLabel.setText("Score: 0");
        scoreLabel.setBounds(12, 3, 70, 21);
        scorePanel.add(scoreLabel);
        
        player = Player.getInstance();
        broker = new ZBroker();
        operation = new ZnakeOperation();
        znake = Znake.getInstance();
        znake.generateBody(ZnakeConstants.INIT_POS_X, ZnakeConstants.INIT_POS_Y);
        //direction = ZnakeConstants.EAST;
        
        board = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }
        };
        
        board.setBackground(Color.white);
        board.setLayout(null);
        container.add(board, BorderLayout.CENTER);
        
        threadMove = new SwingWorker<String, Void>() {
            @Override
            public String doInBackground() {
                while (running) {
                    try {
                        Thread.sleep(znake.getSpeed());
                    } catch (InterruptedException e) {
                    }
                    checkFood();
                    checkExtraFood();
                    checkCollision();
                    znake.move(0);
                    scoreLabel.setText("Score: " + player.getScore());
                    board.repaint();
                }
                return null;
            }
        };
        extraFoodTimer = new Timer(ZnakeConstants.DEFAULT_EXTRA_TIME_REMAINING + 5000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                extraFood = null;
            }
        });
        effectTimer = new Timer(ZnakeConstants.EFFECT_TIME, new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                broker.addCommand(new ClearEffectCommand(operation));
                broker.executeCommand();
            }
        });
        createDefaultFood();
    }
    
    /**
     * Menjalankan engine Znake.
     */
    public void run() {
        running = true;
        threadMove.execute();
//        timer = new Timer(ZnakeConstants.DEFAULT_SPEED_1, this);
//        timer.start();
    }
    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (running) {
//            checkFood();
//            checkExtraFood();
//            checkCollision();
//            timer.setDelay(znake.getSpeed());
//            znake.move(0);
//        } else {
//            timer.stop();
//        }
//        scoreLabel.setText("Score: " + player.getScore());
//        board.repaint();
//    }
    
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
            System.out.println("masuk sini");
            doGameOverDrawing(g);
        }   
    }
    
    private void doGameOverDrawing(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = board.getFontMetrics(small);

        g.setColor(Color.BLACK);
        g.setFont(small);
        g.drawString(
            msg, 
            ((ZnakeConstants.BOARD_WIDTH * ZnakeConstants.DOT_WIDTH) - metr.stringWidth(msg)) / 2, 
            ((ZnakeConstants.BOARD_HEIGHT * ZnakeConstants.DOT_HEIGHT) - 30) / 2);
        
        msg = "Your score is: " + player.getScore();
        small = new Font("Helvetica", Font.BOLD, 14);
        metr = board.getFontMetrics(small);

        g.setColor(Color.BLACK);
        g.setFont(small);
        g.drawString(
            msg, 
            ((ZnakeConstants.BOARD_WIDTH * ZnakeConstants.DOT_WIDTH) - metr.stringWidth(msg)) / 2, 
            ((ZnakeConstants.BOARD_HEIGHT * ZnakeConstants.DOT_HEIGHT) + 30) / 2);
    }
    
    public void moveTo(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                broker.addCommand(
                    new MoveUpCommand(operation)
                );
                break;
            case KeyEvent.VK_RIGHT:
                broker.addCommand(
                    new MoveRightCommand(operation)
                );
                break;
            case KeyEvent.VK_DOWN:
                broker.addCommand(
                    new MoveDownCommand(operation)
                );
                break;
            case KeyEvent.VK_LEFT:
                broker.addCommand(
                    new MoveLeftCommand(operation)
                );
                break;
            //default:
                //cmd = new MoveCommand(engine.getZnakeOperation());
        }
        broker.executeCommand();
    }
    
    /**
     * Create default food dipakai ketika inisialisasi dan makanan sudah dimakan ular.
     */
    private void createDefaultFood() {
        food = FoodFactory.getFoodSnake(ZnakeConstants.NORMAL_EFFECT);
    }
    
    private void createExtraFood() {
        int indexObjExtra = 0;
        String extraFoodEffect = "";
        
        // Random effect extra
        indexObjExtra = ((int) (Math.random() * 10)) % ZnakeConstants.N_OBJECT_EXTRA;
        //indexObjExtra = 4;
        if (indexObjExtra == 0) {
            extraFoodEffect = ZnakeConstants.ADD_BODY_EFFECT;
        } else if (indexObjExtra == 1) {
            extraFoodEffect = ZnakeConstants.SUB_BODY_EFFECT;
        } else if (indexObjExtra == 2) {
            extraFoodEffect = ZnakeConstants.INCREASE_SPEED_EFFECT;
        } else if (indexObjExtra == 3) {
            extraFoodEffect = ZnakeConstants.DECREASE_SPEED_EFFECT;
        } else if (indexObjExtra == 4) {
            extraFoodEffect = ZnakeConstants.REVERSE_DIRECTION_EFFECT;
        }
        
        extraFood = FoodFactory.getFoodSnake(extraFoodEffect);
    }
    
    private void checkFood() {
        ZnakeBodyPart head = znake.getZnakeBodyPart(0);
        
        // Cek ketika snake makan default food
        if (head.getPosition().equals(food.getPosition())) {          
            broker.addCommand( new AddBodyCommand(operation));
            broker.addCommand(new PlusScoreCommand(operation, food));
            broker.executeCommand(); // execute command
            ++foodCount;
            
            createDefaultFood();
            
            // Cek score jika sudah mencapai 10 create extra food
            //if (player.getScore() % 10 == 0) {
            if (foodCount % 5 == 0) {
                createExtraFood();
                //extraFoodTimer.stop();
                extraFoodTimer.restart();
                System.out.println(extraFood.getEffect().getEffectName());
            }
        }
        
         
    }
    
    public void checkExtraFood() {
        ZnakeBodyPart head = znake.getZnakeBodyPart(0);        
        // Cek ketika snake makan extra food
        if (extraFood != null && head.getPosition().equals(extraFood.getPosition())) {
            effectTimer.stop();
            
            broker.addCommand(new ClearEffectCommand(operation));
            if (extraFood.getEffect().getEffectName().equalsIgnoreCase(ZnakeConstants.ADD_BODY_EFFECT)){
                broker.addCommand( new AddBodyCommand(operation));
            } else if (extraFood.getEffect().getEffectName().equalsIgnoreCase(ZnakeConstants.INCREASE_SPEED_EFFECT)){
                broker.addCommand( new IncreaseSpeedCommand(operation));
            } else if (extraFood.getEffect().getEffectName().equalsIgnoreCase(ZnakeConstants.DECREASE_SPEED_EFFECT)){
                broker.addCommand( new DecreaseSpeedCommand(operation));
            } else if (extraFood.getEffect().getEffectName().equalsIgnoreCase(ZnakeConstants.REVERSE_DIRECTION_EFFECT)){
                broker.addCommand( new ReverseDirection(operation));
            } else if (extraFood.getEffect().getEffectName().equalsIgnoreCase(ZnakeConstants.SUB_BODY_EFFECT)){
                broker.addCommand( new SubBodyCommand(operation));
            }
            broker.addCommand(new PlusScoreCommand(operation, extraFood));
            broker.executeCommand(); // Execute command
            extraFood = null;
            effectTimer.start();
        }
    }
    
    /**
     * Memeriksa tabrakan ular.
     */
    public void checkCollision() {
        int znakeLength = znake.length();
        ZnakeBodyPart head = znake.getZnakeBodyPart(0);
        for (int i = 1; i < znakeLength; i++) {
            if (head.getPosition().equals(znake.getZnakeBodyPart(i).getPosition())) {
                running = false;
                break;
            }
        }
    }
    
    public Point getActualPosition(Point position) {
        return new Point(
            position.x * ZnakeConstants.DOT_WIDTH,
            position.y * ZnakeConstants.DOT_HEIGHT
        );
    }
    
    /*
     * Getter and setter
     */
    
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
