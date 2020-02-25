import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class Game extends JFrame{

    Board board;

    int positionX, positionY;

    public Game(){
        setTitle("SHAPEY SHAPES");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        board = new Board(this);
        add(board);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                super.mouseEntered(e);
                setCursor(getToolkit().createCustomCursor(new BufferedImage(3, 3, 2), new Point(0,0), "null"));
            }
        });

        addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent e){
                super.mouseMoved(e);
                positionX = e.getX();
                positionY = e.getY();
            }
        });

        pack();
        board.init();
        setLocationRelativeTo(null);
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public static void main(String[] args){
        new Game();
    }


}
