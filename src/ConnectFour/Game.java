package ConnectFour;

import java.util.Scanner;

public class Game {

    Piece turn;
    Grid grid;

    public Game () {
        turn = Piece.RED;
        grid = new Grid();
    }

    public void add(int column) {
        grid.addPiece(column, turn);
    }

    public void updateTurn(String AI) {
        if (AI == null) {
            turn = Piece.opposite(turn);
        } else {
            // let AI place the piece
            System.out.println("AI mode!");
        }
    }

    public boolean won() {
        return grid.wonGame(turn);
    }

//    public static void play(boolean AI) {
//        Grid grid = new Grid();
//        Piece player = Piece.RED;
//        Scanner userIn = new Scanner(System.in);
//        if (AI) {
//            ConnectAgent agent = new ConnectAgent();
//            while (true) {
//                System.out.println(grid);
//                int choice;
//                if (player.equals(Piece.RED)) {
//                    System.out.print(player + " player select a column to put your piece (0-6): ");
//                    String input = userIn.nextLine();
//                    choice = Integer.parseInt(input);
//                } else {
//                    choice = agent.choice();
//                }
//                grid.addPiece(choice, player);
//                if (grid.wonGame(player)) {
//                    System.out.println(grid);
//                    if (player.equals(Piece.RED)) {
//                        System.out.println("Connect Four!!! " + player + " won the game!");
//                    } else {
//                        System.out.println("Bummer... " + player + " won the game!");
//                    }
//                    return;
//                } else {
//                    player = Piece.opposite(player);
//                }
//            }
//        } else {
//            while (true) {
//                System.out.println(grid);
//                System.out.print(player + " player select a column to put your piece (0-6): ");
//                String choice = userIn.nextLine();
//                grid.addPiece(Integer.parseInt(choice), player);
//                if (grid.wonGame(player)) {
//                    System.out.println(grid);
//                    System.out.println("Connect Four!!! " + player + " won the game!");
//                    return;
//                } else {
//                    player = Piece.opposite(player);
//                }
//            }
//        }
//    }

}
