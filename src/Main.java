public class Main {

    public static void main(String[] args) {
	    Grid grid = new Grid();
	    grid.addPiece(0, Grid.Piece.RED);
	    grid.addPiece(0, Grid.Piece.RED);
	    grid.addPiece(3, Grid.Piece.YELLOW);
        System.out.println(grid);
    }
}
