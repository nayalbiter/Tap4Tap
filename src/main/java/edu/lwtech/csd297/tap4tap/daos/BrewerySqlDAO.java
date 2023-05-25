package edu.lwtech.csd297.tap4tap.daos;

import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.*;

import edu.lwtech.csd297.tap4tap.pojos.*;
import edu.lwtech.csd297.tap4tap.utils.*;

public class BrewerySqlDAO implements BreweryDAO {

    private static final Logger logger = LogManager.getLogger(BrewerySqlDAO.class.getName());

    private Connection conn = null;

    public BrewerySqlDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insert(Brewery brewery) {
        logger.debug("Inserting {}, from city {}, state/province {}, country {}, address {}, {}, {}...", brewery.getName(), brewery.getCity(), brewery.getStateProvince(), brewery.getCountry(), brewery.getAddress1(), brewery.getAddress2(), brewery.getAddress3());

        //!!implement to check if brewery was already inserted

        String query = "INSERT INTO brewery";
        query += "(brewery_id, name, brewery_type, address_1, address_2, address_3, city, state_province, postal_code, country, phone, website_url, longitude, latitude)";
        query += "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        String[] returnColumns = {};
        try ( PreparedStatement stmt = conn.prepareStatement(query, returnColumns); ){
            // Substitute in the argument values for the question marks
            stmt.setObject(1, brewery.getBreweryId());
            stmt.setString(2, brewery.getName());
            stmt.setString(3, brewery.getBreweryType());
            stmt.setString(4, brewery.getAddress1());
            stmt.setString(5, brewery.getAddress2());
            stmt.setString(6, brewery.getAddress3());
            stmt.setString(7, brewery.getCity());
            stmt.setString(8, brewery.getStateProvince());
            stmt.setString(9, brewery.getPostalCode());
            stmt.setString(10, brewery.getCountry());
            stmt.setString(11, brewery.getPhone());
            stmt.setString(12, brewery.getWebsiteUrl());

            stmt.setDouble(13, brewery.getLongitude());
            stmt.setDouble(14, brewery.getLatitude());
            logger.debug("Executing SQL Insert: {}", query);
             // Execute the INSERT statement
            stmt.executeUpdate();

            logger.debug("Brewery successfully inserted with ID = {}", brewery.getBreweryId());
            return true;
        }catch (SQLException e) {
            logger.error("SQL Exception caught in executeSQLInsert: {}, {}", query, e);
            return false;
        }
    }

    @Override
    public Brewery retrieveByID(UUID breweryId) {
        logger.debug("Trying to get Brewery with ID: {}", breweryId);

        String query = "SELECT *";
        query += " FROM brewery WHERE brewery_id=?";

        logger.debug("Executing SQL statement: {}", query);
        ResultSet sqlResults;
        try(PreparedStatement stmt = conn.prepareStatement(query);){
            // Substitute in the argument values for the question marks
            stmt.setObject(1, breweryId);

            // Execute the SELECT query
            sqlResults = stmt.executeQuery();

            return convertResultToBrewery(sqlResults);
        } catch(Exception e){
            logger.debug("Sql Exception caught while selecting brewery by Id: {}", breweryId);
            return null;
        }
    }

    @Override
    public List<Brewery> search(List<SearchParameter> params) {
        String sqlStatement = "SELECT * FROM brewery";
        if (params.size() > 0) {
            String particle = "WHERE";
            for (SearchParameter param : params) {
                sqlStatement += " " + particle;
                if (param.isExact()) {
                    sqlStatement += " " + param.getName() + " = ?";
                } else {
                    sqlStatement += " " + param.getName() + " LIKE ?";
                }
                particle = " AND";
            }
        }
        sqlStatement += " ORDER BY brewery_id LIMIT 8300";
        logger.debug("Preparing search statement: " + sqlStatement);

        List<Brewery> breweries = new ArrayList<>();
        ResultSet sqlResults;
        try (PreparedStatement stmt = conn.prepareStatement(sqlStatement)) {
            for (int i = 0; i < params.size(); i++){
                // Substitute in the argument values for the question marks
                if(params.get(i).isExact()){stmt.setString(i + 1, params.get(i).getValue());}
                else{stmt.setString(1 + i, "%" + params.get(i).getValue() + "%");}
            }
            logger.debug(stmt);
            sqlResults = stmt.executeQuery();
        }catch(SQLException e){
            logger.error("SQL Exception caught executeSQL: {}", e);
            return null;
        }

        try{
            while(sqlResults.next()){
                breweries.add(convertResultToBrewery(sqlResults));
            }
            logger.error("Returning " + breweries.size() + " breweries");
        }catch(SQLException e){
            logger.error("SQL Exception caught in getting results: {}", e);
            return null;
        }
        return breweries;
    }

    public boolean update(Brewery brewery) {
        logger.debug("Trying to update Brewery with Brewery: {}", brewery);

        String query = "UPDATE FROM brewery WHERE brewery_id = ?";
        UUID breweryId = brewery.getBreweryId();
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, breweryId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
        catch(SQLException e){
            logger.error("Error caught in excuteUpdate: {}", e);
            return false;
        }
    }

    public int delete(UUID breweryId) {
        logger.debug("Trying to delete Brewery with ID: {}", breweryId);

        String query = "DELETE FROM brewery WHERE brewery_id=?";

        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1,breweryId);
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
        String query = "SELECT count(*) FROM brewery";
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

    private Brewery convertResultToBrewery(ResultSet result) throws SQLException {
        UUID id = (UUID) result.getObject(result.findColumn("brewery_id"));
        String name = result.getString("name");
        String breweryType = result.getString("brewery_type");
        String address1 = result.getString("address_1");
        String address2 = result.getString("address_2");
        String address3 = result.getString("address_3");
        String city = result.getString("city");
        String stateProvince = result.getString("state_province");
        String country = result.getString("country");
        String postalCode = result.getString("postal_code");
        String websiteUrl = result.getString("website_url");
        String phone = result.getString("phone");
        double latitude = result.getDouble("latitude");
        double longitude = result.getDouble("longitude");
        return new Brewery(id,name, breweryType,
        address1, address2, address3, city,
        stateProvince, postalCode, country,
        websiteUrl, phone, longitude, latitude);
    }
}

