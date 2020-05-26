public class Main {

    public static void main(String[] args) {
	    Grid grid = new Grid();
        System.out.println(grid);
        System.out.println(grid.wonGame(Grid.Piece.RED));
        grid.addPiece(0, Grid.Piece.RED);
        System.out.println(grid);
        System.out.println(grid.wonGame(Grid.Piece.RED));
        grid.addPiece(0, Grid.Piece.RED);
        grid.addPiece(0, Grid.Piece.RED);
        grid.addPiece(0, Grid.Piece.RED);
        System.out.println(grid);
        System.out.println(grid.wonGame(Grid.Piece.RED));
    }
}
