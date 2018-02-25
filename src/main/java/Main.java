import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        Field field = new Field();
        window.add(field);
        for (int i = 0; i < 1; i++) {
            field.getDrawables().add(Cell.builder()
                    .x(Math.random() * window.getWidth())
                    .y(Math.random() * window.getHeight())
                    .build());
        }

        Cell cell = Cell.builder()
                .x(window.getWidth() / 2)
                .y(window.getHeight() / 2)
                .color(Color.RED)
                .build();
        field.getDrawables().add(cell);

        while (true) {
            field.getDrawables().forEach(drawable -> {
                drawable.moveTo(cell);
                field.repaint();
            });
        }
    }
}
