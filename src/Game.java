import java.util.Scanner;

public class Game {

    public static void play(boolean AI) {
        Grid grid = new Grid();
        Piece player = Piece.RED;
        Scanner userIn = new Scanner(System.in);
        if (AI) {

        } else {
            while (true) {
                System.out.println(player + " player select a column to put your piece.");
                System.out.println(grid);
                String choice = userIn.nextLine();
                grid.addPiece(Integer.parseInt(choice), player);
                if (grid.wonGame(player)) {
                    System.out.println("Connect Four!!! " + player + " won the game!");
                    return;
                } else {
                    player = Piece.opposite(player);
                }
            }
        }
    }

}
