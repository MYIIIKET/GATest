public class Util {
    public static double getDistance(int dx, int dy) {
        int min;
        int max;
        if (dx < 0) dx = -dx;
        if (dy < 0) dy = -dy;
        if (dx < dy) {
            min = dx;
            max = dy;
        } else {
            min = dy;
            max = dx;
        }
        return (((max << 8) + (max << 3) - (max << 4) - (max << 1) +
                (min << 7) - (min << 5) + (min << 3) - (min << 1)) >> 8);
    }
}
