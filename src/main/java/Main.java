
public class Main {

    public static final int PAUSE = 200;

    public static void main(String[] args) {
        Window window = new Window();
        Field field = new Field();
        window.add(field);
        for (int i = 0; i < 10; i++) {
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
            pause();
        }
    }

    private static void pause() {
        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
