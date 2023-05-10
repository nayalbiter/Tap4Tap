package edu.lwtech.csd297.tap4tap.daos;

import java.util.*;
import edu.lwtech.csd297.tap4tap.pojos.*;
import org.apache.logging.log4j.*;


public class BreweryMemoryDAO implements DAO<Brewery> {
     private static final Logger logger = LogManager.getLogger(BreweryMemoryDAO.class.getName());

     private List<Brewery> breweryDB;

     public BreweryMemoryDAO(){
          this.breweryDB = new ArrayList<>();

          // String query = "SELECT name FROM brewereies WHERE id = ?";
          // execute(conn, query, brewery.getId().toString())
     }
     // Life Cycle ----------------------------------------
     public boolean initialize(String initParams){
          if(initParams == null) throw new IllegalArgumentException("init: initParams cannot be null");
          logger.debug("Initalizing Brewery Memory DAO with: '{}'", initParams);
          addDemoBreweryData();
          return true;
     }
     public void terminate(){
          logger.debug("Terminating Brewery Memory DAO...");
          breweryDB = null;
     }

     // Create --------------------------------------------
     public int insert(Brewery brewery){
          if(brewery == null)
               throw new IllegalArgumentException("insert: brewery cannot be null");
          logger.debug("Item successfully inserted!");
          return brewery.getBreweryId();
     }

     // Retrieve ------------------------------------------
     // ...one at a time
     public Brewery retrieveByID(String id){
          if(id == null || id.equals("") || id.trim().equals("")) throw new IllegalArgumentException("retrieveByID: id cannot be empty, null or blank");
          logger.debug("Getting brewery with ID: {} ...", id);

          Brewery breweryFound = null;
          for(Brewery brewery : breweryDB){
               if(brewery.getBreweryId() == id){
                    breweryFound = brewery;
                    break;
               }
          }
          return breweryFound;
     }
     public Brewery retrieveByName(String name){
          if(name == null || name.equals("")|| name.trim().equals("")) throw new IllegalArgumentException("retrieveByName: name cannot be null or empty or blank");
          logger.debug("Getting brewery with name: {} ...", name);

          Brewery breweryFound = null;
          for(Brewery brewery : breweryDB){
               if(brewery.getBreweryName() == name){
                    breweryFound = brewery;
                    break;
               }
          }
          return breweryFound;
     }
     // ...all at once
     public List<Brewery> retrieveAll(){
          logger.debug("Getting all breweries ...");
          return new ArrayList<>(breweryDB);

     }
     // public List<Integer> retrieveAllIDs(){
     //      logger.debug("Getting brewery IDs...");

     // }
     // ...some at a time
     public List<Brewery> search(String keyword){
          if (keyword == null || keyword.equals("")|| keyword.trim().equals(""))throw new IllegalArgumentException("search: keyword cannot be null or empty or blank");
          logger.debug("Searching for breweries containing: '{}'", keyword);
          keyword = keyword.toLowerCase();
          List<Brewery> breweriesFound = new ArrayList<>();
          for(Brewery brewery : breweryDB){
               breweriesFound.add(brewery);
               break;
          }
          logger.debug("Found {} breweries with the keyword '{}'!", breweriesFound.size(), keyword);
          return breweriesFound;
     }
     public int size(){
          return breweryDB.size();
     }
     // Update ---------------------------------------------
     public boolean update(Brewery brewery){
          if(brewery == null) throw new IllegalArgumentException("update: brewery cannot be null");
          logger.debug("Trying to update brewery: {} ...", brewery.getBreweryID());
          for (int i = 0; i < breweryDB.size(); i++){
               if(breweryDB.get(i).getBreweryID() == brewery.getBreweryID()){
                    breweryDB.set(i, brewery);
                    logger.debug("Successfully updated brewery: {} !", brewery.getBreweryID());
                    return true;
               }
          }
          logger.debug("Unable to update list: {}. BreweryID not found.", brewery.getBreweryID());
          return false;
     }

     // Delete ---------------------------------------------

     public void delete(String breweryName){
          if(breweryName == null || breweryName.equals("") || breweryName.trim().equals("")) throw new IllegalArgumentException("delete: brewery name cannot be null or empty or blank");
          logger.debug("Trying to delete brewery with brewery name: {} ...", breweryName);
          Brewery breweryFound = null;
          for(Brewery brewery : breweryDB){
               if(brewery.getBreweryName() == breweryName){
                    breweryFound = brewery;
                    break;
               }
          }
          if(breweryFound != null){
               breweryDB.remove(breweryFound);
               logger.debug("Successfully deleted brewery name: {}", breweryName);
          }
          else{
               logger.debug("Unabe to delete brewery name: {}. Brewery not found.", breweryName);
          }
     }
     //===========================================================
     private void addDemoBreweryData(){
          logger.debug(("Creating demo Breweries"), breweryDB);

          String description;
          List<String> breweries;

          int 
     }
}
