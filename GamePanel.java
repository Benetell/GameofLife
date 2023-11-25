import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GamePanel extends JPanel{

    public static int SIZE = 30;
    GamePanel(ArrayList<ArrayList<cell>> mx){
        createMX(mx);
        for(int i = 0; i<SIZE; i++) {
            for(int k = 0; k<SIZE; k++) {
                this.add(mx.get(i).get(k));
            }}
        this.setLayout(new GridLayout(SIZE,SIZE,0,0));
        this.setBorder(new TitledBorder("GamePanel"));
    }
    static public void createMX(ArrayList<ArrayList<cell>> mx){
        for(int i = 0; i<SIZE; i++) {
            mx.add(i, new ArrayList<>());
            for(int k = 0; k < SIZE; k++){
                mx.get(i).add(k,new cell(i,k));
                mx.get(i).get(k).addActionListener(new CellPressed());
            }
        }
    }
    /*
     * a cell kattintásra dob egy "e" ActionEvenet-et, amit a getSource visszafejt.
     * ekkor kiíródik a megnyomott gomb
     */
    static class CellPressed implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                cell clickedButton = (cell) e.getSource();
                if(clickedButton.aliveStatus()==0)clickedButton.setAlive(1);
                else clickedButton.setAlive(0);
            }

        }
}
