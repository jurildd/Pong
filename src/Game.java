package myminitennis;

/**
 * Created by Juril on 10/21/2016.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel {

    Ball ball = new Ball(this);
    Racquet racquet = new Racquet(this);                                        //Creates an instance of the racquet
    Racquet2 racquet2 = new Racquet2(this);                                     //Creates another instance of the racquet
    double speed = 1;                                                           //Sets speed to 1, which will be increased per collision detected


    public Game() {
        addKeyListener(new KeyListener() {                                      //Anonymous function
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racquet.keyReleased(e);
                racquet2.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                racquet.keyPressed(e);
                racquet2.keyPressed(e);
                ball.keyPressed(e);
            }
        });

        setFocusable(true);                                                     //Without this, di mugana ang KeyListener
    }

    private void move() throws InterruptedException{                            //Move method w/c is called to move the ball and racquet
        ball.move();
        racquet.move();
        racquet2.move();
    }

    @Override
    public void paint(Graphics g) {                                             //Paints the ball and racquet
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,                   //Para smooth ang ball
                RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        g2d.setColor(Color.RED);
        racquet.paint(g2d);
        g2d.setColor(Color.BLUE);
        racquet2.paint(g2d);

        g2d.setColor(Color.GRAY);                                               //Set font color to grey
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));                        //Set font to Verdana
        g2d.drawString(String.valueOf(ball.getScore2()), 10, 30);		        //Displays the score of player 2
        g2d.drawString(String.valueOf(ball.getScore()), 10, getHeight() - 30);  //Displays the score of player 1
        if(ball.count % 10 == 0 && ball.count != 1){
            g2d.setFont(new Font("Helvetica", Font.ITALIC, 20));
            g2d.drawString("Press 0 for powerup!", 100, getHeight() - 30);
            g2d.setFont(new Font("Helvetica", Font.ITALIC, 20));
            g2d.drawString("Press E for powerup!", 100, 30);
        }

    }

    public void roundOver()throws InterruptedException {                        //Called if less than 3 pa ang scores sa each other, and one of them kay nakascore
        Sound.ROUNDOVER.play();
        JOptionPane.showMessageDialog(this, "Player 1: " + ball.getScore() + "\nPlayer 2: " + ball.getScore2(),
                "Round Over", JOptionPane.YES_NO_OPTION);
        if(ball.getScore() != 3 && ball.getScore2() !=3){                       //If di pa 3 ang score, call balik ang game method
            game();
            Sound.ROUNDOVER.stop();
        }
        else{                                                                   //If 3 na, call gameover
            gameOver();
        }
    }

    public void gameOver()throws InterruptedException{                          //Prompts a window if ganahan pa ag player mag play again or exit
        Sound.GAMEOVER.play();
        if (JOptionPane.showConfirmDialog(null, "Do you want to play again?", "GAME OVER",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                game();
        } else {
            System.exit(ABORT);
        }
    }

    public static void main(String[] args)throws InterruptedException {
        JFrame frame = new JFrame("Mini Tennis");
        Game game = new Game();
        frame.add(game);                                                        //Adds the drawm object to the window
        frame.setSize(300, 400);                                                //Sets the size of the frame
        frame.setVisible(true);                                                 //Para makita ang frame
        frame.setResizable(false);                                              //Para dili maresize
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                   //Para kung i-click ang close, muclose
        game.game();                                                            //Runs the game
    }

    public void game() throws InterruptedException{                             //Loop pirmi hangtod wala mapildi sa round/game
        while (true) {
            move();
            repaint();
            Thread.sleep(10);                                                   //Para mupause kadyot para maprint ang mga object sa window
        }
    }
}
