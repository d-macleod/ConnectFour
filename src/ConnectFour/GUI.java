package ConnectFour;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    Game game;

    String[] AI;
    String[] players;

    int numPlayers;
    String AItype;

    JFrame frame;

    JButton btn0;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    JButton btn4;
    JButton btn5;
    JButton btn6;


    public GUI() {
        game = new Game();
        AI = new String[]{"Random", "MiniMax"};
        players = new String[]{"1 Player", "2 Players"};
        numPlayers = 1;
        AItype = "Random";

        frame = new JFrame("Connect Four");

        btn0 = new ColumnButton(game.grid, 0);
        btn1 = new ColumnButton(game.grid, 1);
        btn2 = new ColumnButton(game.grid, 2);
        btn3 = new ColumnButton(game.grid, 3);
        btn4 = new ColumnButton(game.grid, 4);
        btn5 = new ColumnButton(game.grid, 5);
        btn6 = new ColumnButton(game.grid, 6);
    }


    public void selection() {

        JFrame startFrame = new JFrame("Selection");
        JPanel startPanel = new JPanel();
        JComboBox dropDownPlayers = new JComboBox(players);
        dropDownPlayers.setSelectedIndex(numPlayers == 1 ? 0 : 1);
        JButton okbtn = new JButton("Okay");

        startPanel.setLayout(new FlowLayout());
        JLabel playerLabel = new JLabel("Number of Players");
        playerLabel.setBounds(10, 20, 80, 25);


        dropDownPlayers.addItemListener(
                itemEvent -> {
                    if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                        numPlayers = dropDownPlayers.getSelectedIndex() + 1;
                    }
                }
        );

        okbtn.addActionListener(
                actionEvent -> {
                    startFrame.dispose();
                    if (numPlayers == 2) {
                        AItype = null;
                        this.play();
                    } else {
                        selection2();
                    }
                }
        );


        startPanel.add(playerLabel);
        startPanel.add(dropDownPlayers);
        startPanel.add(okbtn);

        startFrame.add(startPanel);
        startFrame.pack();
        startFrame.setSize(600, 80);
        startFrame.setResizable(false);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
    }

    public void selection2() {

        JFrame startFrame = new JFrame("Selection");
        JPanel startPanel = new JPanel();
        JComboBox dropDownAI = new JComboBox(AI);
        dropDownAI.setSelectedIndex(AItype.equals("Random") ? 0 : 1);
        JButton okbtn = new JButton("Okay");

        startPanel.setLayout(new FlowLayout());
        JLabel AILabel = new JLabel("AI type");
        AILabel.setBounds(10, 20, 80, 25);

        dropDownAI.addItemListener(
                itemEvent -> {
                    if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                        AItype = AI[dropDownAI.getSelectedIndex()];
                    }
                }
        );

        okbtn.addActionListener(
                actionEvent -> {
                    startFrame.dispose();
                    this.play();
                }
        );



        startPanel.add(AILabel);
        startPanel.add(dropDownAI);
        startPanel.add(okbtn);

        startFrame.add(startPanel);
        startFrame.pack();
        startFrame.setSize(600, 80);
        startFrame.setResizable(false);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
    }

    public void play() {


        JPanel panel = new JPanel();


        btn0.addActionListener(new Action(0));
        btn0.addMouseListener(new MouseEffect(0));
        btn0.setBorderPainted(false);

        btn1.addActionListener(new Action(1));
        btn1.addMouseListener(new MouseEffect(1));
        btn1.setBorderPainted(false);

        btn2.addActionListener(new Action(2));
        btn2.addMouseListener(new MouseEffect(2));
        btn2.setBorderPainted(false);

        btn3.addActionListener(new Action(3));
        btn3.addMouseListener(new MouseEffect(3));
        btn3.setBorderPainted(false);

        btn4.addActionListener(new Action(4));
        btn4.addMouseListener(new MouseEffect(4));
        btn4.setBorderPainted(false);

        btn5.addActionListener(new Action(5));
        btn5.addMouseListener(new MouseEffect(5));
        btn5.setBorderPainted(false);

        btn6.addActionListener(new Action(6));
        btn6.addMouseListener(new MouseEffect(6));
        btn6.setBorderPainted(false);

        panel.setLayout(new GridLayout(1, 7));
        panel.add(btn0);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn6);

        frame.add(panel);
        frame.pack();
        frame.setSize(700, 635);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void endReached(Piece winner) {

        JFrame endFrame = new JFrame("Game Over");
        JPanel endPanel = new JPanel();

        JButton yesbtn = new JButton("Yes");
        JButton nobtn = new JButton("No");

        endPanel.setLayout(new FlowLayout());
        JLabel winnerLabel = new JLabel(winner.toString() + " won the game. Play again?");
        winnerLabel.setBounds(10, 20, 80, 25);
        JLabel staleLabel = new JLabel("Stalemate. Play again?");
        staleLabel.setBounds(10, 20, 80, 25);

        yesbtn.addActionListener(
                actionEvent -> {
                    game = new Game();
                    if (numPlayers == 2) {
                        AItype = "Random";
                    }
                    btn0 = new ColumnButton(game.grid, 0);
                    btn1 = new ColumnButton(game.grid, 1);
                    btn2 = new ColumnButton(game.grid, 2);
                    btn3 = new ColumnButton(game.grid, 3);
                    btn4 = new ColumnButton(game.grid, 4);
                    btn5 = new ColumnButton(game.grid, 5);
                    btn6 = new ColumnButton(game.grid, 6);
                    endFrame.dispose();
                    frame.dispose();
                    frame = new JFrame("Connect Four");
                    this.selection();
                }
        );

        nobtn.addActionListener(
                actionEvent -> {
                    endFrame.dispose();
                    frame.dispose();
                }
        );

        if (game.grid.isStaleMate()) {
            endPanel.add(staleLabel);
        } else {
            endPanel.add(winnerLabel);
        }
        endPanel.add(yesbtn);
        endPanel.add(nobtn);

        endFrame.add(endPanel);
        endFrame.pack();
        endFrame.setSize(600, 80);
        endFrame.setResizable(false);
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setLocationRelativeTo(null);
        endFrame.setVisible(true);
    }

    private class Action implements ActionListener {

        private int column;

        public Action(int column) {
            this.column = column;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(!game.play(AItype, column)) {
                btn0.repaint();
                btn0.removeActionListener(btn0.getActionListeners()[0]);
                btn1.repaint();
                btn1.removeActionListener(btn1.getActionListeners()[0]);
                btn2.repaint();
                btn2.removeActionListener(btn2.getActionListeners()[0]);
                btn3.repaint();
                btn3.removeActionListener(btn3.getActionListeners()[0]);
                btn4.repaint();
                btn4.removeActionListener(btn4.getActionListeners()[0]);
                btn5.repaint();
                btn5.removeActionListener(btn5.getActionListeners()[0]);
                btn6.repaint();
                btn6.removeActionListener(btn6.getActionListeners()[0]);
                endReached(game.turn);
            } else {
                btn0.repaint();
                btn1.repaint();
                btn2.repaint();
                btn3.repaint();
                btn4.repaint();
                btn5.repaint();
                btn6.repaint();
            }
        }
    }

    private class MouseEffect implements MouseListener {

        private int column;

        public MouseEffect(int column) {
            this.column = column;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {}

        @Override
        public void mousePressed(MouseEvent mouseEvent) {}

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {}

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

            switch (column) {
                case 0:
                    btn0.setBorderPainted(true);
                    break;
                case 1:
                    btn1.setBorderPainted(true);
                    break;
                case 2:
                    btn2.setBorderPainted(true);
                    break;
                case 3:
                    btn3.setBorderPainted(true);
                    break;
                case 4:
                    btn4.setBorderPainted(true);
                    break;
                case 5:
                    btn5.setBorderPainted(true);
                    break;
                case 6:
                    btn6.setBorderPainted(true);
                    break;
            }

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            switch (column) {
                case 0:
                    btn0.setBorderPainted(false);
                    break;
                case 1:
                    btn1.setBorderPainted(false);
                    break;
                case 2:
                    btn2.setBorderPainted(false);
                    break;
                case 3:
                    btn3.setBorderPainted(false);
                    break;
                case 4:
                    btn4.setBorderPainted(false);
                    break;
                case 5:
                    btn5.setBorderPainted(false);
                    break;
                case 6:
                    btn6.setBorderPainted(false);
                    break;
            }
        }
    }

}
