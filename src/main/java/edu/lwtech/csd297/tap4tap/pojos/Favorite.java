package edu.lwtech.csd297.tap4tap.pojos;

public class Favorite {
    private int favoriteId;
    private String breweryId;
    private int userId;

    public Favorite(int favoriteId, String breweryId, int userId) {
        this.favoriteId = favoriteId;
        this.breweryId = breweryId;
        this.userId = userId;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public String getBreweryId() {
        return breweryId;
    }

    public int getUserId() {
        return userId;
    }
}
