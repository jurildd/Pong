package myminitennis;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Juril on 10/21/2016.
 */
//Basically the same as the Racquet class. Difference is kani siya kay naa sa taas.
public class Racquet2 {
    private static final int Y = 30;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;
    int x = 0;
    int xa = 0;
    private Game game;

    public Racquet2(Game game) {
        this.game = game;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WIDTH)
            x = x + xa;
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, Y, WIDTH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A)
            xa = (int)-game.speed;
        if (e.getKeyCode() == KeyEvent.VK_D)
            xa = (int)game.speed;

    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

    public int getBotY() {
        return Y + HEIGHT;
    }
}
