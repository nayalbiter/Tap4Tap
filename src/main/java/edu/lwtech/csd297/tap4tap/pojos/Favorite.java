package edu.lwtech.csd297.tap4tap.pojos;
import java.util.*;;

public class Favorite {
    private int favoriteId;
    private UUID breweryId;
    private int userId;

    public Favorite(int favoriteid,UUID breweryId, int userId){
        this.favoriteId = favoriteid;
        this.breweryId = breweryId;
        this.userId = userId;
    }
    public Favorite(UUID breweryId, int userId) {
        this.breweryId = breweryId;
        this.userId = userId;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int id){
        this.favoriteId = id;
    }
    public UUID getBreweryId() {
        return breweryId;
    }

    public int getUserId() {
        return userId;
    }
}
