import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Field extends JPanel {

    private List<Drawable> drawables = new ArrayList<>();

    public Field() {
        setBackground(Color.WHITE);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawables.forEach(drawable -> drawable.draw(g));
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }
}
