package edu.lwtech.csd297.tap4tap.pojos;

public class Favorite {
    private int favoriteId;
    private String breweryId;
    private int listId;

    public Favorite(int favoriteId, String breweryId, int listId) {
        this.favoriteId = favoriteId;
        this.breweryId = breweryId;
        this.listId = listId;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public String getBreweryId() {
        return breweryId;
    }

    public int getListId() {
        return listId;
    }
}
