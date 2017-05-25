
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by marti on 2017.05.23..
 */
public class MineSweeper {

    private static final char MINE = '*';
    private static final char EMPTY = '.';

    private final int rowCount;
    private final int colCount;
    private final List<Cell> mines;
    private final Boolean revealed;
    private char[][] table;

    public MineSweeper(int rowCount, int colCount) {
        this(rowCount, colCount, generateMines(rowCount, colCount), false);
    }

    private MineSweeper(int rowCount, int colCount, List<Cell> mines, boolean revealed) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.revealed = revealed;
        this.mines = mines;
        generateTable();
    }

    public MineSweeper copy(boolean revealed) {
        return new MineSweeper(rowCount, colCount, mines, revealed);
    }

    private static int calculateNumMines(int rowNum, int colNum) {
        // any logic that determines difficulty
        return Math.min(rowNum * colNum, rowNum + colNum);
    }

    private static List<Cell> generateMines(int rowCount, int colCount) {
        Random random = new Random();
        int numMines = calculateNumMines(rowCount, colCount);
        List<Cell> table = IntStream.range(0, rowCount * colCount)
                                    .mapToObj(i -> Cell.of(i / colCount, i % colCount))
                                    .collect(Collectors.toList());
        Collections.shuffle(table, random);
        return table.subList(0, numMines);
    }

    private void generateTable() {
        table = new char[rowCount][colCount];
        initializeTable(revealed ? '0' : EMPTY);
        setTable(mines, MINE);
        if (revealed) {
            fillTableMineCounts();
        }
    }

    private void fillTableMineCounts() {
        for (Cell mine : mines) {
            List<Cell> neighbours = mine.neighbours().stream()
                                        .filter(c -> c.isWithin(rowCount, colCount))
                                        .filter(c -> table[c.getRow()][c.getCol()] == '0')
                                        .collect(Collectors.toList());
            for (Cell cell : neighbours) {
                long numNeighbouringMines = cell.neighbours().stream()
                                                .filter(c -> c.isWithin(rowCount, colCount))
                                                .filter(c -> table[c.getRow()][c.getCol()] == MINE)
                                                .count();
                table[cell.getRow()][cell.getCol()] = Long.toString(numNeighbouringMines).charAt(0);
            }
        }
    }

    private void initializeTable(char c) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                table[i][j] = c;
            }
        }
    }

    private void setTable(List<Cell> fields, char c) {
        fields.forEach(f -> table[f.getRow()][f.getCol()] = c);
    }

    @Override
    public String toString() {
        StringBuilder stringTable = new StringBuilder();
        for (char[] rows : table) {
            stringTable.append(Arrays.toString(rows) + '\n');
        }
        return stringTable.toString();
    }
}
