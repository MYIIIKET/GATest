import io.jenetics.prog.op.Op;

import java.util.Collections;
import java.util.function.Function;

public enum Command implements Op<Cell> {
    UP(c -> c[0].up()),
    RIGHT(c -> c[0].right()),
    DOWN(c -> c[0].down()),
    LEFT(c -> c[0].left()),
    UPRIGHT(c->c[0].upright()),
    DOWNRIGHT(c->c[0].downright()),
    DOWNLEFT(c->c[0].downleft()),
    UPLEFT(c->c[0].upleft());

    Function<Cell[], Cell> function;

    Command(Function<Cell[], Cell> function) {
        this.function = function;
    }

    @Override
    public int arity() {
        return 1;
    }

    @Override
    public Cell apply(Cell[] cells) {
        return function.apply(cells);
    }

    public Cell apply(Cell cell) {
        return function.apply((Cell[]) Collections.singletonList(cell).toArray());
    }
}
