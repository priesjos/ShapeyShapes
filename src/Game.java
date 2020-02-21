import javax.swing.*;
import java.awt.event.KeyEvent;

public class Game extends JFrame{

    Board board;

    public Game(){
        setTitle("SHAPEY SHAPES");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        board = new Board(this);
        add(board);
        pack();
        board.init();
    }

    public static void main(String[] args){
        new Game();
    }
}
