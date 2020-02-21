import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    Game game;
    Timer timer;
    Food food;
    Enemy enemy;
    Player player;
    ArrayList<Food> foods = new ArrayList<Food>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Board(Game game){
        this.game = game;
        setPreferredSize(new Dimension(600, 700));
        setBackground(Color.BLACK);
    }

    public void init(){
        player = new Player(Color.green, 50, 50, 15, 15, this);

        for (int i = 0; i < (int)(Math.random()*4)+2; i++){
            foods.add(new Food(Color.YELLOW, 0, 200, 20, 20, this));
        }

        for (int i = 0; i < (int)(Math.random()*3)+1; i++){
            enemies.add(new Enemy(Color.WHITE, 0, 400, 20, 20, this));
        }
        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void paintComponent(Graphics g){;
        super.paintComponent(g);

        player.paint(g);

        for (Food food: foods) {
            food.paint(g);
        }

        for (Enemy enemy: enemies){
            enemy.paint(g);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Food food: foods){
            food.move(this);
        }
        for (Enemy enemy: enemies){
            enemy.move(this);
        }
        repaint();
    }
}
