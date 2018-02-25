import javax.swing.JFrame;
import java.awt.Dimension;

public class Window extends JFrame {

    public Window(){
        setTitle("WINDOW");
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(640, 480));
        pack();
        setVisible(true);
    }
}
