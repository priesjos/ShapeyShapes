import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    Game game;
    Timer timer;
    ArrayList<Sprite> actors;
    int padding = 25;
    long nextMoment;

    public Board(Game game){
        this.game = game;
        setPreferredSize(new Dimension(600, 700));
        setBackground(Color.BLACK);
    }

    public void init(){
        actors = new ArrayList<>();

        actors.add(new Player(Color.green, getWidth()/2, getHeight()/2, 25, 85, this, game));

        for (int i = 0; i < STATS.getNumEnemies(); i++){
            actors.add(new Enemy(Color.WHITE, (int)(Math.random()*(getWidth()-padding)+padding), (int)(Math.random()*(getHeight()-padding)+padding), 20, 20, this));
        }

        for (int i = 0; i < STATS.getNumFood(); i++){
            actors.add(new Food(Color.RED, (int)(Math.random()*(getWidth()-padding)+padding), (int)(Math.random()*(getHeight()-padding)+padding), 20, 20, this));
        }

        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Sprite actor: actors) {actor.paint(g); }
    }

    public void checkCollisions(){
        for (int i = 1; i < actors.size(); i++){
            if (actors.get(0) instanceof Player && actors.get(0).collidesWith(actors.get(i))){

                if (actors.get(i) instanceof Enemy){
                    actors.get(0).setRemove(true);
                    game.setMouseClicked(false);
                }
                else
                    actors.get(i).setRemove(true);
            }
        }
        for (int i = actors.size()-1; i >= 0; i--){
            if (actors.get(i).isRemove()){
                actors.remove(i);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nextMoment = System.currentTimeMillis();

        if ((nextMoment - game.getMoment()) >= 1500){
            checkCollisions();
        }

        if (game.isMouseClicked()){
            for (Sprite actor: actors) {actor.move(); }
        }


        if (actors.size() <= STATS.getNumEnemies()+1){
            System.out.println("LEVEL PASSED");
            game.setMouseClicked(false);
        }

        repaint();

    }
}
