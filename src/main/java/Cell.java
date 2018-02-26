import lombok.Builder;
import lombok.Data;

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
    private double step = 10d;
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
        if (drawable instanceof Target) {
            moveTo((Target) drawable);
        }
    }

    @Override
    public void moveTo(double tx, double ty) {
        double distance = Util.getDistance((int) tx - (int) x, (int) ty - (int) y);
        if (step < distance) {
            updatePositions(tx, ty);
        } else {
            x = tx;
            y = ty;
        }
    }

    private void moveTo(Target drawable) {
        moveTo(drawable.getX(), drawable.getY());
    }

    private void updatePositions(double tX, double tY) {
        double dirX;
        double dirY;
        double dir;
        dirX = (tX - x);
        dirY = (tY - y);
        dir = Math.atan2(dirY, dirX);

        x += Math.cos(dir) * step;
        y += Math.sin(dir) * step;
    }

}
