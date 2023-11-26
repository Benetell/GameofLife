import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JButton;
/*
Ez az osztály egy custom JButton, amiből a pálya cellái állnak.
 */
public class cell extends JButton {
    private int row,col;
    private int alive = 0;
    /*
    Inicialiálja a cellát
     */
    public cell(int row, int col) {
        super();
        setPreferredSize(new Dimension(20,20));
        this.row = row;
        this.col = col;
        this.setAlive(0);
    }
    /*
    cella színének settere
     */
    public void setColor(String s) {
        switch(s) {
            case "black":
                this.setBackground(Color.black);
                break;
            case "white":
                this.setBackground(Color.white);
                break;
            case "gray1":
                this.setBackground(new Color(179, 179, 179));
                break;
             case "gray2":
                    this.setBackground(new Color(134, 134, 134));
                    break;
             case "gray3":
                    this.setBackground(new Color(115, 115, 115));
                    break;
             case "gray4":
                    this.setBackground(new Color(77, 77, 77));
                    break;
             case "gray6":
                    this.setBackground(new Color(38,38,38));
                    break;
        }
    }
    /*
    visszaadja a cella jelenlegi állapotát
     */
    public int aliveStatus() {
        return alive;
    }
    /*
    a cella állapotának settere, az 1-es állapot azt jelenti, hogy a cella életben van, a többi pedig azt, hogy mennyi ideje halott a cella
     */
    public void setAlive(int i) {
        alive = i;
        if(alive == 0) {
            this.setColor("white");
        }else if(alive == 1) {
            this.setColor("black");
        }else if(alive == 2) {
            this.setColor("gray6");
        }else if(alive == 3) {
            this.setColor("gray4");
        }else if(alive == 4) {
            this.setColor("gray3");
        }else if(alive == 5) {
            this.setColor("gray2");
        }else if(alive == 6) {
            this.setColor("gray1");
        }
    }
    /*
    a paraméterként kapott mátrixban megnézi a cella élő szomszédjainak számát
     */
    public int countNeighbours(ArrayList<ArrayList<cell>> mx) {
        int count = 0;
        if(row == 29){
            int[][] nums = {{0,1},{-1,1},{-1,0},{0,-1},{-1,-1}};
            for(int i = 0; i < 5; i++)
                try {
                    if(mx.get(row+nums[i][0]).get(col+nums[i][1]).aliveStatus() == 1) {
                        count++;
                    }
                }catch(IndexOutOfBoundsException ignored){}

        }else {
            int[][] nums = {{0, 1}, {1, 1}, {-1, 1}, {1, 0}, {-1, 0}, {0, -1}, {-1, -1}, {1, -1}};
            for (int i = 0; i < 8; i++)
                try {
                    if (mx.get(row + nums[i][0]).get(col + nums[i][1]).aliveStatus() == 1) {
                        count++;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }

        }
        //if(count>0)System.out.println("i: "+row+" " + "k: "+col + " " +count);
        return count;
    }
}

