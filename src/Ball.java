package myminitennis;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


/**
 * Created by Juril on 10/21/2016.
 */
public class Ball{
    private static final int DIAMETER = 30;                                     //Size of the ball

    private Game game;
    private int x = 50;                                                         //Initial position of the ball (x axis)
    private int xa = 1;                                                         //Direction of the ball
    private int y = 100;                                                        //Initial position of the ball (y axis)
    private int ya = 1;                                                         //Direction of the ball
    public int turn = 1;                                                       //Kung kay kinsang racquet na turn
    private int p1 = 0;                                                         //Score sa player 1
    private int p2 = 0;                                                         //Score sa player 2
    private int temp1 = p1;                                                     //Temporary score sa p2 para mahibaw-an kung nakascore ang usa or wala
    private int temp2 = p2;                                                     //Temporary score sa p2 para mahibaw-an kung nakascore ang usa or wala
    public int count = 0;

    public Ball(Game game) {                                                    //Default constructor para pag gamit sa Key Listener
        this.game = game;
    }

    public int getScore(){                                                      //Getter method sa score sa player 1 para magamit sa other classes
        return p1;
    }

    public int getScore2(){                                                     //Getter method sa score sa player 2 para magamit sa other classes
        return p2;
    }

    void move() throws InterruptedException{
        if(temp1 != p1 || temp2 != p2){                                         //If naa makascore sa ila duha, balik uno ang values. So ireset pud ang values
            if(temp1 != p1){
                xa = 1;
                ya = 1;
                turn = 1;
            }
            else{
                xa = -1;
                ya = -1;
                turn = 0;
            }
            count = 0;
            x = game.getWidth() / 2;
            y = game.getHeight() / 2;
            temp1 = p1;
            temp2 = p2;
            game.speed = 1;
        }
        if(p1 == 3 || p2 == 3){                                                 //If ang score sa usa sa ila kay equal na to 3, set back to 0.
            p1 = 0;
            p2 = 0;
        }
        boolean changeDirection = true;
        if (x + xa < 0)                                                         //If midair, walay mahitabo
            xa = (int)game.speed;
        else if (x + xa > game.getWidth() - DIAMETER) {                        //If maigo sa right side na wall, bounce back
            xa = (int) -game.speed;
            count++;
        }
        else if (y + ya < 0) {                                                    //If maigo sa left side na wall, bounce back
            ya = (int) game.speed;
            count++;
        }
        else if (y + ya > game.getHeight() - DIAMETER) {                        //If makalusot sa ubos na racquet, score ang top racquet/player
            ++p2;                                                               //Add point sa player
            game.roundOver();                                                   //Prompts the score window
        }

        else if (collision()){                                                  //If naay usa ka racquet na nagcollide sa ball
            count++;
            if(turn % 2 == 1){
                y = game.racquet.getTopY() - DIAMETER;
                ya = (int)-game.speed;

            }
            if(turn % 2 == 0){
                y = game.racquet2.getBotY() + DIAMETER;                         //Similar sa getTopY, but gibutang balik sa taas na racquet
                ya = (int)+game.speed;
            }
            turn++;
            game.speed += 0.2;

        } else
            changeDirection = false;
        if(y <= 10){                                                            //If nakalusot ang ball sa taas na racquet
            ++p1;
            game.roundOver();
        }
        if (changeDirection) {
            Sound.BALL.play();
        }
        x = x + xa;                                                             //Pag move sa ball
        y = y + ya;                                                             //Pag move sa ball
    }
    public void keyPressed(KeyEvent e) {                                        //Powerup
        if(count % 10 == 0 && count != 1){
            if (e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_NUMPAD0){
                game.speed +=10;
            }
        }

    }
    private boolean collision() {                                               //If naay usa ka racquet na nagcollide sa ball, mureturn siya ug true therefore musulod sa if(collision) na statement
        if(game.racquet.getBounds().intersects(getBounds()) || game.racquet2.getBounds().intersects(getBounds())) {
            return true;
        }
        else {
            return false;
        }
    }

    public void paint(Graphics2D g) {                                           //Creates the circle object
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {                                              //Returns the bound area of the circle, pero rectangle ang form
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}