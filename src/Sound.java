package myminitennis;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav"));
	public static final AudioClip ROUNDOVER = Applet.newAudioClip(Sound.class.getResource("roundover.wav"));
	public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("gameover.wav"));
}