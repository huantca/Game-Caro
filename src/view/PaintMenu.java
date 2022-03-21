package view;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class PaintMenu extends JPanel {
    private Caro20 caro20 = new Caro20();
    private Caro3 caro3 = new Caro3();
    public PaintMenu(){
       addMouseListener(new MouseInputAdapter() {
           @Override 
           public void mousePressed(MouseEvent e){
               super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                 if (x>=200 && x<=400 && y>=90 && y<=300) {
                    caro3.caro();
                    music();
                    
                }
                if (x>= 190 && x<=400 && y>=300 && y<=520) {
                    caro20.caro();
                    music();
                }
           }
       });
       
    }
    private BufferedImage img3;
    private BufferedImage img20;
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; 
        g2.setBackground(Color.GRAY);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        String text = "Chọn bàn chơi";
        g2.setColor(Color.yellow);
        g2.drawString(text, 166, 34);
        g2.setColor(Color.ORANGE);
        g2.drawString(text,169, 37);
        try {
            img3 = ImageIO.read(getClass().getResource("../img/3x3.jpg"));
            g.drawImage(img3, 190,50,220, 220, this);
            img20 = ImageIO.read(getClass().getResource("../img/20x20.jpg"));
            g.drawImage(img20, 190,300,220, 220, this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void music(){
        File file = new File("src/music/N56BNFY-click.wav");
        try {   

            AudioInputStream input = AudioSystem.getAudioInputStream(file) ;
            Clip clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
