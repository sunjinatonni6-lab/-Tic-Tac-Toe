import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerXTurn = true;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3,3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        Font font = new Font("Times New Roman", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return;
        }

        clicked.setText(playerXTurn ? "X" : "O");
        clicked.setForeground(playerXTurn ? Color.BLACK : Color.BLUE);

        if (checkForWin()) {
            String winner = playerXTurn ? "Player X" : "Player O";
            JOptionPane.showMessageDialog(this, winner + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        } else {
            playerXTurn = !playerXTurn;
        }
    }

    private boolean checkForWin() {
        String symbol = playerXTurn ? "X" : "O";

      
        for (int i = 0; i < 3; i++) {
            if (symbol.equals(buttons[i][0].getText()) &&
                symbol.equals(buttons[i][1].getText()) &&
                symbol.equals(buttons[i][2].getText())) return true;

            if (symbol.equals(buttons[0][i].getText()) &&
                symbol.equals(buttons[1][i].getText()) &&
                symbol.equals(buttons[2][i].getText())) return true;
        }

        if (symbol.equals(buttons[0][0].getText()) &&
            symbol.equals(buttons[1][1].getText()) &&
            symbol.equals(buttons[2][2].getText())) return true;

        if (symbol.equals(buttons[0][2].getText()) &&
            symbol.equals(buttons[1][1].getText()) &&
            symbol.equals(buttons[2][0].getText())) return true;

        return false;
    }

    private boolean isBoardFull() {
        for (JButton[] row : buttons) {
            for (JButton b : row) {
                if (b.getText().equals("")) return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (JButton[] row : buttons) {
            for (JButton b : row) {
                b.setText("");
            }
        }
        playerXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
    

