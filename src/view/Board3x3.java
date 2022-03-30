package view;
import model.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
public class Board3x3 extends JPanel{
    private static final int N = 3;
    private static final int M = 3;
    
    private BufferedImage imgX;
    private BufferedImage imgO;

    private static EndGame endGame;
    public static final int DRAW = 0;
    public static final int Win = 1;
    public static final int Continue = 2;
    private Coordinates coordinates;
    private Stack<Undo> st;

    private Coordinates matrix[][] = new Coordinates[N][M];
    private String currentPlayer = Coordinates.EMPTY_VALUE;

    private void initMatrix(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = new Coordinates();
            }
        }
    }
    public Board3x3(){
        this.initMatrix();
        CheckWin3x3 win3x3 = new CheckWin3x3(this);
        st = new Stack<Undo>();
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                click();
                if (currentPlayer.equals(Coordinates.EMPTY_VALUE)) {
                    return;
                }
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        coordinates = matrix[i][j];
                        
                        int xStart = coordinates.getX();
                        int yStart = coordinates.getY();
                        int xEnd = xStart + coordinates.getW();
                        int yEnd = yStart + coordinates.getH();

                        if (x > xStart && x< xEnd && y> yStart && y<yEnd) {
                            if (coordinates.getValue().equals(Coordinates.EMPTY_VALUE)) {
                                
                                coordinates.setValue(currentPlayer);
                                repaint();
                                int result = win3x3.win(currentPlayer);
                                
                                if (endGame !=null) {
                                    endGame.end(currentPlayer, result);
                                }
                                if (result == Continue) {
                                    //xét ngược lại currenPlayer(O X)
                                currentPlayer = currentPlayer.equals(Coordinates.O_VALUE) ? Coordinates.X_VALUE: Coordinates.O_VALUE;
                                }
                                
                               if (matrix[i][j].getValue() != Coordinates.EMPTY_VALUE) {
                                   st.push(new Undo(matrix[i][j]));
                               } 
                            }
                        }
                    }
                }
            }
        });  
        try {
            imgX = ImageIO.read(getClass().getResource("../img/X.png"));
            imgO = ImageIO.read(getClass().getResource("../img/O.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    public String getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void reset(){
        this.initMatrix();
        this.setCurrentPlayer(Coordinates.EMPTY_VALUE);
        repaint();
    }
    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        //Creating a line object         
        int cl = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int x = j * 200;
                int y = i * 200; 

                //cập nhật lại ma trận
                Coordinates coordinates = matrix[i][j];
                coordinates.setX(x);
                coordinates.setY(y);
                coordinates.setW(200);
                coordinates.setH(200);

                Color color = cl % 2 == 0 ? Color.WHITE : Color.GRAY; 
                g.setColor(color);
                g.fillRect(x, y, 200, 200);

                if (coordinates.getValue().equals(Coordinates.X_VALUE)) {
                    BufferedImage img =  imgX ;
                    g.drawImage(img, x, y , 200, 200, this);
                }else if(coordinates.getValue().equals(Coordinates.O_VALUE)){
                    BufferedImage img =  imgO ;
                    g.drawImage(img, x, y , 200, 200, this);
                }
                
                cl++;
            }
           // cl++;
        }
    
        
    }
    public void setEndGame(EndGame endGame) {
        Board3x3.endGame = endGame;
    }
    public static int getN() {
        return N;
    }
    public static int getM() {
        return M;
    }
    public Coordinates[][] getMatrix() {
        return matrix;
    }
    public void setMatrix(Coordinates[][] matrix) {
        this.matrix = matrix;
    }
    public boolean undoo(){
        if (st.isEmpty()) {
            return false;
        }
        Undo un = st.pop();
        coordinates = matrix[un.getCoordinates().getY()/200][un.getCoordinates().getX()/200];
        coordinates.setValue(Coordinates.EMPTY_VALUE);
        repaint();
        return true;
    }
    public void click(){
        File file = new File("src/music/mixkit-arcade-game-jump-coin-216.wav");
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
