package edu.lwtech.csd297.tap4tap.daos;

import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.*;

import edu.lwtech.csd297.tap4tap.pojos.*;
import edu.lwtech.csd297.tap4tap.utils.*;

public class BrewerySqlDAO implements BreweryDAO<Brewery> {

    private static final Logger logger = LogManager.getLogger(BrewerySqlDAO.class.getName());

    private Connection conn = null;

    public BrewerySqlDAO(Connection conn) {
        this.conn = conn;
    }


    public boolean initialize(String initParams) {
        // logger.info("Connecting to the database...");

        // conn = SQLUtils.connect(initParams);
        // if (conn == null) {
        //     logger.error("Unable to connect to SQL Database: {}", initParams);
        //     return false;
        // }
        // logger.info("...connected!");

        return false;
    }
//does not need it
    // public void terminate() {
    //     // SQLUtils.disconnect(conn);
    //     // conn = null;
    // }
    @Override
    public boolean insert(Brewery brewery) {
        logger.debug("Inserting {}, from city {}, state/province {}, country {}, address {}, {}, {}...", brewery.getName(), brewery.getCity(), brewery.getStateProvince(), brewery.getCountry(), brewery.getAddress1(), brewery.getAddress2(), brewery.getAddress3());

//check if brewery was already inserted

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
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
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

    // public Brewery retrieveByIndex(int index) {
    //     logger.debug("Trying to get Brewery with index: {}", index);

    //     index++; // SQL uses 1-based indexes

    //     if (index < 1)
    //         return null;

    //     String query = "SELECT *";
    //     query += " FROM brewery ORDER BY brewery_id LIMIT " + index;
    //     //don't understand this query statement.**ask do we need? if so, how does this work?


    //     List<SQLRow> rows = SQLUtils.executeSQL(conn, query);

    //     if (rows == null) {
    //         logger.debug("Did not find brewery.");
    //         return null;
    //     }

    //     SQLRow row = rows.get(rows.size() - 1);
    //     Brewery brewery = convertRowToBrewery(row);
    //     return brewery;
    // }
    // ...some at a time
    // public List<Brewery> retrievedByName(String name){
    //     logger.debug("retrieving brewery list by brewery name: {}, name");
    //     List<Brewery> breweries = new ArrayList<>();
    //     String query = "SELECT * FROM brewery WHERE name = ? ORDER BY brewery_id";
    //     ResultSet sqlResults = null;
    //     try(PreparedStatement stmt = conn.prepareStatement(query)){
    //         stmt.setString(1, name);
    //         sqlResults = stmt.executeQuery();
    //     }catch(Exception e){
    //     logger.error("SQL Exceptino caught in executeSQL: {}", e);
    //     }
    //     if(sqlResults == null){
    //         return new ArrayList<>();
    //     }
    //     try{
    //         while(sqlResults.next()){
    //             breweries.add(convertResultToBrewery(sqlResults));
    //         }
    //     }catch(SQLException e){
    //         logger.error("SQL Exception caught in getting results: {}", e);
    //     }
    //     return breweries;
    // }
    // public List<Brewery> retrievedByCountry(String country){
    //     logger.debug("retrieve brewery list by country: {}", country);
    //     List<Brewery> breweries = new ArrayList<>();
    //     String query = "SELECT * FROM brewery WHERE country = ?";
    //     query += "ORDER BY brewery_id";
    //     ResultSet sqlResults = null;
    //     try (PreparedStatement stmt = conn.prepareStatement(query)){
    //         //Substitute in the argument value for the question mark
    //         stmt.setString(1, country);
    //         // Execute the SELECT query
    //         sqlResults = stmt.executeQuery();
    //     }catch(SQLException e){
    //         logger.error("SQL Exception caught in executeSQL: {}", e);
    //     }
    //     if(sqlResults == null){
    //         return new ArrayList<>();
    //     }
    //     try{
    //     while(sqlResults.next()){
    //         breweries.add(convertResultToBrewery(sqlResults));
    //     }}catch(SQLException e){
    //         logger.error("SQL Exception caught in getting results: {}", e);
    //     }
    //     return breweries;
    // }
    // public List<Brewery> retrievedByStateProvince(String stateProvince){
    //     logger.debug("retrieving breweries by state/province {}", stateProvince);
    //     List<Brewery> breweries = new ArrayList<>();
    //     String query = "SELECT * FROM brewery WHERE state_province = ? ORDER BY brewery_id";
    //     ResultSet sqlResults = null;
    //     try(PreparedStatement stmt = conn.prepareStatement(query)){
    //         //Substitute in the argument value for the question mark
    //         stmt.setString(1, stateProvince);
    //         // Execute the SELECT query
    //         sqlResults = stmt.executeQuery();
    //     }catch(SQLException e){
    //         logger.error("SQL Exception caught in executeSQL: {}", e);
    //     }
    //     if(sqlResults == null){
    //         return new ArrayList<>();
    //     }
    //     try{
    //     while(sqlResults.next()){
    //         breweries.add(convertResultToBrewery(sqlResults));
    //     }}catch(SQLException e){
    //         logger.error("SQL Exception caught in executeSQL: {}", e);
    //     }
    //     return breweries;
    // }
    // public List<Brewery> retrievedByCity(String city){
    //     logger.debug("retrieving breweries by city {}", city);
    //     List<Brewery> breweries = new ArrayList<>();
    //     String query = "SELECT * FROM brewery WHERE city = ? ORDER BY brewery_id";
    //     ResultSet sqlResults = null;
    //     try(PreparedStatement stmt = conn.prepareStatement(query)){
    //         //Substitute in the argument value for the question mark
    //         stmt.setString(1, city);
    //         sqlResults = stmt.executeQuery();
    //     }
    //     catch(SQLException e){
    //         logger.error("SQL exception caught in executeSQL: {}", e);
    //     }
    //     if(sqlResults == null){
    //         return new ArrayList<>();
    //     }
    //     try{
    //         while(sqlResults.next()){
    //             breweries.add(convertResultToBrewery(sqlResults));
    //         }
    //     }catch(SQLException e){
    //         logger.error("SQL Exception caught in getting results: {}", e);
    //     }
    //     return breweries;
    // }
    // public List<Brewery> retrievedByZipCode(String zipCode){
    //     List<Brewery> breweries = new ArrayList<>();
    //     logger.debug("retrieving breweries by zip code: {}", zipCode);

    //     String query = "SELET * FROM brewery WHERE postal_code = ? ORDER BY brewery_id";
    //     ResultSet sqlResults = null;
    //     try(PreparedStatement stmt = conn.prepareStatement(query)){
    //         stmt.setString(1, zipCode);
    //         sqlResults = stmt.executeQuery();
    //     }catch(SQLException e){
    //         logger.error("SQL Exception caught in executeSQL: {}", e);
    //     }
    //     if(sqlResults == null){
    //         return new ArrayList<>();
    //     }
    //     try{
    //         while(sqlResults.next()){
    //             breweries.add(convertResultToBrewery(sqlResults));
    //         }
    //     }catch(SQLException e){
    //         logger.error("SQL Exception caught in getting result: {}",e);
    //     }
    //     return breweries;
    // }
    // ...all at a time
    // public List<Brewery> retrieveAll() {
    //     logger.debug("Getting all Breweries...");

    //     String query = "SELECT *";
    //     query += " FROM brewery ORDER BY brewery_id LIMIT 20";

    //     List<Brewery> breweries = new ArrayList<>();
    //     ResultSet sqlResults = null;
    //     try(PreparedStatement stmt = conn.prepareStatement(query)){
    //         sqlResults = stmt.executeQuery();
    //     }catch(SQLException e){
    //         logger.error("SQL Exception caught in executeSQL", e);
    //     }
    //     if(sqlResults == null){
    //         new ArrayList<>();
    //     }
    //     try{
    //         while(sqlResults.next()){
    //             breweries.add(convertResultToBrewery(sqlResults));
    //         }
    //     }catch(SQLException e){
    //         logger.error("SQL Exception caugh in getting results: {}", e);
    //     }

    //     return breweries;
    // }

    // public List<Brewery> retrieveAllIDs() {
    //     logger.debug("Getting all Brewery IDs...");

    //     String query = "SELECT brewery_id FROM brewery ORDER BY brewery_id";
    //     ResultSet sqlResults = null;

    //     logger.debug("Executing SQL statement: {}", query);
    //     try {
    //         PreparedStatement stmt = conn.prepareStatement(query);
    //         sqlResults = stmt.executeQuery();
    //     }
    //     catch (SQLException e) {
    //         logger.error("SQL Exception caught in executeSQL: {}", e);
    //     if (sqlResults == null) {
    //         logger.debug("No breweries found!");
    //         return new ArrayList<>();
    //     }
    // }
    //     List<String> breweryIds = new ArrayList<>();
    //     try{
    //         while(sqlResults.next()) {
    //             try{
    //                 String value = sqlResults.getObject(1).toString();
    //                 breweryIds.add(value);
    //             }
    //             catch(SQLException e){
    //                 logger.error("SQL Exception caught in getting brewery ids: {}",e);
    //             }
    //         }
    //     }catch(SQLException e){
    //         logger.debug("SQL exception caught in interating ResultSet: {}", e);
    //     }
    //     return breweryIds;
    // }

    @Override
    public List<Brewery> search(SearchParameter[] params, int limit, int offset) {

        logger.debug("Searching for brewery with '{}'", params[0].getName());
        List<String> sqlParams = new ArrayList();
        String sqlStatement = "SELECT * FROM brewery";
        if (params.length > 0) {
            sqlStatement += " WHERE";

            for (SearchParameter param : params) {
                if (param.isExact()) {
                    sqlStatement += param.getName() + " = ?";
                } else {
                    sqlStatement += param.getName() + " LIKE ?";
                }
                sqlParams.add(param.getValue());
            }
        }
        sqlStatement += " ORDER BY brewery_id LIMIT ? OFFSET ?";

        List<Brewery> breweries = new ArrayList<>();
        ResultSet sqlResults;
        try (PreparedStatement stmt = conn.prepareStatement(sqlStatement)) {
            int position = 1;
            for(String param: sqlParams){
                stmt.setString(position++, param);
            }
            stmt.setInt(position++, limit);
            stmt.setInt(position++, offset);

            sqlResults = stmt.executeQuery();
        }catch(SQLException e){
            logger.error("SQL Exception caught executeSQL: {}", e);
            return null;
        }

        try{
            while(sqlResults.next()){
                breweries.add(convertResultToBrewery(sqlResults));
            }
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
//?????
    public int size() {
        logger.debug("Getting the number of rows...");

        String query = "SELECT count(*) FROM brewery";

        List<SQLRow> rows = SQLUtils.executeSQL(conn, query);
        if (rows == null) {
            logger.error("No breweries found!");
            return 0;
        }

        String value = rows.get(0).getItem();
        return Integer.parseInt(value);
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
        String websiteUrl = result.getString("webiste_url");
        String phone = result.getString("phone");
        double latitude = result.getDouble("latitude");
        double longitude = result.getDouble("longitude");
        return new Brewery(id,name, breweryType,
        address1, address2, address3, city,
        stateProvince, postalCode, country,
        websiteUrl, phone, longitude, latitude);
    }
}

