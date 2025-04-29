public class Bank {
    private Account account;

    public Bank() {
        // Hardcoded one account for demo
        this.account = new Account("user123", "1234");
    }

    public Account authenticate(String userId, String pin) {
        if (account.validate(userId, pin)) {
            return account;
        } else {
            return null;
        }
    }
}
