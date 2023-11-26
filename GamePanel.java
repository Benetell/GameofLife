import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
/*
   ez az osztaly valósítja meg a játék Jpanelét, benne a mátrixal
 */
public class GamePanel extends JPanel{

    public static int SIZE = 30;
    static ArrayList<ArrayList<cell>> mx = new ArrayList<>();
    /*
    létrehozza a cell mátrixot és megjeleníti
     */
    GamePanel(ArrayList<ArrayList<cell>> _mx){
        mx = _mx;
        createMX(_mx);
        for(int i = 0; i<SIZE; i++) {
            for(int k = 0; k<SIZE; k++) {
                this.add(_mx.get(i).get(k));
            }}
        this.setLayout(new GridLayout(SIZE,SIZE,0,0));
        this.setBorder(new TitledBorder("GamePanel"));
    }
    /*
    inicializálja a paraméteréül kapott mátrixot és annak gombjait
     */
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
     a cell kattintásra dob egy "e" ActionEvenet-et, amit a getSource viszafejt, a gombnyomás így a 0 és 1 közti állapotát váltja a cellnek
    */
    static class CellPressed implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                cell clickedButton = (cell) e.getSource();
                if(clickedButton.aliveStatus()==0)clickedButton.setAlive(1);
                else clickedButton.setAlive(0);
            }

    }
}
