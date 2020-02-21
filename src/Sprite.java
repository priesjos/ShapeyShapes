import java.awt.*;

public abstract class Sprite {

    Color color;
    int x, y, width, height;
    double dx, dy;
    final int SPEED = 8;
    Board board;

    public Sprite(Color color, int x, int y, int width, int height, Board board){

        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.board = board;

        while ((int)dx == 0 || (int)dy == 0){
            double angle = 2 * Math.PI * (Math.random()+1);
            double speed = (SPEED*Math.random() + 1);
            dx = Math.cos(angle)*speed;
            dy = Math.sin(angle)*speed;
        }
    }
/*
    public void paint(Graphics g, Color color, int x, int y, int diameter, boolean isCircle){
        g.setColor(color);
        if (isCircle)
            g.fillOval(x, y, diameter, diameter);
        else
            g.fillRect(x, y, diameter, diameter);
    }*/

    public abstract void paint(Graphics g);

    public void move(Board board){

        //Predictive movement
        double nextLeft = x + dx;
        double nextRight = x+width + dx;
        double nextTop = y + dy;
        double nextBottom = y+height + dy;

        if (nextTop < 0 || nextBottom > (double)board.getHeight()){
            dy *= -1;
        }
        if (nextLeft < 0 || nextRight > (double)board.getWidth()){
            dx *= -1;
        }

        x += dx;
        y += dy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}