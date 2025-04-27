public class MCQ {
    private String question;
    private String[] options;
    private int correctAnswer; // index (0-3)

    public MCQ(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void display() {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean checkAnswer(int selectedOption) {
        return selectedOption - 1 == correctAnswer;
    }
}
