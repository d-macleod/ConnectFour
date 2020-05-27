import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userIn = new Scanner(System.in);
        System.out.println("  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@    -----    ------    |\\      |   |\\      |    |------     -----    ---------             /|     @@");
        System.out.println("@@   |        |      |   |  \\    |   |  \\    |    |          |             |                / |     @@");
        System.out.println("@@   |        |      |   |   \\   |   |   \\   |    |-----     |             |               /  |     @@");
        System.out.println("@@   |        |      |   |    \\  |   |    \\  |    |          |             |              /   |     @@");
        System.out.println("@@   |        |      |   |     \\ |   |     \\ |    |          |             |             /____|__   @@");
        System.out.println("@@    -----    ------    |      \\|   |      \\|    |------     -----        |                  |     @@");
        System.out.println("  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println();
        System.out.print("Would you like to play solo or against a friend? (1P/2P): ");
        String choice = userIn.nextLine();

        if (choice.equals("1P")) {
            // play against AI
            Game.play(true);
        } else if (choice.equals("2P")) {
            // play against friend
            Game.play(false);
        } else {
            System.out.println("Not a valid selection. Enter 1P for one player, or 2P for two players.");
        }

    }
}
