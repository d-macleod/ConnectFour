public class Grid {
    Piece[][] positions;

    enum Piece {
        RED,
        YELLOW,
        EMPTY
    }

    public Grid () {
        positions = new Piece[6][7];
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions.length; j++) {
                positions[i][j] = Piece.EMPTY;
            }
        }
    }

    /**
     * Adds piece to the grid and returns if it was successful
     * @return success or not
     */
    public boolean addPiece (int column, Piece piece) {
        for (int i = positions.length - 1; i >= 0; i--) {
            if (positions[i][column].equals(Piece.EMPTY)) {
                positions[i][column] = piece;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String grid = "";
        for (int i = 0; i < positions.length; i++) {
            for (Piece piece : positions[i]) {
                if (piece == Piece.RED) {
                    grid += "R ";
                } else if (piece == Piece.YELLOW) {
                    grid += "Y ";
                } else {
                    grid += "* ";
                }
            }
            grid += "\n";
        }
        return grid;
    }
}
