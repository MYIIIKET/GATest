import io.jenetics.prog.op.Op;

import java.util.function.Function;

public enum Command implements Op<Cell> {
    UP(c->c[0].up()),
    RIGHT(c->c[0].right()),
    DOWN(c->c[0].down()),
    LEFT(c->c[0].left());

    Function<Cell[], Cell> function;

    Command(Function<Cell[], Cell> function) {
        this.function = function;
    }

    Command(int dir, Cell cell){

    }

    @Override
    public int arity() {
        return 1;
    }

    @Override
    public Cell apply(Cell[] cells) {
        return function.apply(cells);
    }
}
