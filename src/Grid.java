
public class Grid {
    Piece[][] board;

    enum Piece {
        RED,
        YELLOW,
        EMPTY
    }

    public Grid () {
        board = new Piece[6][7];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = Piece.EMPTY;
            }
        }
    }

    public int getHeight() {
        return board.length;
    }

    public int getWidth() {
        return board[0].length;
    }

    /**
     * Adds piece to the grid and returns if it was successful
     * @return success or not
     */
    public boolean addPiece (int column, Piece piece) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][column].equals(Piece.EMPTY)) {
                board[i][column] = piece;
                return true;
            }
        }
        return false;
    }

    public boolean wonGame (Piece turn) {

        // horizontalCheck
        for (int j = 0; j < getWidth() - 3; j++) {
            for (int i = 0; i < getHeight(); i++){
                if (this.board[i][j] == turn && this.board[i][j+1] == turn && this.board[i][j+2] == turn && this.board[i][j+3] == turn){
                    return true;
                }
            }
        }
        // verticalCheck
        for (int i = 0; i < this.getHeight() - 3; i++){
            for (int j = 0; j < getWidth(); j++){
                if (this.board[i][j] == turn && this.board[i+1][j] == turn && this.board[i+2][j] == turn && this.board[i+3][j] == turn){
                    return true;
                }
            }
        }
        // ascendingDiagonalCheck
        for (int j = 0; j < getWidth()-3; j++){
            for (int i = 0; i < getHeight()-3; i++){
                if (this.board[i][j] == turn && this.board[i+1][j+1] == turn && this.board[i+2][j+2] == turn && this.board[i+3][j+3] == turn)
                    return true;
            }
        }
        // descendingDiagonalCheck
        for (int j = 0; j < getWidth()-3; j++){
            for (int i = getHeight() - 1; i > 2; i--){
                if (this.board[i][j] == turn && this.board[i-1][j+1] == turn && this.board[i-2][j+2] == turn && this.board[i-3][j+3] == turn)
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String grid = "";
        for (int i = 0; i < board.length; i++) {
            for (Piece piece : board[i]) {
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
