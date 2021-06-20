import java.awt.*;

public class Snake implements Drawable {
    private int [] x;
    private int [] y;
    private int current_length;
    private int size;
    public Snake() {
        this.x = new int[GamePanel.MAX_UNITS];
        this.y = new int[GamePanel.MAX_UNITS];
        this.size = GamePanel.UNIT_SIZE;
        this.current_length = 2;
        initSnake();
    }
    private void initSnake() {
        this.x[0] = 100;
        this.x[1] = 100 - this.size;
        this.y[0] = 100;
        this.y[1] = 100;

    }
    public int[] getX() {
        return x;
    }
    public int getCurrent_length() {
        return this.current_length;
    }
    public int[] getY() {
        return y;
    }
    public void moveSnake(char direction) {
        for (int i = current_length; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction) {
            case 'U' -> y[0] = y[0] - size; //up
            case 'D' -> y[0] = y[0] + size; // down
            case 'R' -> x[0] = x[0] + size; // right
            case 'L' -> x[0] = x[0] - size; // left

        }
    }
    public void AddToBody() {
        x[current_length] = x[current_length - 1];
        y[current_length] = y[current_length - 1];
        current_length++;
    }
    public int getHeadX() {
        return this.x[0];
    }
    public int getHeadY() {
        return this.y[0];
    }

    @Override
    public void draw(Graphics graphics) {

        graphics.setColor(new Color(194, 255, 208));
        graphics.fillRect(x[0] , y[0] , size, size);
        graphics.setColor(new Color(24, 219, 70));
        for (int i = 1; i < current_length ; i++) {
            graphics.fillRect(x[i] , y[i] , size, size);
        }
    }
}
