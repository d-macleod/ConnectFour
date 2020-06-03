package ConnectFour;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ConnectAgent {

    Grid grid;

    public ConnectAgent(Grid grid) {
        this.grid = grid;
    }

    public int choice(String AI) {
        switch (AI) {
            case "Easy":
                return random();
            case "Medium":
                return MiniMax(4);
            case "Hard":
                return alphabeta(grid, 7, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
            default:
                return random();
        }
    }

    public int random() {
        Instant time = Instant.now();
        long seed = time.getEpochSecond();
        Random random = new Random(seed);
        int randInt = random.nextInt(7);
        while (!grid.legalMoves().contains(randInt)) {
            randInt = random.nextInt(7);
        }
        return randInt;
    }

    public int MiniMax(int depth) {
        MiniMaxAgent agent = new MiniMaxAgent(depth, grid);
        return agent.getAction();
    }

    public int alphabeta(Grid grid, int depth, int alpha, int beta, boolean maximizingPlayer) {
        MiniMaxAgent agent = new MiniMaxAgent(depth, grid);
        return agent.alphabeta(grid, depth, alpha, beta, maximizingPlayer).mv;
//        return 0;
    }



    private class MiniMaxAgent {
        int depth;
        Grid grid;
        HashMap<Integer, Integer> moves;

        public MiniMaxAgent(int depth, Grid grid) {
            this.depth = depth;
            this.grid = copyGrid(grid);
            this.moves = new HashMap<>();
            for (int i = 0; i < 7; i++) {
                moves.put(i, 0);
            }
        }

        public Grid copyGrid(Grid grid) {
            Grid newGrid = new Grid();
            for (int i = 0; i < grid.getHeight(); i++) {
                for (int j = 0; j < grid.getWidth(); j++) {
                    newGrid.board[i][j] = grid.board[i][j];
                }
            }
            return newGrid;
        }

        public boolean isGoal(Grid grid, Piece turn) {
            return grid.wonGame(turn);
        }

        public Grid generateSuccessor(Grid grid, Piece turn, int column) {
            Grid newGrid = copyGrid(grid);
            newGrid.addPiece(column, turn);
            return newGrid;
        }

        public int evalEnding(Grid grid) {
            if (isGoal(grid, Piece.YELLOW)) {
                return 5000;
            } else if (isGoal(grid, Piece.RED)) {
                return -5000;
            } else {
                return 0;
            }
        }

        public int eval(Grid grid) {

            int score = 0;
            Piece computer = Piece.YELLOW;
            Piece human = Piece.RED;

            //count number of 3 in a rows for yellow (+500 for each)
            //count number of 3 in a rows for red (-500 for each)

            // horizontalCheck
            for (int j = 0; j < grid.getWidth() - 3; j++) {
                for (int i = 0; i < grid.getHeight(); i++){
                    if (grid.board[i][j] == Piece.EMPTY && grid.board[i][j+1] == computer && grid.board[i][j+2] == computer && grid.board[i][j+3] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i][j+1] == Piece.EMPTY && grid.board[i][j+2] == computer && grid.board[i][j+3] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i][j+1] == computer && grid.board[i][j+2] == Piece.EMPTY && grid.board[i][j+3] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i][j+1] == computer && grid.board[i][j+2] == computer && grid.board[i][j+3] == Piece.EMPTY){
                        score += 500;
                    } else if (grid.board[i][j] == Piece.EMPTY && grid.board[i][j+1] == human && grid.board[i][j+2] == human && grid.board[i][j+3] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i][j+1] == Piece.EMPTY && grid.board[i][j+2] == human && grid.board[i][j+3] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i][j+1] == human && grid.board[i][j+2] == Piece.EMPTY && grid.board[i][j+3] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i][j+1] == human && grid.board[i][j+2] == human && grid.board[i][j+3] == Piece.EMPTY){
                        score -= 500;
                    }
                }
            }
            // verticalCheck
            for (int i = 0; i < grid.getHeight() - 3; i++){
                for (int j = 0; j < grid.getWidth(); j++){
                    if (grid.board[i][j] == Piece.EMPTY && grid.board[i+1][j] == computer && grid.board[i+2][j] == computer && grid.board[i+3][j] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j] == Piece.EMPTY && grid.board[i+2][j] == computer && grid.board[i+3][j] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j] == computer && grid.board[i+2][j] == Piece.EMPTY && grid.board[i+3][j] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j] == computer && grid.board[i+2][j] == computer && grid.board[i+3][j] == Piece.EMPTY){
                        score += 500;
                    } else if (grid.board[i][j] == Piece.EMPTY && grid.board[i+1][j] == human && grid.board[i+2][j] == human && grid.board[i+3][j] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j] == Piece.EMPTY && grid.board[i+2][j] == human && grid.board[i+3][j] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j] == human && grid.board[i+2][j] == Piece.EMPTY && grid.board[i+3][j] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j] == human && grid.board[i+2][j] == human && grid.board[i+3][j] == Piece.EMPTY){
                        score -= 500;
                    }
                }
            }
            // ascendingDiagonalCheck
            for (int j = 0; j < grid.getWidth()-3; j++){
                for (int i = 0; i < grid.getHeight()-3; i++){
                    if (grid.board[i][j] == Piece.EMPTY && grid.board[i+1][j+1] == computer && grid.board[i+2][j+2] == computer && grid.board[i+3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j+1] == Piece.EMPTY && grid.board[i+2][j+2] == computer && grid.board[i+3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j+1] == computer && grid.board[i+2][j+2] == Piece.EMPTY && grid.board[i+3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j+1] == computer && grid.board[i+2][j+2] == computer && grid.board[i+3][j+3] == Piece.EMPTY) {
                        score += 500;
                    } else if (grid.board[i][j] == Piece.EMPTY && grid.board[i+1][j+1] == human && grid.board[i+2][j+2] == human && grid.board[i+3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j+1] == Piece.EMPTY && grid.board[i+2][j+2] == human && grid.board[i+3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j+1] == human && grid.board[i+2][j+2] == Piece.EMPTY && grid.board[i+3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j+1] == human && grid.board[i+2][j+2] == human && grid.board[i+3][j+3] == Piece.EMPTY) {
                        score -= 500;
                    }
                }
            }
            // descendingDiagonalCheck
            for (int j = 0; j < grid.getWidth()-3; j++){
                for (int i = grid.getHeight() - 1; i > 2; i--){
                    if (grid.board[i][j] == Piece.EMPTY && grid.board[i-1][j+1] == computer && grid.board[i-2][j+2] == computer && grid.board[i-3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i-1][j+1] == Piece.EMPTY && grid.board[i-2][j+2] == computer && grid.board[i-3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i-1][j+1] == computer && grid.board[i-2][j+2] == Piece.EMPTY && grid.board[i-3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i-1][j+1] == computer && grid.board[i-2][j+2] == computer && grid.board[i-3][j+3] == Piece.EMPTY) {
                        score += 500;
                    } else if (grid.board[i][j] == Piece.EMPTY && grid.board[i-1][j+1] == human && grid.board[i-2][j+2] == human && grid.board[i-3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i-1][j+1] == Piece.EMPTY && grid.board[i-2][j+2] == human && grid.board[i-3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i-1][j+1] == human && grid.board[i-2][j+2] == Piece.EMPTY && grid.board[i-3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i-1][j+1] == human && grid.board[i-2][j+2] == human && grid.board[i-3][j+3] == Piece.EMPTY) {
                        score -= 500;
                    }
                }
            }

            return score;
        }

        public int eval2(Grid grid) {

            int score = 0;
            Piece computer = Piece.YELLOW;
            Piece human = Piece.RED;

            int[][] evaluationTable = new int[][]{{3, 4, 5, 7, 5, 4, 3},
                                                  {4, 6, 8, 10, 8, 6, 4},
                                                  {5, 8, 11, 13, 11, 8, 5},
                                                  {5, 8, 11, 13, 11, 8, 5},
                                                  {4, 6, 8, 10, 8, 6, 4},
                                                  {3, 4, 5, 7, 5, 4, 3}};

            for (int i = 0; i < grid.getHeight(); i++) {
                for (int j = 0; j < grid.getWidth(); j++) {
                    if (grid.board[i][j] == computer) {
                        score += evaluationTable[i][j];
                    }
                    else if (grid.board[i][j] == human) {
                        score -= evaluationTable[i][j];
                    }
                }
            }

            //count number of 3 in a rows for yellow (+500 for each)
            //count number of 3 in a rows for red (-500 for each)

            // horizontalCheck
            for (int j = 0; j < grid.getWidth() - 3; j++) {
                for (int i = 0; i < grid.getHeight(); i++){
                    if (grid.board[i][j] == Piece.EMPTY && grid.board[i][j+1] == computer && grid.board[i][j+2] == computer && grid.board[i][j+3] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i][j+1] == Piece.EMPTY && grid.board[i][j+2] == computer && grid.board[i][j+3] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i][j+1] == computer && grid.board[i][j+2] == Piece.EMPTY && grid.board[i][j+3] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i][j+1] == computer && grid.board[i][j+2] == computer && grid.board[i][j+3] == Piece.EMPTY){
                        score += 500;
                    } else if (grid.board[i][j] == Piece.EMPTY && grid.board[i][j+1] == human && grid.board[i][j+2] == human && grid.board[i][j+3] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i][j+1] == Piece.EMPTY && grid.board[i][j+2] == human && grid.board[i][j+3] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i][j+1] == human && grid.board[i][j+2] == Piece.EMPTY && grid.board[i][j+3] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i][j+1] == human && grid.board[i][j+2] == human && grid.board[i][j+3] == Piece.EMPTY){
                        score -= 500;
                    }
                }
            }
            // verticalCheck
            for (int i = 0; i < grid.getHeight() - 3; i++){
                for (int j = 0; j < grid.getWidth(); j++){
                    if (grid.board[i][j] == Piece.EMPTY && grid.board[i+1][j] == computer && grid.board[i+2][j] == computer && grid.board[i+3][j] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j] == Piece.EMPTY && grid.board[i+2][j] == computer && grid.board[i+3][j] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j] == computer && grid.board[i+2][j] == Piece.EMPTY && grid.board[i+3][j] == computer){
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j] == computer && grid.board[i+2][j] == computer && grid.board[i+3][j] == Piece.EMPTY){
                        score += 500;
                    } else if (grid.board[i][j] == Piece.EMPTY && grid.board[i+1][j] == human && grid.board[i+2][j] == human && grid.board[i+3][j] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j] == Piece.EMPTY && grid.board[i+2][j] == human && grid.board[i+3][j] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j] == human && grid.board[i+2][j] == Piece.EMPTY && grid.board[i+3][j] == human){
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j] == human && grid.board[i+2][j] == human && grid.board[i+3][j] == Piece.EMPTY){
                        score -= 500;
                    }
                }
            }
            // ascendingDiagonalCheck
            for (int j = 0; j < grid.getWidth()-3; j++){
                for (int i = 0; i < grid.getHeight()-3; i++){
                    if (grid.board[i][j] == Piece.EMPTY && grid.board[i+1][j+1] == computer && grid.board[i+2][j+2] == computer && grid.board[i+3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j+1] == Piece.EMPTY && grid.board[i+2][j+2] == computer && grid.board[i+3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j+1] == computer && grid.board[i+2][j+2] == Piece.EMPTY && grid.board[i+3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i+1][j+1] == computer && grid.board[i+2][j+2] == computer && grid.board[i+3][j+3] == Piece.EMPTY) {
                        score += 500;
                    } else if (grid.board[i][j] == Piece.EMPTY && grid.board[i+1][j+1] == human && grid.board[i+2][j+2] == human && grid.board[i+3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j+1] == Piece.EMPTY && grid.board[i+2][j+2] == human && grid.board[i+3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j+1] == human && grid.board[i+2][j+2] == Piece.EMPTY && grid.board[i+3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i+1][j+1] == human && grid.board[i+2][j+2] == human && grid.board[i+3][j+3] == Piece.EMPTY) {
                        score -= 500;
                    }
                }
            }
            // descendingDiagonalCheck
            for (int j = 0; j < grid.getWidth()-3; j++){
                for (int i = grid.getHeight() - 1; i > 2; i--){
                    if (grid.board[i][j] == Piece.EMPTY && grid.board[i-1][j+1] == computer && grid.board[i-2][j+2] == computer && grid.board[i-3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i-1][j+1] == Piece.EMPTY && grid.board[i-2][j+2] == computer && grid.board[i-3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i-1][j+1] == computer && grid.board[i-2][j+2] == Piece.EMPTY && grid.board[i-3][j+3] == computer) {
                        score += 500;
                    } else if (grid.board[i][j] == computer && grid.board[i-1][j+1] == computer && grid.board[i-2][j+2] == computer && grid.board[i-3][j+3] == Piece.EMPTY) {
                        score += 500;
                    } else if (grid.board[i][j] == Piece.EMPTY && grid.board[i-1][j+1] == human && grid.board[i-2][j+2] == human && grid.board[i-3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i-1][j+1] == Piece.EMPTY && grid.board[i-2][j+2] == human && grid.board[i-3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i-1][j+1] == human && grid.board[i-2][j+2] == Piece.EMPTY && grid.board[i-3][j+3] == human) {
                        score -= 500;
                    } else if (grid.board[i][j] == human && grid.board[i-1][j+1] == human && grid.board[i-2][j+2] == human && grid.board[i-3][j+3] == Piece.EMPTY) {
                        score -= 500;
                    }
                }
            }

            return score;
        }

        public Pair max (Pair a, Pair b) {
            if (a.value >= b.value) {
                return a;
            } else {
                return b;
            }
        }

        public Pair min (Pair a, Pair b) {
            if (a.value < b.value) {
                return a;
            } else {
                return b;
            }
        }

        public Pair alphabeta(Grid grid, int depth, int alpha, int beta, boolean maximizingPlayer) {
            if (depth == 0) {
                return new Pair(eval2(grid), 0);
            } else if (evalEnding(grid) != 0) {
                return new Pair(evalEnding(grid), 0);
            } else if (maximizingPlayer) {
                Pair bestMV = new Pair(Integer.MIN_VALUE, 0);
                for (int mv : grid.legalMoves()) {
                    Grid newGrid = generateSuccessor(grid, Piece.YELLOW, mv);
                    Pair curMV = new Pair(alphabeta(newGrid, depth - 1, alpha, beta, false).value, mv);
                    bestMV = max(bestMV, curMV);
                    alpha = Math.max(alpha, bestMV.value);
                    if (alpha >= beta) {
                        break;
                    }
                }
                return bestMV;
            } else {
                Pair bestMV = new Pair(Integer.MAX_VALUE, 0);
                for (int mv : grid.legalMoves()) {
                    Grid newGrid = generateSuccessor(grid, Piece.RED, mv);
                    Pair curMV = new Pair(alphabeta(newGrid, depth - 1, alpha, beta, true).value, mv);
                    bestMV = min(bestMV, curMV);
                    beta = Math.min(beta, bestMV.value);
                    if (beta <= alpha) {
                        break;
                    }
                }
                return bestMV;
            }
        }


        public int getAction() {
            int currDepth = 1;
            ArrayList<Integer> bestMVs = new ArrayList<>();
            int bestScore = Integer.MIN_VALUE;

            for (int mv : grid.legalMoves()) {
                Grid newGrid = generateSuccessor(grid, Piece.YELLOW, mv);
                moves.put(mv, minVal(newGrid, currDepth));
                if (moves.get(mv) > bestScore) {
                    bestMVs.clear();
                    bestMVs.add(mv);
                    bestScore = moves.get(mv);
                } else if (moves.get(mv) == bestScore) {
                    bestMVs.add(mv);
                }
            }
            Instant time = Instant.now();
            long seed = time.getEpochSecond();
            Random random = new Random(seed);
            return bestMVs.get(random.nextInt(bestMVs.size()));
        }


        public int minVal(Grid grid, int currDepth) {
            if (evalEnding(grid) != 0) {
                return evalEnding(grid);
            } else if (currDepth >= this.depth) {
                return eval(grid);
            } else {
                HashMap<Integer, Integer> humanMoves = new HashMap<>();
                int bestScore = Integer.MAX_VALUE;
                for (int mv : grid.legalMoves()) {
                    Grid newGrid = generateSuccessor(grid, Piece.RED, mv);
                    humanMoves.put(mv, maxVal(newGrid, currDepth + 1));
                    if (humanMoves.get(mv) < bestScore) {
                        bestScore = humanMoves.get(mv);
                    }
                }
                return bestScore;
            }
        }

        public int maxVal(Grid grid, int currDepth) {
            if (evalEnding(grid) != 0) {
                return evalEnding(grid);
            } else if (currDepth >= this.depth) {
                return eval(grid);
            } else {
                HashMap<Integer, Integer> computerMoves = new HashMap<>();
                int bestScore = Integer.MIN_VALUE;
                for (int mv : grid.legalMoves()) {
                    Grid newGrid = generateSuccessor(grid, Piece.YELLOW, mv);
                    computerMoves.put(mv, minVal(newGrid, currDepth + 1));
                    if (computerMoves.get(mv) > bestScore) {
                        bestScore = computerMoves.get(mv);
                    }
                }
                return bestScore;
            }
        }

        private class Pair {
            int value;
            int mv;

            public Pair(int value, int mv) {
                this.value = value;
                this.mv = mv;
            }
        }


    }

}
