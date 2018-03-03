import io.jenetics.EnumGene;
import io.jenetics.Mutator;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.ext.SingleNodeCrossover;
import io.jenetics.ext.util.Tree;
import io.jenetics.prog.ProgramGene;

public class Main {

    public static final int PAUSE = 50;

    public static void main(String[] args) {
        Window window = new Window();
        Field field = new Field();
        window.add(field);

        Target target = Target.getStatic();
        field.getDrawables().add(target);

        Cell cell = Cell.getStatic();
        field.getDrawables().add(cell);

        Engine<ProgramGene<Cell>, Double> engine = Engine
                .builder(GeneTest::error, GeneTest.CODEC)
                .minimizing()
                .build();

        ProgramGene<Cell> program = engine.stream()
                .limit(1000)
                .collect(EvolutionResult.toBestGenotype())
                .getGene();

        program.flattenedNodes().forEach(node -> {
            System.out.println(node.getValue().name());
            if (node.getValue().name() != null && !node.getValue().name().equals("Cell")) {
                Command command = Command.valueOf(node.getValue().name());
                Cell c = (Cell) field.getDrawables().get(1);
                if (command.name().equals(Command.UP.name())) {
                    field.getDrawables().set(1, c.up());
                }
                if (command.name().equals(Command.RIGHT.name())) {
                    field.getDrawables().set(1, c.right());
                }
                if (command.name().equals(Command.DOWN.name())) {
                    field.getDrawables().set(1, c.down());
                }
                if (command.name().equals(Command.LEFT.name())) {
                    field.getDrawables().set(1, c.left());
                }
                field.repaint();
                pause();
            }
        });
        System.out.println(Tree.toDottyString(program));


    }

    private static void pause() {
        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
