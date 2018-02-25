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

        Target target = Target.builder()
                .x(Math.random() * window.getWidth())
                .y(Math.random() * window.getHeight())
                .build();
        field.getDrawables().add(target);

        while (true) {
            field.getDrawables().forEach(drawable -> {
                drawable.moveTo(target);
                field.repaint();
            });
        }
    }
}
