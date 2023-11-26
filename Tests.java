import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class Tests {
    ArrayList<ArrayList<cell>> mx = new ArrayList<>();
    @Before
    public void setUp(){
        new GameFrame(mx);
    }
    @Test
    public void bsTest(){
        GameFrame.bs.setText("37/12");
        ControlPanel.bs.doClick();
        ArrayList<Integer> b = new ArrayList<>();
        b.add(3);
        b.add(7);
        ArrayList<Integer> s = new ArrayList<>();
        s.add(1);
        s.add(2);
        assertTrue("b ertekek beolvasasa",MainPanel.b.containsAll(b));
        assertTrue("s ertekek beolvasasa",MainPanel.s.containsAll(s));
    }
    @Test
    public void jcbTest(){
           ControlPanel.faster.doClick();
           assertEquals("speed modban jol reagal a fasterre", "speed: 50", GameFrame.speed.getText());
           GameFrame.jcb.setSelectedItem("sleep");
           assertEquals("sleep modban jol reagal a fasterre", "speed: 450", GameFrame.speed.getText());

    }

    @Test
    public void neighbourTestDuringRun(){
        mx.get(0).get(5).doClick();
        ControlPanel.start.doClick();
        assertEquals("",1,mx.get(0).get(5).aliveStatus());
        assertEquals("",1,mx.get(0).get(4).countNeighbours(mx));
        ControlPanel.stop.doClick();
    }
    @Test
    public void szabalymodositasJatekAlatt(){
        ControlPanel.start.doClick();
        assertFalse("futas alatt allitani",GameFrame.bs.isEnabled());
        ControlPanel.stop.doClick();
        assertTrue("futason kivul allitani",GameFrame.bs.isEnabled());
    }
    @Test
    public void maxspeed(){
        assertTrue("gyorsitasni ha nem erte el a maximumot",ControlPanel.faster.isEnabled());
        MainPanel.speed = 50;
        ControlPanel.faster.doClick();
        assertFalse("gyorsitani ha eletre a maximumot",ControlPanel.faster.isEnabled());
    }

    @Test
    public void neighbourTestOneCell(){
        ArrayList<ArrayList<cell>> _mx = new ArrayList<>();
        _mx.add(new ArrayList<>());
        _mx.get(0).add(new cell(0,0));
        assertEquals("",0,_mx.get(0).get(0).countNeighbours(_mx));
    }

    @Test
    public void serializationTest() throws IOException {
        String s = "[[0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]]";
        mx.get(0).get(1).doClick();
        GameFrame.save.doClick();
        BufferedReader br = new BufferedReader(new FileReader("table.json"));
        assertEquals("",br.readLine(),s);
        br.close();
    }

    @Test
    public void borderTest() {

    }
}
