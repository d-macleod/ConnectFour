public enum Piece {
    RED,
    YELLOW,
    EMPTY;

    public static Piece opposite(Piece p) {
        switch (p) {
            case RED:
                return YELLOW;
            case YELLOW:
                return RED;
            default:
                return EMPTY;
        }
    }

    public static String rep(Piece p) {
        switch (p) {
            case RED:
                return "R";
            case YELLOW:
                return "Y";
            default:
                return "*";
        }
    }
}
