import java.awt.*;

public class Apple implements Drawable{
    private int  x;
    private int y;
    private int size;
    public Apple(int x , int y , int size) {
        this.x  = x;
        this.y = y;
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(this.x, this.y, this.size , this.size);
    }
}
