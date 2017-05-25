/**
 * Created by marti on 2017.05.23..
 */
public class Main {

    public static void main(String[] args) {

        MineSweeper game = new MineSweeper(5,5);
        System.out.println(game.toString());
        System.out.println(game.copy(true).toString());

    }

}
