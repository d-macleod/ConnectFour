package ConnectFour;

public class Game {

    Piece turn;
    Grid grid;
    ConnectAgent agent;

    public Game () {
        turn = Piece.RED;
        grid = new Grid();
        agent = new ConnectAgent();
    }

    public void add(int column) {
        grid.addPiece(column, turn);
    }

    public void updateTurn() {
        turn = Piece.opposite(turn);
    }

    public boolean won() {
        return grid.wonGame(turn);
    }

    public boolean play(String AI, int column) {
        if (AI != null) {
                add(column);
                if (won()) {
                    System.out.println(grid);
                    System.out.println("Connect Four!!! " + turn + " won the game!");
                    return false;
                } else {
                    updateTurn();
                    add(agent.choice());
                    System.out.println(grid);
                    if (grid.wonGame(turn)) {
                        System.out.println(grid);
                        System.out.println("Connect Four!!! " + turn + " won the game!");
                        return false;
                    } else {
                        updateTurn();
                    }
                }
        } else {
            add(column);
            if (won()) {
                System.out.println(grid);
                System.out.println("Connect Four!!! " + turn + " won the game!");
                return false;
            } else {
                System.out.println(grid);
                updateTurn();
            }

        }
        return true;
    }


}
