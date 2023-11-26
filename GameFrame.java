

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

/*
    egy custom JFrame osztály, ami megvalósítja a UI-t
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
    public static JComboBox jcb;
//harom osztaly 10 fuggvenye teszt

    /*
    létrehozza a paneleket, egy MainPanelbe teszi őket és azt hozzáadja a framehez
     */
    public GameFrame(ArrayList<ArrayList<cell>> _mx){
        mx=_mx;

        //menubar a mentésnek és a betöltésnek
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

        //felső panel a born/survive rate beállításához, a speed labelhez és annak a mértékegységéhez
        speed.setText("speed: "+MainPanel.viewSpeed);
        JPanel info = new JPanel();
        info.setLayout(new BorderLayout());
        String[] choices = {"speed","sleep"};
        jcb = new JComboBox(choices);
        jcb.addActionListener(e -> {
            if(e.getSource()==jcb) {
                if (jcb.getSelectedIndex() == 1) speed.setText("speed: " + MainPanel.speed);
                if (jcb.getSelectedIndex() == 0) speed.setText("speed: " + MainPanel.viewSpeed);
            }
        });
        info.add(bs,BorderLayout.WEST);
        info.add(speed, BorderLayout.CENTER);
        info.add(jcb, BorderLayout.EAST);

        //control panel
        JPanel control = new ControlPanel (mx);
        control.setLayout(new GridLayout(2,2));

        //game panel
        GamePanel grid = new GamePanel(mx);

        //main panel
        main = new MainPanel(mx);
        main.setLayout(new BorderLayout());
        main.add(info,BorderLayout.NORTH);
        main.add(grid,BorderLayout.CENTER);
        main.add(control,BorderLayout.SOUTH);

        //frame beállításai
        this.add(main);
        this.setSize(800,800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    //ezzel készíti elő a frame a MainPanelbeli szálat
    public void start(){
        main.startThread();
    }

}

