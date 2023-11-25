import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InfoPanel extends JPanel {
    public static JTextField bs;
    public static JTextPane speed = new JTextPane();
    public InfoPanel(){
        bs = new JTextField("B/S rate",15);
        speed.setText("speed: "+MainPanel.viewSpeed);
        this.setLayout(new BorderLayout());
        this.add(bs, BorderLayout.WEST);
        this.add(speed,BorderLayout.EAST);
    }
}
