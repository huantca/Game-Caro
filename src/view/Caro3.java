package view;
import model.Coordinates;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Caro3 {
    private static int time = 0;
    private static JLabel jLabelTime;
    private static Timer timer;
    private static JButton undo;
    private static JButton start;
    private static JButton revert;
    private static Board3x3 board3x3;
    private int t = 0;

    public void caro(){
        JFrame jFrame = new JFrame("GAME Caro");
        
        JPanel jPanel = new JPanel();
         
        //chạy dọc theo trục 0y
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        
        
        board3x3 = new Board3x3();
        board3x3.setEndGame(new EndGame(){
            @Override
            public void end(String player,int st){
                if (st == Board20x20.Win) {
                    musicWin();
                    JOptionPane.showMessageDialog(null,"người chơi "+player+" thắng");
                    endG();
                }else if (st == Board20x20.DRAW) {
                    musicWin();
                    JOptionPane.showMessageDialog(null,"hòa");
                    endG();
                }
            }
        });
        
        board3x3.setPreferredSize(new Dimension(600, 600));

        JPanel bottomjPanel = new JPanel();
        bottomjPanel.setBackground(Color.PINK);
        
        start = new JButton("Start");
        start.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                music();
                start();
                t++;
            } 
        });
        //Undo
        undo = new JButton("Undo");
        undo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                    undoplayer();
                
            }
        });

        //quay lại menu
        PaintMenu paintDisplay = new PaintMenu();
        revert = new JButton("Quay lại Menu");
        revert.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                music();
                jFrame.setVisible(false);
                if (t!=0) {
                     endG();
                }
                jFrame.add(paintDisplay);
            } 
        });

        jLabelTime = new JLabel("0:0");
        bottomjPanel.add(jLabelTime);
        bottomjPanel.add(start);
        bottomjPanel.add(undo);
        bottomjPanel.add(revert);

        jPanel.add(board3x3);
        jPanel.add(bottomjPanel);

        
        jFrame.add(jPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(true);
        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }
    private static void start(){
        board3x3.reset();
        String value = Coordinates.O_VALUE;
        board3x3.setCurrentPlayer(value);

        time = 0;
        jLabelTime.setText("0:0");
        //timer.
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){
                time++;
                String value = time/60 +" : "+ time%60;
                jLabelTime.setText(value);
            }
        },1000,1000);
        start.setText("Reset");
    }
    private static void endG(){
        start.setText("Start");

        time = 0;
        jLabelTime.setText("0:0");

        timer.cancel();
        timer = new Timer();
        board3x3.reset();
    }
    public void undoplayer(){
        if (board3x3.undoo()) {
            String currentPLayer = board3x3.getCurrentPlayer();
            String v = null;
            if (currentPLayer == Coordinates.O_VALUE) {
                v = Coordinates.X_VALUE;
            }else if (currentPLayer == Coordinates.X_VALUE) {
                v = Coordinates.O_VALUE;
            }
            board3x3.setCurrentPlayer(v);
        }
        
    }
    public void music(){
        File file = new File("src/music/mixkit-negative-tone-interface-tap-2569.wav");
        try {   

            AudioInputStream input = AudioSystem.getAudioInputStream(file) ;
            Clip clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void musicWin(){
        File file = new File("src/music/Am-thanh-chuc-mung-chien-thang-www_tiengdong_com.wav");
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


