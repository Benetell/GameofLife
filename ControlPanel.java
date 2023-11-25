import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ControlPanel extends JPanel {
    static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    static ArrayList<ArrayList<cell>> mx;
    public static JButton faster = new JButton("faster");
    public ControlPanel(ArrayList<ArrayList<cell>> _mx) {
        mx = _mx;
        JButton start = new JButton("start");
        start.addActionListener(new startListener());
        this.add(start);

        JButton stop = new JButton("stop");
        stop.addActionListener(new stopListener());
        this.add(stop);

        faster.addActionListener(new fasterListener());
        this.add(faster);

        JButton slower = new JButton("slower");
        slower.addActionListener(new slowerListener());
        this.add(slower);

        JButton random= new JButton("random");
        random.addActionListener(new randomListener());
        this.add(random);

        JButton bs= new JButton("set bs");
        bs.addActionListener(new bsListener());
        this.add(bs);


        this.setLayout(new FlowLayout());
        this.setBorder(new TitledBorder("ControlPanel"));

    }
    static class startListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainPanel.running = 1;
            System.out.println("start");
        }

    }
    static class bsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = GameFrame.bs.getText();
            System.out.println(text);
            if(text.equals( "B/S rate")) return;
            MainPanel.b.clear();
            MainPanel.s.clear();
            String[] bs = text.split("/");
            //alapbol 23/3
            int b = Integer.parseInt(bs[0]);
            int s = Integer.parseInt(bs[1]);
            while (b > 0) {
                MainPanel.b.add(b%10);
                b = b / 10;
            }
            while(s>0){
                MainPanel.s.add(s%10);
                s = s / 10;
            }
        }

    }
    static class stopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainPanel.running = 0;
            System.out.println("stop");

        }

    }
    static class fasterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(MainPanel.speed>50) {
                MainPanel.speed -= 50;
                MainPanel.viewSpeed += 50;
            }else{
                faster.setEnabled(false);
            }
            if (GameFrame.jcb.getSelectedIndex() == 1) GameFrame.speed.setText("speed: " + MainPanel.speed);
            if (GameFrame.jcb.getSelectedIndex() == 0) GameFrame.speed.setText("speed: " + MainPanel.viewSpeed);
        }
    }
    static class slowerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
                MainPanel.speed += 50;
            MainPanel.viewSpeed -= 50;
            if(!faster.isEnabled()) {
                faster.setEnabled(true);
            }
            if (GameFrame.jcb.getSelectedIndex() == 1) GameFrame.speed.setText("speed: " + MainPanel.speed);
            if (GameFrame.jcb.getSelectedIndex() == 0) GameFrame.speed.setText("speed: " + MainPanel.viewSpeed);

        }
    }
    static class randomListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            MainPanel.random = 1;
        }
    }
    //at jmenube
    static class saveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<ArrayList<cell>> finalMx = mx;
            ArrayList<ArrayList<Integer>> values = new ArrayList<>();
            for(int i = 0; i < GamePanel.SIZE; i++) {
                values.add(i, new ArrayList<>());
                for (int j = 0; j < GamePanel.SIZE; j++)
                    values.get(i).add(j, mx.get(i).get(j).aliveStatus());
            }
            FileWriter writer = null;
            try {
                writer = new FileWriter("table.json");
                gson.toJson(values, writer);
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }



        }
    }
    //jmenu
    static class loadListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //Type userListType = new TypeToken<ArrayList<ArrayList<cell>>>(){}.getType();
                List<String> lines = Files.readAllLines(Paths.get("table.json"), StandardCharsets.UTF_8);
                String a = lines.toArray(new String[lines.size()])[0];
                String[] arr = a.split("]");
                int row = 0;
                int col = 0;
                for(String i : arr) {
                    for (int j = 2; j < i.length(); j++) {
                        if (j % 2 == 0) {
                            mx.get(row).get(col).setAlive(Character.getNumericValue(i.charAt(j)));
                            col++;
                        }
                    }
                    col = 0;
                    row++;
                }


            } catch(Exception ignored) {
            }

        }
    }
}
