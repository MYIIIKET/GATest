import lombok.Builder;
import lombok.Data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

@Data
@Builder
public class Target implements Drawable {
    private double x;
    private double y;
    private Rectangle2D.Double bounds;

    @Builder.Default
    private double size = 5;
    @Builder.Default
    private double step = 0;
    @Builder.Default
    private Color color = Color.RED;

    public static Target getStatic() {
        return Target.builder().x(320).y(240).build();
    }

    public Rectangle2D.Double getBounds() {
        if (bounds == null) {
            return bounds = new Rectangle2D.Double(x, y, size, size);
        }
        return bounds;
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(color);
        graphics2D.fill(new Rectangle2D.Double(x, y, size, size));
    }

}
