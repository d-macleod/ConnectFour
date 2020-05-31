package ConnectFour;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ColumnButton extends JButton {

    Grid grid;
    int column;
    HashMap<Integer, int[]> coord;


    public ColumnButton(Grid grid, int column) {
        super();
        this.grid = grid;
        this.column = column;
        this.coord = new HashMap<>();
        for (int i = 0; i < grid.getHeight(); i++) {
            coord.put(i, new int[]{6, (i * 100) + 10});
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(0,0 ,100, 700);
        g.setColor(Color.WHITE);
        for (int i = grid.getHeight() - 1; i >= 0; i--) {
            g.fillOval(coord.get(i)[0], coord.get(i)[1], 89, 89);
        }

        for (int i = grid.getHeight() - 1; i >= 0; i--) {
            if (grid.board[i][column].equals(Piece.EMPTY)) {
                break;
            } else if (grid.board[i][column].equals(Piece.RED)){
                g.setColor(Color.RED);
                g.fillOval(coord.get(i)[0], coord.get(i)[1], 89, 89);
            } else {
                g.setColor(Color.YELLOW);
                g.fillOval(coord.get(i)[0], coord.get(i)[1], 89, 89);
            }
        }
    }
}
