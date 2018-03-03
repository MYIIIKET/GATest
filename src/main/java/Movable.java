public interface Movable {
    void moveTo(Drawable drawable);

    void moveTo(double x, double y);

    Cell up();
    Cell right();
    Cell down();
    Cell left();
    Cell upright();
    Cell downright();
    Cell downleft();
    Cell upleft();
    Cell move(Integer dir);
}
