package edu.lwtech.csd297.tap4tap.pojos;

public class User {
    private int userId;
    private String username;
    private String hashedPasword;
    private String securityQuestion;
    private String hashedSecurityAnswer;
    private String displayName;
    private String email;
    private boolean admin;

    public User(int userId, String username, String hashedPasword, String securityQuestion, String hashedSecurityAnswer, String displayName, String email, boolean admin) {
        this.userId = userId;
        this.username = username;
        this.hashedPasword = hashedPasword;
        this.securityQuestion = securityQuestion;
        this.hashedSecurityAnswer = hashedSecurityAnswer;
        this.displayName = displayName;
        this.email = email;
        this.admin = admin;
    }

    public User(int userId, String username, String hashedPasword, String securityQuestion, String hashedSecurityAnswer, String displayName, boolean admin) {
        this(userId, username, hashedPasword, securityQuestion, hashedSecurityAnswer, displayName, null, admin);
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPasword() {
        return hashedPasword;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getHashedSecurityAnswer() {
        return hashedSecurityAnswer;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public boolean getAdmin() {
        return admin;
    }

    public String toString(){
        return "User id: " + userId + "\nUser name: " + username + "\nUser display name: " + displayName;
    }
}
