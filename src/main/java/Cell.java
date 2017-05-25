import java.util.ArrayList;
import java.util.List;

public class Cell {

    private final int row;
    private final int col;

    private Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static Cell of(int row, int col) {
        return new Cell(row, col);
    }

    public List<Cell> neighbours() {
        List<Cell> neighbourList = new ArrayList<>(8);
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                Cell neighbor = Cell.of(i,j);
                if (!neighbor.equals(this)) {
                    neighbourList.add(neighbor);
                }
            }
        }
        return neighbourList;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isWithin(int rowCount, int colCount) {
        return (col < colCount) && (col >= 0) && (row < rowCount) && (row >= 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell field = (Cell) o;

        if (row != field.row) {
            return false;
        }
        return col == field.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}

