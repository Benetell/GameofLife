

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;
/*
ebbe a panelba ágyazom a többit, hogy együtt tudjam őket kezelni és itt valósítom meg a játék algoritmusát
 */
public class MainPanel extends JPanel implements Runnable{
    public static volatile int running = 0;
    public static int speed = 500;
    public static int viewSpeed = 0;
    public static int random = 0;
    public static ArrayList<Integer> b = new ArrayList<>();

    public static ArrayList<Integer> s = new ArrayList<>();
    private Thread gameThread;
    public static ArrayList<ArrayList<cell>> mx;
    MainPanel(ArrayList<ArrayList<cell>> _mx){
        b.add(3);
        s.add(2);
        s.add(3);
        mx = _mx;
    }
    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while(gameThread!=null) {
            if(random == 1){
                for(int row = 0; row<GamePanel.SIZE; row++){
                    for(int col = 0; col<GamePanel.SIZE; col++){
                        mx.get(row).get(col).setAlive(new Random().nextInt(2));
                    }
                    random = 0;
                }
            }
            GameFrame.bs.setEnabled(true);
            if(running == 1) {
                GameFrame.bs.setEnabled(false);
                GamePanel.createMX(GameFrame.pastmx);
                for(int row = 0; row<GamePanel.SIZE; row++){
                    for(int col = 0; col<GamePanel.SIZE; col++){
                        int alive = GameFrame.mx.get(row).get(col).aliveStatus();
                        GameFrame.pastmx.get(row).get(col).setAlive(alive);
                    }
                }


                for(int row = 0; row<GamePanel.SIZE; row++) {
                    for(int column = 0; column<GamePanel.SIZE; column++) {

                        int neighbours = GameFrame.pastmx.get(row).get(column).countNeighbours(GameFrame.pastmx);
                        //System.out.println("[" + GameFrame.mx.get(row).get(column).getRow() + "][" + GameFrame.mx.get(row).get(column).getCol() + "]");

                        if(GameFrame.pastmx.get(row).get(column).aliveStatus()==1) {
                            if(!(s.contains(neighbours))){
                                GameFrame.mx.get(row).get(column).setAlive(GameFrame.mx.get(row).get(column).aliveStatus()+1);
                            }
                        }else if(b.contains(neighbours)) GameFrame.mx.get(row).get(column).setAlive(1);

                        if(GameFrame.mx.get(row).get(column).aliveStatus()==6) {
                            GameFrame.mx.get(row).get(column).setAlive(0);
                        }
                        if(GameFrame.mx.get(row).get(column).aliveStatus() > 1)
                            GameFrame.mx.get(row).get(column).setAlive(GameFrame.mx.get(row).get(column).aliveStatus()+1);
                    }
                }

                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
