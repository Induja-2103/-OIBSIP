import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        Account account = bank.authenticate(userId, pin);
        if (account != null) {
            System.out.println("Login successful.");
            ATM atm = new ATM(account);
            atm.showMenu();
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }

        scanner.close();
    }
}
