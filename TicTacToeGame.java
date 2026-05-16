import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    char currentPlayer = 'X';
    JLabel statusLabel;

    public TicTacToeGame() {

        setTitle("Tic-Tac-Toe Game");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Player X Turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 50));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(statusLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();

        // Check if button already clicked
        if (!clickedButton.getText().equals("")) {
            return;
        }

        // Set current player's symbol
        clickedButton.setText(String.valueOf(currentPlayer));

        // Check winner
        if (checkWinner()) {
            statusLabel.setText("Player " + currentPlayer + " Wins!");
            disableButtons();
            return;
        }

        // Check draw
        if (isBoardFull()) {
            statusLabel.setText("Game Draw!");
            return;
        }

        // Switch player
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        statusLabel.setText("Player " + currentPlayer + " Turn");
    }

    // Method to check winning combinations
    public boolean checkWinner() {

        String[][] winPatterns = {
                {"0", "1", "2"},
                {"3", "4", "5"},
                {"6", "7", "8"},
                {"0", "3", "6"},
                {"1", "4", "7"},
                {"2", "5", "8"},
                {"0", "4", "8"},
                {"2", "4", "6"}
        };

        for (String[] pattern : winPatterns) {

            int a = Integer.parseInt(pattern[0]);
            int b = Integer.parseInt(pattern[1]);
            int c = Integer.parseInt(pattern[2]);

            String text1 = buttons[a].getText();
            String text2 = buttons[b].getText();
            String text3 = buttons[c].getText();

            if (!text1.equals("") &&
                text1.equals(text2) &&
                text2.equals(text3)) {

                return true;
            }
        }

        return false;
    }

    // Check if board is full
    public boolean isBoardFull() {

        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }

        return true;
    }

    // Disable all buttons after game ends
    public void disableButtons() {

        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    public static void main(String[] args) {

        new TicTacToeGame();
    }
}