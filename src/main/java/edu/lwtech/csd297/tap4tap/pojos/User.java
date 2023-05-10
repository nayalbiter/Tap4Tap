package edu.lwtech.csd297.tap4tap.pojos;

public class User {
    private int userId;
    private String username;
    private String hashedPasword;
    private String displayName;

    public User(int userId, String username, String hashedPasword, String displayName) {
        this.userId = userId;
        this.username = username;
        this.hashedPasword = hashedPasword;
        this.displayName = displayName;
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

    public String getDisplayName() {
        return displayName;
    }
}
