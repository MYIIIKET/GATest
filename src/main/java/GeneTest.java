import io.jenetics.Genotype;
import io.jenetics.engine.Codec;
import io.jenetics.prog.ProgramChromosome;
import io.jenetics.prog.ProgramGene;
import io.jenetics.prog.op.EphemeralConst;
import io.jenetics.prog.op.Op;
import io.jenetics.prog.op.Var;
import io.jenetics.util.ISeq;

import java.util.Arrays;

public class GeneTest {

    public static final Target SAMPLE = Target.getStatic();
    public static final Border BORDER = Border.getStatic();

    public static final ISeq<Op<Cell>> OPERATIONS = ISeq.of(
            Command.UP,
            Command.RIGHT,
            Command.DOWN,
            Command.LEFT
//            Command.UPRIGHT,
//            Command.DOWNRIGHT,
//            Command.DOWNLEFT,
//            Command.UPLEFT
    );

    public static final ISeq<Op<Cell>> TERMINALS = ISeq.of(
            Var.of("Cell", 0)
    );

    public static final double error(final ProgramGene<Cell> program) {
        double result = 0;
        Cell[] cell = {Cell.getStatic()};
        result += Util.getDistance((int) (SAMPLE.getX() - program.eval(Cell.getStatic()).getX()),
                (int) (SAMPLE.getY() - program.eval(Cell.getStatic()).getY()));
        for (ProgramGene<Cell> node : program.flattenedNodes()) {
            if (node.getValue().name() != null && !node.getValue().name().equals("Cell")) {
                node.getValue().apply(cell);
                if (BORDER.getBounds().contains(cell[0].getX(), cell[0].getY())) {
                    result += 1000;
                }
            }
        }
        result += program.size()*0.00001;
        return result;
    }

    public static final Codec<ProgramGene<Cell>, ProgramGene<Cell>> CODEC = Codec.of(
            Genotype.of(ProgramChromosome.of(
                    200,
                    OPERATIONS,
                    TERMINALS
            )),
            Genotype::getGene
    );

    public static void main(String[] args) {
    }
}
