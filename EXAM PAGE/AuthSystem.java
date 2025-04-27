import java.util.HashMap;
import java.util.Scanner;

public class AuthSystem {
    private HashMap<String, User> users = new HashMap<>();
    private User loggedInUser = null;

    public void registerUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            loggedInUser = user;
            System.out.println("Login successful!");
            return true;
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public void updateProfile(String profileInfo) {
        if (loggedInUser != null) {
            loggedInUser.updateProfile(profileInfo);
            System.out.println("Profile updated!");
        }
    }

    public void updatePassword(String newPassword) {
        if (loggedInUser != null) {
            loggedInUser.updatePassword(newPassword);
            System.out.println("Password updated!");
        }
    }

    public void logout() {
        loggedInUser = null;
        System.out.println("You have been logged out.");
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }
}
