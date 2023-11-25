

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/*
    egy custom JFrame osztály, ami megvalósítja a UI-t
    tartalmaz:
 */
public class GameFrame extends JFrame{
    static JMenuBar mb;

    static JMenu x;

    static JMenuItem save, load;
    MainPanel main;
    public static ArrayList<ArrayList<cell>> mx;
    public static ArrayList<ArrayList<cell>> pastmx = new ArrayList<>();



    public GameFrame(ArrayList<ArrayList<cell>> _mx){
//harom osztaly 10 fuggvenye teszt
        mx=_mx;
        mb = new JMenuBar();
        x = new JMenu("menu");
        save = new JMenuItem("save");
        load = new JMenuItem("load");
        save.addActionListener(new ControlPanel.saveListener());
        load.addActionListener(new ControlPanel.loadListener());
        x.add(save);
        x.add(load);
        mb.add(x);
        this.setJMenuBar(mb);

        this.setSize(800,800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        InfoPanel info = new InfoPanel();
        GamePanel grid = new GamePanel(mx);
        JPanel control = new ControlPanel (mx);
        main = new MainPanel(mx);
        control.setLayout(new GridLayout(2,2));
        info.setLayout(new BorderLayout());

        main.setLayout(new BorderLayout());
        main.add(info,BorderLayout.NORTH);
        main.add(grid,BorderLayout.CENTER);
        main.add(control,BorderLayout.SOUTH);
        this.add(main);
        this.setVisible(true);
    }
    public void start(){
        main.startThread();
    }

}
