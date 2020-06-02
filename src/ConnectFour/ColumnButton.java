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

        if (grid.won) {
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.setColor(Color.BLACK);

            for (int i = 0; i < grid.con4.length - 1; i++) {
                int[] xy = grid.con4[i];
                if (xy[1] == column) {
                    int x = coord.get(xy[0])[0] + 40;
                    int y = coord.get(xy[0])[1] - 55;
                    Rectangle rect = new Rectangle(x, y, 5, 200);

                    switch (grid.con4[4][0]) {
                        case 0:
                            g2d.rotate(Math.toRadians(90), x + 2.5, y + 100);
                            break;
                        case 1:
                            x += 2;
                            y += 50;
                            rect = new Rectangle(x, y, 5, 100);
                            break;
                        case 2:
                            g2d.rotate(Math.toRadians(-45), x + 2.5, y + 100);
                            break;
                        case 3:
                            g2d.rotate(Math.toRadians(45), x + 2.5, y + 100);
                            break;
                    }
                    g2d.draw(rect);
                    g2d.fill(rect);

                }

            }
            g2d.dispose();
        }
    }

}
