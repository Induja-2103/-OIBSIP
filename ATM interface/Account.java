import java.util.ArrayList;
import java.util.List;

public class Account {
    private String userId;
    private String pin;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean validate(String userId, String pin) {
        return this.userId.equals(userId) && this.pin.equals(pin);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
        System.out.println("Deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            System.out.println("Invalid withdrawal.");
            return;
        }
        balance -= amount;
        transactionHistory.add(new Transaction("Withdraw", amount));
        System.out.println("Withdrawal successful.");
    }

    public void transfer(String recipient, double amount) {
        if (amount <= 0 || amount > balance) {
            System.out.println("Invalid transfer amount.");
            return;
        }
        balance -= amount;
        transactionHistory.add(new Transaction("Transfer to " + recipient, amount));
        System.out.println("Transferred to " + recipient + " successfully.");
    }

    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\nTransaction History:");
        for (Transaction t : transactionHistory) {
            System.out.println(t);
        }
    }
}
