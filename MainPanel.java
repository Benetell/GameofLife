

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
    public static Thread gameThread;
    public static ArrayList<ArrayList<cell>> mx;
    /*
    inicializálja a b és s listákat (az alapjáték b/s rate-je 3/23
    b: ha pl a 3as benne van, akkor ha egy cellnek 3 élő szomszédja van, és halott akkor megszületik
    s: ha pl a 2es benne van, akor ha egy cellnek 2 élő szomszédja van és él, akor életben marad
     */
    MainPanel(ArrayList<ArrayList<cell>> _mx){
        b.add(3);
        s.add(2);
        s.add(3);
        mx = _mx;
    }
    /*
    létrehozza a szálat amin fut a szimuláció
     */
    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    /*
    elindítja a szálat amin fut a szimuláció a b/s listákban meghatározott szabályok szerint
     */
    @Override
    public void run() {
        //amíg fut a szál
        while(gameThread!=null) {

            //a random gomb megnyomásával ez az érték 1-re vált és létrejön egy random mátrix
            if(random == 1){
                for(int row = 0; row<GamePanel.SIZE; row++){
                    for(int col = 0; col<GamePanel.SIZE; col++){
                        mx.get(row).get(col).setAlive(new Random().nextInt(2));
                    }
                    random = 0;
                }
            }
            //elindul a szimuláció is
            if(running == 1) {
                //elmenti a pastmxba az éppen aktuális állapotot, mert az alapján kell meghatározni a következő állapotot
                GamePanel.createMX(GameFrame.pastmx);
                for(int row = 0; row<GamePanel.SIZE; row++){
                    for(int col = 0; col<GamePanel.SIZE; col++){
                        int alive = GameFrame.mx.get(row).get(col).aliveStatus();
                        GameFrame.pastmx.get(row).get(col).setAlive(alive);
                    }
                }
                //a fő algoritmus
                for(int row = 0; row<GamePanel.SIZE; row++) {
                    for(int column = 0; column<GamePanel.SIZE; column++) {
                        int neighbours = GameFrame.pastmx.get(row).get(column).countNeighbours(GameFrame.pastmx);

                        if(GameFrame.pastmx.get(row).get(column).aliveStatus()==1) {
                            //ha az előző körben élt a cella és a szomszédainak száma nem szerepel az s listában, akkor legyen 2-es állapotú
                            if(!(s.contains(neighbours))){
                                GameFrame.mx.get(row).get(column).setAlive(2);
                            }
                        //ha nem élet, és a szomszédainak száma benne van a b listában, akkor szülessen meg
                        }else if(b.contains(neighbours)) GameFrame.mx.get(row).get(column).setAlive(1);

                        //ha halottak tovább halványlnak
                        if(GameFrame.mx.get(row).get(column).aliveStatus()==6) {
                            GameFrame.mx.get(row).get(column).setAlive(0);
                        }
                        if(GameFrame.mx.get(row).get(column).aliveStatus() > 1)
                            GameFrame.mx.get(row).get(column).setAlive(GameFrame.mx.get(row).get(column).aliveStatus()+1);
                    }
                }
                //minél nagyobb az alvás ideje annál lassabb lesz a szimuláció
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
