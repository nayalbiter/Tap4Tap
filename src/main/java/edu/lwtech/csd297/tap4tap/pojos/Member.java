package edu.lwtech.csd297.tap4tap.pojos;

// Member POJO

public class Member {

    private int userId = 0;             // Database ID (or -1 if it isn't in the database yet)
    private String username;
    private String password;

    public Member(String username, String password) {
        constructMember(userId++, username, password);
    }

    public Member(int userId, String username, String password) {
        if (userId <= 0)
            throw new IllegalArgumentException("userId must be a positive integer");

        constructMember(userId, username, password);
    }

    private void constructMember(int userId, String username, String password) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("username cannot be empty or null");
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("password cannot be empty or null");

        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    // ----------------------------------------------------------------

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        // Updates the userId of members that have just been added to the database
        if (userId <= 0)
            throw new IllegalArgumentException("UserId must be a positive integer.");
        if (this.userId != -1)
            throw new IllegalArgumentException("Member has already been added to the database.");

        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "[" + username + ", ******** ]";
    }

}