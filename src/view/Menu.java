package view;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;


public class Menu {
    private Clip clip;
    public void menu(){
        JFrame jFrame = new JFrame("Game Caro 2 người chơi");
        startMusic();
        PaintMenu menu = new PaintMenu();
        jFrame.add(menu);
        jFrame.setSize(600,600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        
    }
    public void startMusic(){
        File file = new File("src/music/caro.wav");
        try {   

            AudioInputStream input = AudioSystem.getAudioInputStream(file) ;
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
