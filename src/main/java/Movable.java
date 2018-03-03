public interface Movable {
    void moveTo(Drawable drawable);

    void moveTo(double x, double y);

    Cell up();
    Cell right();
    Cell down();
    Cell left();
    Cell move(Integer dir);
}
