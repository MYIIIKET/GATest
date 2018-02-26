import io.jenetics.Gene;

public class CellGene implements Gene<Cell, CellGene> {

    private Cell cell;

    public CellGene(Cell cell) {
        this.cell = cell;
    }

    @Override
    public Cell getAllele() {
        return cell;
    }

    @Override
    public CellGene newInstance() {
        return new CellGene(Cell.getRandom());
    }

    @Override
    public CellGene newInstance(Cell cell) {
        return of(cell);
    }

    private CellGene of(Cell cell) {
        return new CellGene(cell);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
