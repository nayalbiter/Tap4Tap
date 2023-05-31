package edu.lwtech.csd297.tap4tap.daos;

import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.*;

import edu.lwtech.csd297.tap4tap.pojos.*;

public class FavoriteSqlDAO implements FavoriteDAO {

    private static final Logger logger = LogManager.getLogger(FavoriteSqlDAO.class.getName());

    private Connection conn = null;

    public FavoriteSqlDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insert(Favorite favorite) {
        //!!implement to check if favorite was already inserted

        String query = "INSERT INTO favorite";
        query += "(brewery_id, user_id)";
        query += "VALUES (?,?)";

        String[] returnColumns = {};
        try ( PreparedStatement stmt = conn.prepareStatement(query, returnColumns); ){
            // Substitute in the argument values for the question marks
            stmt.setString(1, favorite.getBreweryId());
            stmt.setInt(2, favorite.getUserId());

            logger.debug("Executing SQL Insert: {}", query);
             // Execute the INSERT statement
            stmt.executeUpdate();

            logger.debug("Favorite successfully inserted with ID = {}", favorite.getFavoriteId());
            return true;
        }catch (SQLException e) {
            logger.error("SQL Exception caught in executeSQLInsert: {}, {}", query, e);
            return false;
        }
    }

    @Override
    public Favorite retrieveByID(int favoriteId) {
        logger.debug("Trying to get Favorite with ID: {}", favoriteId);

        String query = "SELECT *";
        query += " FROM favorite WHERE favorite_id=?";

        logger.debug("Executing SQL statement: {}", query);
        ResultSet sqlResults;
        try(PreparedStatement stmt = conn.prepareStatement(query);){
            // Substitute in the argument values for the question marks
            stmt.setInt(1, favoriteId);

            // Execute the SELECT query
            sqlResults = stmt.executeQuery();

            return convertResultToFavorite(sqlResults);
        } catch(Exception e){
            logger.debug("Sql Exception caught while selecting favorite by Id: {}", favoriteId);
            return null;
        }
    }

    @Override
    public List<Favorite> retrieveByUserId(int id){
        List<Favorite> favoriteList = new ArrayList<>();
        logger.debug("Trying to retrieve Favorite list with id: {}", id);

        String  query = "SELECT *";
        query += " FROM favorite where user_id=?";

        logger.debug("Excuting SQL statement: {}", query);
        ResultSet sqlResults;
        try(PreparedStatement stmt = conn.prepareStatement(query);){
            stmt.setInt(1, id);
            sqlResults = stmt.executeQuery();
        } catch(SQLException e){
            logger.debug("Sql Exception caught while selecting favorites by id:{}", id);
            return null;
        }
        try{
            while(sqlResults.next()){
                favoriteList.add(convertResultToFavorite(sqlResults));
            }
            logger.error("Returning " + favoriteList.size() + "favorites");
        }catch(SQLException e){
            logger.error("SQL Exception caught in getting results: {}", e);
            return null;
        }
        return favoriteList;
    }
    public boolean update(Favorite favorite) {
        logger.debug("Trying to update Favorite with Favorite: {}", favorite);

        String query = "UPDATE FROM favorite WHERE favorite_id = ?";
        int favoriteId = favorite.getFavoriteId();
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, favoriteId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
        catch(SQLException e){
            logger.error("Error caught in excuteUpdate: {}", e);
            return false;
        }
    }

    public int delete(int favoriteId) {
        logger.debug("Trying to delete Favorite with ID: {}", favoriteId);

        String query = "DELETE FROM favorite WHERE favorite_id=?";

        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setObject(1,favoriteId);
            return stmt.executeUpdate();
        }
        catch(SQLException e){
            logger.error("Error caught in executeUpdate: {}", e);
            return -1;
        }
    }

    public int size() {
        logger.debug("Getting the number of rows...");
        ResultSet sqlResults;
        String query = "SELECT count(*) FROM favorite";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            sqlResults = stmt.executeQuery();
        }catch(SQLException e){
            logger.error("Error caught in excuteQuery: {}", e);
            return -1;
        }
        int count = 0;
        try{
            while(sqlResults.next()){
                count++;
            }
            return count;
        }catch(SQLException e){
            logger.error("SQL Exception caught in getting results: {}", e);
            return -1;
        }
    }

    // =====================================================================

    private Favorite convertResultToFavorite(ResultSet result) throws SQLException {
        int favoriteId =  result.getInt(result.findColumn("favorite_id"));
        String breweryId = result.getString(result.findColumn("brewery_id"));
        int userId = result.getInt(result.findColumn("user_id"));
        return new Favorite(favoriteId, breweryId, userId);
    }
}