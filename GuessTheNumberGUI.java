import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessTheNumberGUI extends JFrame implements ActionListener {
    private Random random;
    private int numberToGuess;
    private int attemptsLeft;
    private int score;
    private JTextField guessField;
    private JLabel messageLabel, attemptsLabel, scoreLabel;
    private JButton guessButton, newGameButton;

    public GuessTheNumberGUI() {
        // Game variables
        random = new Random();
        newGame();

        // UI setup
        setTitle("🎮 Guess The Number Game");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        getContentPane().setBackground(new Color(245, 245, 245)); // Light background

        messageLabel = new JLabel("Guess a number between 1 and 100!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));

        guessField = new JTextField();
        guessButton = new JButton("Submit Guess");
        guessButton.setBackground(new Color(0, 123, 255));
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);

        attemptsLabel = new JLabel("Attempts Left: " + attemptsLeft, SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);

        newGameButton = new JButton("New Game");
        newGameButton.setBackground(new Color(40, 167, 69));
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setFocusPainted(false);

        add(messageLabel);
        add(guessField);
        add(guessButton);
        add(attemptsLabel);
        add(scoreLabel);
        add(newGameButton);

        guessButton.addActionListener(this);
        newGameButton.addActionListener(this);

        setVisible(true);
    }

    public void newGame() {
        numberToGuess = random.nextInt(100) + 1;
        attemptsLeft = 10;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            String text = guessField.getText();
            try {
                int guess = Integer.parseInt(text);
                attemptsLeft--;

                if (guess == numberToGuess) {
                    int points = attemptsLeft * 10;
                    score += points;
                    JOptionPane.showMessageDialog(this, "🎉 Correct! You earned " + points + " points!", "Correct", JOptionPane.INFORMATION_MESSAGE);
                    newGame();
                } else if (guess < numberToGuess) {
                    messageLabel.setText("📉 Too Low! Try again.");
                } else {
                    messageLabel.setText("📈 Too High! Try again.");
                }

                if (attemptsLeft <= 0) {
                    JOptionPane.showMessageDialog(this, "❌ Out of attempts! Number was: " + numberToGuess, "Game Over", JOptionPane.ERROR_MESSAGE);
                    newGame();
                }
                
                guessField.setText("");
                attemptsLabel.setText("Attempts Left: " + attemptsLeft);
                scoreLabel.setText("Score: " + score);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ Please enter a valid number!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == newGameButton) {
            newGame();
            score = 0;
            messageLabel.setText("Guess a number between 1 and 100!");
            guessField.setText("");
            attemptsLabel.setText("Attempts Left: " + attemptsLeft);
            scoreLabel.setText("Score: " + score);
        }
    }

    public static void main(String[] args) {
        new GuessTheNumberGUI();
    }
}
