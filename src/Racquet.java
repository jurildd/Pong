package myminitennis;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Juril on 10/21/2016.
 */
public class Racquet {
    private static final int Y = 330;                                               //Location racquet sa ubos
    private static final int WIDTH = 60;                                            //Width sa racquet
    private static final int HEIGHT = 10;                                           //Height sa racquet
    int x = 0;
    int xa = 0;
    private Game game;
    private Ball ball;

    public Racquet(Game game) {
        this.game = game;
    }

    public void move() {                                                            //Where the movement of the racquet is done
        if (x + xa > 0 && x + xa < game.getWidth() - WIDTH)                         //If wala pa naigo sa either side of the wall, makamove pa siya
            x = x + xa;
    }

    public void paint(Graphics2D g) {                                               //Creates the racquet
        g.fillRect(x, Y, WIDTH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {                                           //If gibuy-an nimo ang key, dili mumove
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {                                            //If left key imo giclick, mumove to left
        if (e.getKeyCode() == KeyEvent.VK_LEFT)                                     //If right key, mumove to right
            xa = (int)-game.speed;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = (int)game.speed;

    }

    public Rectangle getBounds() {                                                  //Bounds sa racquet
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }


    public int getTopY() {                                                          //If ang ball kay mapadulong sa side sa imo racquet, para nice ang pag bounce, ibutang ang ball sa taas sa imo racquet
        return Y - HEIGHT;
    }
}
