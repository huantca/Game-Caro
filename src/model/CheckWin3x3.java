package model;
import view.*;
public class CheckWin3x3 {
    private int M ;
    private int N ;

    private Coordinates matrix[][];

    public CheckWin3x3(Board3x3 board3x3){
        M = Board3x3.getM();
        N = Board3x3.getN();
        matrix = board3x3.getMatrix();

    }
public int win(String player){
    //đường chéo thứ nhất
    if(this.matrix[0][0].getValue().equals(player) && this.matrix[1][1].getValue().equals(player) && this.matrix[2][2].getValue().equals(player)){
        return 1;
    }

    //Đường chéo thứ hai
    if(this.matrix[0][2].getValue().equals(player) && this.matrix[1][1].getValue().equals(player) && this.matrix[2][0].getValue().equals(player)){
        return 1;
    }

    //Dòng thứ 1
    if(this.matrix[0][0].getValue().equals(player) && this.matrix[0][1].getValue().equals(player) && this.matrix[0][2].getValue().equals(player)){
        return 1;
    }

    //Dòng thứ 2
    if(this.matrix[1][0].getValue().equals(player) && this.matrix[1][1].getValue().equals(player) && this.matrix[1][2].getValue().equals(player)){
        return 1;
    }

    //Dòng thứ 3
    if(this.matrix[2][0].getValue().equals(player) && this.matrix[2][1].getValue().equals(player) && this.matrix[2][2].getValue().equals(player)){
        return 1;
    }

    //Cột thứ 1
    if(this.matrix[0][0].getValue().equals(player) && this.matrix[1][0].getValue().equals(player) && this.matrix[2][0].getValue().equals(player)){
        return 1;
    }

    //Cột thứ 2
    if(this.matrix[0][1].getValue().equals(player) && this.matrix[1][1].getValue().equals(player) && this.matrix[2][1].getValue().equals(player)){
        return 1;
    }

    //Cột thứ 3
    if(this.matrix[0][2].getValue().equals(player) && this.matrix[1][2].getValue().equals(player) && this.matrix[2][2].getValue().equals(player)){
        return 1;
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
