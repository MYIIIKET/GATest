import io.jenetics.MultiPointCrossover;
import io.jenetics.Mutator;
import io.jenetics.RouletteWheelSelector;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.ext.SingleNodeCrossover;
import io.jenetics.ext.util.Tree;
import io.jenetics.prog.ProgramGene;

import java.io.IOException;
import java.util.Collections;

public class Main {

    public static final int PAUSE = 50;

    public static void main(String[] args) throws IOException {
        Window window = new Window();
        Field field = new Field();
        window.add(field);

        Border border = Border.getStatic();
        field.getDrawables().add(border);

        Target target = Target.getStatic();
        field.getDrawables().add(target);

        Cell cell = Cell.getStatic();
        field.getDrawables().add(cell);

        Engine<ProgramGene<Cell>, Double> engine = Engine
                .builder(GeneTest::error, GeneTest.CODEC)
                .minimizing()
                .selector(new RouletteWheelSelector<>())
                .alterers(new Mutator<>(0.99), new SingleNodeCrossover<>())
                .populationSize(1000)
                .build();

        ProgramGene<Cell> program = engine.stream()
                    .limit(100)
                    .collect(EvolutionResult.toBestGenotype())
                    .getGene();

        System.out.println("Ready...");
//        System.in.read();
        program.flattenedNodes().forEach(node -> {
            if (node.getValue().name() != null && !node.getValue().name().equals("Cell")) {
                Cell[] c = {(Cell) field.getDrawables().get(2)};
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
