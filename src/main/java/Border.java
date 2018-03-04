import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

@Data
@Builder
public class Border implements Drawable {
    private double x;
    private double y;
    private double width;
    private double height;
    @Builder.Default
    private Color color = Color.BLUE;

    private Rectangle2D.Double bounds;

    public static Border getStatic() {
        return Border.builder()
                .x(200)
                .y(100)
                .width(400)
                .height(40)
                .build();
    }

    public Rectangle2D.Double getBounds() {
        if (bounds == null) {
            return bounds = new Rectangle2D.Double(x, y, width, height);
        }
        return bounds;
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(color);
        graphics2D.fill(new Rectangle2D.Double(x, y, width, height));
    }
}
