import java.util.Arrays;
import java.util.Random;

/**
 * Created by marti on 2017.05.23..
 */
public class MineSweeper {

    private char[][] table;
    private Random randStar;

    public MineSweeper(int rowNum, int colNum) {
        table = new char[rowNum][colNum];
        generateMines();
        generateFields('.');
    }

    private void generateFields(char character) {
        for(int i=0; i < table.length; i++) {
            for (int j=0; j < table[i].length; j++) {
                if(table[i][j] != '*') {
                    table[i][j] = character;
                }
            }
        }
    }

    private void generateMines() {
        randStar = new Random();
        for (int i=0; i < table.length; i++) {
            table[randStar.nextInt(table.length)][randStar.nextInt(table[table[i].length-1].length)]='*';
        }
    }


    public void showNumbers() {
        generateFields('0');
        for (int row = 0; row < table.length; row++) {
            for (int col=0; col < table[row].length; col++) {
                if (table[row][col] == '*') {
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if ((row + x >= 0 && row + x <= table[row].length - 1) &&
                                    (col + y >= 0 && col + y <= table[col].length - 1) &&
                                    ((table[row + x][col + y] != '*'))) {
                                String field = String.valueOf(table[row + x][col + y]);
                                Integer fieldNum = Integer.parseInt(field) + 1;
                                table[row + x][col + y] = Integer.toString(fieldNum).charAt(0);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String stringTable = "";
        for (char[] rows : table) {
            stringTable += Arrays.toString(rows) + '\n';
        }
        return stringTable;
    }
}
