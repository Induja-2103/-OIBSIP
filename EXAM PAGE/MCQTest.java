import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MCQTest {
    private List<MCQ> questions;
    private int score = 0;
    private boolean timeUp = false;

    public MCQTest(List<MCQ> questions) {
        this.questions = questions;
    }

    public void startTest(int durationSeconds) {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUp = true;
                System.out.println("\nTime is up! Auto-submitting your test...");
            }
        }, durationSeconds * 1000);

        for (MCQ mcq : questions) {
            if (timeUp) break;

            mcq.display();
            System.out.print("Enter your answer (1-4): ");
            int answer = scanner.nextInt();
            if (mcq.checkAnswer(answer)) {
                score++;
            }
        }

        timer.cancel();
        System.out.println("\nTest completed! Your score: " + score + "/" + questions.size());
    }
}
