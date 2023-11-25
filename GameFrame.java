

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

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
    public static JTextField bs = new JTextField("B/S rate",15);

    public static JTextPane speed = new JTextPane();


    public GameFrame(ArrayList<ArrayList<cell>> _mx){
        speed.setText("speed: "+MainPanel.viewSpeed);
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

        JPanel info = new JPanel();
        GamePanel grid = new GamePanel(mx);
        JPanel control = new ControlPanel (mx);
        main = new MainPanel(mx);

        info.setLayout(new BorderLayout());
        info.add(bs,BorderLayout.WEST);
        info.add(speed, BorderLayout.EAST);
        control.setLayout(new GridLayout(2,2));
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

