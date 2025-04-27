public class User {
    private String username;
    private String password;
    private String profileInfo;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.profileInfo = "";
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void updateProfile(String profileInfo) {
        this.profileInfo = profileInfo;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public String getProfileInfo() {
        return profileInfo;
    }
}
