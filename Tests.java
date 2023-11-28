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
        MainPanel.speed = 500;
        assertEquals("speed modban jol reagal a fasterre", "speed: 0", GameFrame.speed.getText());
        ControlPanel.faster.doClick();
        assertEquals("speed modban jol reagal a fasterre", "speed: 100", GameFrame.speed.getText());
        GameFrame.jcb.setSelectedItem("sleep");
        assertEquals("sleep modban jol reagal a fasterre", "sleep: 400", GameFrame.speed.getText());
    }

    @Test
    public void neighbourTestDuringRun(){
        mx.get(0).get(5).doClick();
        ControlPanel.start.doClick();
        assertEquals("kattintassal 1es allapotba all",1,mx.get(0).get(5).aliveStatus());
        assertEquals("jol szamolja az elozo korbol az ujat",1,mx.get(0).get(4).countNeighbours(mx));
        ControlPanel.stop.doClick();
    }
    @Test
    public void szabalymodositasJatekAlatt(){
        ControlPanel.start.doClick();
        assertFalse("nem lehet futas alatt allitani",GameFrame.bs.isEnabled());
        ControlPanel.stop.doClick();
        assertTrue("lehet futason kivul allitani",GameFrame.bs.isEnabled());
    }
    @Test
    public void maxspeed(){
        assertTrue("lehet gyorsitasni ha nem erte el a maximumot",ControlPanel.faster.isEnabled());
        MainPanel.speed = 50;
        ControlPanel.faster.doClick();
        assertFalse("nem lehet gyorsitani ha elerte a maximumot",ControlPanel.faster.isEnabled());
    }

    @Test
    public void minspeed(){
        assertTrue(" lehet lassitani ha nem elerte a minimumot",ControlPanel.slower.isEnabled());
        MainPanel.speed = 950;
        ControlPanel.slower.doClick();
        assertFalse("nem lehet lassitani ha elerte a minimumot",ControlPanel.slower.isEnabled());
    }
    @Test
    public void neighbourTestOneCell(){
        ArrayList<ArrayList<cell>> _mx = new ArrayList<>();
        _mx.add(new ArrayList<>());
        _mx.get(0).add(new cell(0,0));
        assertEquals("szomszedok nelkul nem dob hibat a szomszed szamlalas",0,_mx.get(0).get(0).countNeighbours(_mx));
    }

    @Test
    public void serializationTest() throws IOException {
        GamePanel.setSIZE(30);
        String s = "[[0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]]";
        mx.get(0).get(1).doClick();
        GameFrame.save.doClick();
        BufferedReader br = new BufferedReader(new FileReader("table.json"));
        assertEquals("megfelelo formatumban hozza letre a jsont",br.readLine(),s);
        br.close();
    }

    @Test
    public void sizeTest() {
        GamePanel.setSIZE(101);
        assertEquals("tulmeretezesnel 100 a meret", GamePanel.SIZE,100);
        GamePanel.setSIZE(0);
        assertEquals("alulmeretezesnel 100 a meret", GamePanel.SIZE,100);
    }

    @Test
    public void startTest(){
        assertTrue("kattintható a start ha nem fut",ControlPanel.start.isEnabled());
        ControlPanel.start.doClick();
        assertFalse("nem kattinthato a start ha fut",ControlPanel.start.isEnabled());
        ControlPanel.stop.doClick();
    }
}
