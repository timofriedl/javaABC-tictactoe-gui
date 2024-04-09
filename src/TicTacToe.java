import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private final JButton[][] buttons;
    private String currentPlayer;
    private String winner;

    public TicTacToe() {
        super("Tic Tac Toe");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttons = new JButton[3][3];
        currentPlayer = "X";
        winner = "";

        JPanel panel = new JPanel(new GridLayout(3, 3));
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                JButton button = new JButton();
                button.setFont(new Font("Monospaced", Font.BOLD, 80));
                button.setFocusable(false);
                button.addActionListener(this);
                panel.add(button);
                buttons[y][x] = button;
            }
        }

        add(panel);
        setVisible(true);
    }

    private String symbolAt(int x, int y) {
        return buttons[y][x].getText();
    }

    private void reset() {
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setText("");
                button.setEnabled(true);
            }
        }
        winner = "";
    }

    private boolean checkWinner(String a, String b, String c) {
        if (!a.isEmpty() && a.equals(b) && a.equals(c)) {
            winner = a;
        }
        return a.isEmpty() || b.isEmpty() || c.isEmpty();
    }

    private void checkWinner() {
        boolean anyEmpty = false;

        for (int y = 0; y < 3; y++) {
            if (checkWinner(symbolAt(0, y), symbolAt(1, y), symbolAt(2, y))) {
                anyEmpty = true;
            }
        }

        for (int x = 0; x < 3; x++) {
            if (checkWinner(symbolAt(x, 0), symbolAt(x, 1), symbolAt(x, 2))) {
                anyEmpty = true;
            }
        }

        if (checkWinner(symbolAt(0, 0), symbolAt(1, 1), symbolAt(2, 2))
                | checkWinner(symbolAt(2, 0), symbolAt(1, 1), symbolAt(0, 2))) {
            anyEmpty = true;
        }

        if (!winner.isEmpty() || !anyEmpty) {
            String message = winner.isEmpty() ? "Tie!" : "Player " + winner + " won!";
            JOptionPane.showMessageDialog(this, message, "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            reset();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        button.setText(currentPlayer);
        button.setEnabled(false);

        checkWinner();
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}