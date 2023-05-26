package edu.lwtech.csd297.tap4tap.daos;

import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.*;

import edu.lwtech.csd297.tap4tap.pojos.*;

public class UserSqlDAO implements UserDAO {

    private static final Logger logger = LogManager.getLogger(UserSqlDAO.class.getName());

    private Connection conn = null;

    public UserSqlDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insert(User user) {
        //!!implement to check if user was already inserted

        String query = "INSERT INTO user";
        query += "(user_id, username, hashed_password, display_name)";
        query += "VALUES (?,?,?,?)";

        String[] returnColumns = {};
        try ( PreparedStatement stmt = conn.prepareStatement(query, returnColumns); ){
            // Substitute in the argument values for the question marks
            stmt.setObject(1, user.getUserId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getHashedPasword());
            stmt.setString(4, user.getDisplayName());
            
            logger.debug("Executing SQL Insert: {}", query);
             // Execute the INSERT statement
            stmt.executeUpdate();

            logger.debug("User successfully inserted with ID = {}", user.getUserId());
            return true;
        }catch (SQLException e) {
            logger.error("SQL Exception caught in executeSQLInsert: {}, {}", query, e);
            return false;
        }
    }
    //implement
    @Override
    public User retrieveByUsername(String username){

        logger.debug("Trying to get User with usernam: {}", username);

        String query = "SELECT *";
        query += " FROM user WHERE username=?";

        logger.debug("Executing SQL statement: {}", query);
        ResultSet sqlResults;
        try(PreparedStatement stmt = conn.prepareStatement(query);){
            // Substitute in the argument values for the question marks
            stmt.setObject(1, username);
<<<<<<< Updated upstream

            // Execute the SELECT query
            sqlResults = stmt.executeQuery();

            return convertResultToUser(sqlResults);
        } catch(Exception e){
            logger.debug("Sql Exception caught while selecting user by username: {}", username);
            return null;
        }
=======
            logger.debug("setting name to query {}", stmt.toString());
            // Execute the SELECT query
            sqlResults = stmt.executeQuery();

        } catch(Exception e){
            logger.debug("Sql Exception: {} caught while selecting user by username: {} using query: {}", e, username, query);
            return null;
        }
        try{
            while(sqlResults.next()){
                return(convertResultToUser(sqlResults));
            }
        }
        catch(SQLException e){
            logger.error("SQL Exception caught getting results: {}", e);
            return null;
        }
        return null;
>>>>>>> Stashed changes
    }

    @Override
    public User retrieveByID(int userId) {
        logger.debug("Trying to get User with ID: {}", userId);

        String query = "SELECT *";
        query += " FROM user WHERE user_id=?";

        logger.debug("Executing SQL statement: {}", query);
        ResultSet sqlResults;
        try(PreparedStatement stmt = conn.prepareStatement(query);){
            // Substitute in the argument values for the question marks
            stmt.setObject(1, userId);

            // Execute the SELECT query
            sqlResults = stmt.executeQuery();

            return convertResultToUser(sqlResults);
        } catch(Exception e){
            logger.debug("Sql Exception caught while selecting user by Id: {}", userId);
            return null;
        }
    }

    public boolean update(User user) {
        logger.debug("Trying to update User with User: {}", user);

        String query = "UPDATE FROM user WHERE user_id = ?";
        int userId = user.getUserId();
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, userId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
        catch(SQLException e){
            logger.error("Error caught in excuteUpdate: {}", e);
            return false;
        }
    }

    public int delete(int userId) {
        logger.debug("Trying to delete User with ID: {}", userId);

        String query = "DELETE FROM user WHERE user_id=?";

        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1,userId);
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
        String query = "SELECT count(*) FROM user";
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

    private User convertResultToUser(ResultSet result) throws SQLException {
        int userId =  result.getInt(result.findColumn("user_id"));
        String username = result.getString(result.findColumn("username"));
        String hashedPassword = result.getString(result.findColumn("hashed_password"));
        String displayName = result.getString(result.findColumn("display_name"));
        return new User(userId, username, hashedPassword, displayName);
    }
}

