import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthSystem auth = new AuthSystem();
        auth.registerUser("testuser", "password123"); // Default user

        while (true) {
            System.out.println("\n1. Login\n2. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Username: ");
                String username = scanner.next();
                System.out.print("Password: ");
                String password = scanner.next();

                if (auth.login(username, password)) {
                    boolean session = true;
                    while (session) {
                        System.out.println("\n1. Update Profile\n2. Update Password\n3. Take MCQ Test\n4. Logout");
                        int option = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (option) {
                            case 1:
                                System.out.print("Enter new profile info: ");
                                String profile = scanner.nextLine();
                                auth.updateProfile(profile);
                                break;
                            case 2:
                                System.out.print("Enter new password: ");
                                String newPass = scanner.next();
                                auth.updatePassword(newPass);
                                break;
                            case 3:
                                List<MCQ> questions = Arrays.asList(
                                    new MCQ("What is the capital of France?", new String[]{"Paris", "Berlin", "Rome", "Madrid"}, 0),
                                    new MCQ("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1),
                                    new MCQ("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 1),
                                    new MCQ("Who wrote 'Romeo and Juliet'?", new String[]{"Charles Dickens", "William Shakespeare", "Leo Tolstoy", "Mark Twain"}, 1),
                                    new MCQ("What is the chemical symbol for water?", new String[]{"H2O", "O2", "CO2", "NaCl"}, 0)
                                );
                                MCQTest test = new MCQTest(questions);
                                System.out.println("Starting test (you have 30 seconds)...");
                                test.startTest(30); // 30 seconds
                            
                                // After the test ends, ask user to continue or logout
                                System.out.println("\nDo you want to (1) Return to menu or (2) Logout?");
                                int postTestChoice = scanner.nextInt();
                                if (postTestChoice == 2) {
                                    auth.logout();
                                    session = false;
                                }
                                break;
                            
                            case 4:
                                auth.logout();
                                session = false;
                                break;
                        }
                    }
                }
            } else {
                System.out.println("Exiting...");
                break;
            }
        }
        scanner.close();
    }
}
