import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.Random;

public class GamePanel extends JPanel {
   private Snake snake;
   private Apple apple;
   private char direction;
   private boolean is_running;
   private Random random;
   private  int speed;
   public static final int SCREEN_WIDTH = 600;
   public static final int SCREEN_HEIGHT = 600;
   public static final int UNIT_SIZE = 25;
   public static final int MAX_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;

    public GamePanel() {
        direction ='R';
        random = new Random();
        snake = new Snake();
        apple = new Apple(0,0, UNIT_SIZE);
        speed = 130;
        createNewApple();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new GameKeys());
        this.is_running = true;

    }
    public void startGame() {
        new Thread(() -> {
            while (is_running) {
                moveOneStep();
                checkCollisions();
                repaint();
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public void paint(Graphics graphics) {
        super.paint(graphics);
        draw(graphics);

    }
    public void draw(Graphics graphics) {
        apple.draw(graphics);
        snake.draw(graphics);

    }
    public void moveOneStep(){
        snake.moveSnake(this.direction);
    }
    public void checkCollisions(){
        checkApple();
        checkBorders();
        checkSnakeCollision();

    }
    public void checkApple() {
        if (snake.getHeadX() == apple.getX() && snake.getHeadY() == apple.getY()) {
           createNewApple();
           if (speed > 40)
                speed -= 10;
           snake.AddToBody();
        }
    }
    public void checkSnakeCollision() {
        int x = snake.getHeadX();
        int y = snake.getHeadY();
        for (int i = 1; i < snake.getCurrent_length(); i++) {
            if (snake.getX()[i] == x && snake.getY()[i] == y) {
                is_running = false;
                return;
            }
        }
    }
    public void checkBorders() {
        int x = snake.getHeadX();
        int y = snake.getHeadY();
        if (x < 0) {
            // left border
            is_running = false;
        }
        if (x > SCREEN_WIDTH) {
            // right border
            is_running = false;
        }
        if (y < 0) {
            // up border
            is_running = false;

        }
        if (y > SCREEN_HEIGHT ) {
            // down border
            is_running = false;

        }


    }
    public void createNewApple() {
        this.apple.setX(random.nextInt(SCREEN_WIDTH / UNIT_SIZE)* UNIT_SIZE) ;
        this.apple.setY(random.nextInt(SCREEN_HEIGHT / UNIT_SIZE)* UNIT_SIZE) ;
    }

    public class GameKeys implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT -> direction = 'R';
                case KeyEvent.VK_LEFT -> direction = 'L';
                case KeyEvent.VK_UP -> direction = 'U';
                case KeyEvent.VK_DOWN -> direction = 'D';

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }
}
