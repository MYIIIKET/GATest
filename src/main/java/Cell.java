import lombok.Builder;
import lombok.Data;
import lombok.experimental.var;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

@Data
@Builder
public class Cell implements Drawable {
    private double x;
    private double y;
    private Ellipse2D.Double bounds;

    @Builder.Default
    private double size = 10;
    @Builder.Default
    private double step = 1.0d;
    @Builder.Default
    private Color color = Color.BLACK;

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
        graphics2D.fill(new Ellipse2D.Double(x, y, size, size));
    }

    @Override
    public void moveTo(Drawable drawable) {
        if (drawable instanceof Cell) {
            move((Cell) drawable);
        }
    }

    private void move(Cell drawable) {
        double distance;
        double tX = drawable.getX();
        double tY = drawable.getY();
        distance = getDistance(tX, tY);
        updatePositions(distance, tX, tY);
    }

    private void updatePositions(double distance, double tX, double tY) {
        double dirX;
        double dirY;
        dirX = (tX - x) / distance;
        dirY = (tY - y) / distance;

        x += step * dirX;
        y += step * dirY;
    }

    private double getDistance(double tX, double tY) {
        double distance;
        double tXabs = Math.abs(tX);
        double tYabs = Math.abs(tY);
        distance = (1007 / 1024) * Math.max(tXabs, tYabs)
                + 441 / 1024 * Math.min(tXabs, tYabs);
        if (Math.max(tXabs, tYabs) < 16 * Math.min(tXabs, tYabs)) {
            distance -= (5 * Math.max(tXabs, tYabs)) / 128;
        }
        return distance;
    }
}
