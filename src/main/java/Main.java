import io.jenetics.Mutator;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.ext.SingleNodeCrossover;
import io.jenetics.ext.util.Tree;
import io.jenetics.prog.ProgramGene;

import java.util.Collections;

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
                .alterers(new Mutator<>(), new SingleNodeCrossover<>())
                .populationSize(500)
                .build();

        ProgramGene<Cell> program = engine.stream()
                    .limit(100)
                    .collect(EvolutionResult.toBestGenotype())
                    .getGene();

        program.flattenedNodes().forEach(node -> {
            if (node.getValue().name() != null && !node.getValue().name().equals("Cell")) {
                Cell[] c = {(Cell) field.getDrawables().get(1)};
                node.getValue().apply(c);
                field.repaint();
                pause();
            }
        });
        System.out.println(program.size());


    }

    private static void pause() {
        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
