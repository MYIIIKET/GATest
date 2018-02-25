import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

@Data
@Builder
public class Target implements Drawable {
    private double x;
    private double y;
    private Ellipse2D.Double bounds;

    @Builder.Default
    private double size = 10;
    @Builder.Default
    private double step = 0;
    @Builder.Default
    private Color color = Color.RED;

    public Ellipse2D.Double getBounds() {
        if (bounds == null) {
            return bounds = new Ellipse2D.Double(x, y, size, size);
        }
        return bounds;
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(color);
        graphics2D.fill(new Rectangle2D.Double(x, y, size, size));
    }

    @Override
    public void moveTo(Drawable drawable) {

    }
}
