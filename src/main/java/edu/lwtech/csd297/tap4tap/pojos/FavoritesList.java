package edu.lwtech.csd297.tap4tap.pojos;

public class FavoritesList {
    private int listId;
    private int userId;

    public FavoritesList(int listId, int userId) {
        this.listId = listId;
        this.userId = userId;
    }

    public int getListId() {
        return listId;
    }

    public int getUserId() {
        return userId;
    }
}
