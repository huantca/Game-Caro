package model;
import view.*;
public class CheckWin20x20 {
    private Board20x20 board;
    private int M ;
    private int N ;

    private Coordinates matrix[][] ;
    private String currentPlayer ;
    public CheckWin20x20(Board20x20 board20x20){
        this.board = board20x20;
        M = Board20x20.getM();
        N = Board20x20.getN();
        matrix = board20x20.getMatrix();

    }
public int win( int i, int j){
    currentPlayer = board.getCurrentPlayer();
    //Chiều ngang
    int count = 0;
    for(int col = 0; col < M; col++){
        Coordinates coordinates = matrix[i][col];
        if (coordinates.getValue().equals(currentPlayer)) {
            count++;
            if(count == 5){
                // System.out.println("Ngang");
                return 1;
            }
        }else {
            count = 0;
        }
    }


    //Chiều dọc
    count = 0;
    for(int row = 0; row < N; row++){
        Coordinates coordinates = matrix[row][j];
        if (coordinates.getValue().equals(currentPlayer)) {
            count++;
            if(count == 5){
                // System.out.println("Dọc");
                return 1;
            }
        }else {
            count = 0;
        }
    }

    //Chéo trái
    int min = Math.min(i, j);
    int TopI = i - min;
    int TopJ = j - min;
    count = 0;

    for(;TopI < N && TopJ < M; TopI++, TopJ++){
        Coordinates coordinates = matrix[TopI][TopJ];
        if (coordinates.getValue().equals(currentPlayer)) {
            count++;
            if(count == 5){
                // System.out.println("Chéo trái");
                return 1;
            }
        }else {
            count = 0;
        }
    }


    //Chéo phải
    min = Math.min(i, j);
    TopI = i - min;
    TopJ = j + min;
    count = 0;
    if (i > 10 && j<8) {
        int max = Math.max(i, j);
        TopI = i - max;
        TopJ= j + max;
    }

    if(TopJ >= M){
        int du = TopJ - (M - 1);
        TopI = TopI + du;
        TopJ = M - 1;
    }

    for(;TopI < N && TopJ >= 0; TopI++, TopJ--){
        Coordinates coordinates = matrix[TopI][TopJ];
        if (coordinates.getValue().equals(currentPlayer)) {
            count++;
            if(count == 5){
                // System.out.println("Chéo phải");
                return 1;
            }
        }else {
            count = 0;
        }
    }

    if(this.isFull()){
        return 0;
    }

    return 2;
}

private boolean isFull(){
    int number = N * M;

    int k = 0;
    for(int i = 0 ; i < N; i++){
        for(int j = 0 ; j < M; j++){
            Coordinates coordinates = matrix[i][j];
            if(!coordinates.getValue().equals(Coordinates.EMPTY_VALUE)){
                k++;
            }
        }
    }

    return k == number;
}
}
