import io.jenetics.util.RandomRegistry;
import lombok.Builder;
import lombok.Data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

@Data
@Builder
public class Cell implements Drawable, Movable {
    private double x;
    private double y;
    private Ellipse2D.Double bounds;

    @Builder.Default
    private double size = 10;
    @Builder.Default
    private double step = 10d;
    @Builder.Default
    private Color color = Color.BLACK;

    public static Cell getStatic() {
        return Cell.builder().x(500).y(30).build();
    }

    public static Cell getRandom() {
        return Cell.builder()
                .x(RandomRegistry.getRandom().nextDouble() * 640)
                .y(RandomRegistry.getRandom().nextDouble() * 480)
                .build();
    }

    public static Cell[] getRandom(int size) {
        Cell[] cells = new Cell[size];
        for (int i = 0; i < size; i++) {
            cells[i] = Cell.getRandom();
        }
        return cells;
    }

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
            Target target = (Target) drawable;
            moveTo(target.getX(), target.getY());
        }
    }

    @Override
    public void moveTo(double tx, double ty) {
        double distance = Util.getDistance((int) (tx - x), (int) (ty - y));
        if (step < distance) {
            updatePositions(tx, ty);
        } else {
            x = tx;
            y = ty;
        }
    }

    @Override
    public Cell up() {
        y -= step;
        return this;
    }

    @Override
    public Cell right() {
        x += step;
        return this;
    }

    @Override
    public Cell down() {
        y += step;
        return this;
    }

    @Override
    public Cell left() {
        x -= step;
        return this;
    }

    @Override
    public Cell upright() {
        y -= step;
        x += step;
        return this;
    }

    @Override
    public Cell downright() {
        y += step;
        x += step;
        return this;
    }

    @Override
    public Cell downleft() {
        y += step;
        x -= step;
        return this;
    }

    @Override
    public Cell upleft() {
        y -= step;
        x -= step;
        return this;
    }

    @Override
    public Cell move(Integer dir) {
        switch (dir) {
            case 0:
                return up();
            case 1:
                return right();
            case 2:
                return down();
            case 3:
                return left();
            default:
                return this;
        }
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
