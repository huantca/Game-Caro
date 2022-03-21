package view;
import javax.swing.JOptionPane;

public class Option {

    public void JOption(){
        String[] arr = {"3x3","20x20"};
        //JOptionPane jOptionPane = new JOptionPane();
        int selectBox = JOptionPane.showOptionDialog(null,"chọn bàn choi", "MENU..", 0, JOptionPane.QUESTION_MESSAGE,null , arr, null);
        if (selectBox == 0) {
            Caro3 caro3x3 = new Caro3();
            caro3x3.caro();
        }
        else if(selectBox==1){
            Caro20 caro20 = new Caro20();
            caro20.caro();
        }
    }

    
}
