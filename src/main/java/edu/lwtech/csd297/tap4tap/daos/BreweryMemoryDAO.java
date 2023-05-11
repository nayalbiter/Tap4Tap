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
     public String insert(Brewery brewery){
          if(brewery == null)
               throw new IllegalArgumentException("insert: brewery cannot be null");
          logger.debug("Inserting {}...", brewery);
          breweryDB.add(brewery);
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
               if(brewery.getName() == name){
                    breweryFound = brewery;
                    break;
               }
          }
          return breweryFound;
     }
     // public brewery retureiveBycountry()
     public Brewery retrieveByIndex(int index) {
          if (index < 0)throw new IllegalArgumentException("retrieveByIndex: Index cannot be negative");
          logger.debug("Trying to get Brewery with index: {} ...", index);

          return breweryDB.get(index);
      }
     // ...all at once
     public List<Brewery> retrieveAll(){
          logger.debug("Getting all breweries ...");
          return new ArrayList<>(breweryDB);
     }
     public List<String> retrieveAllIDs() {
          logger.debug("Getting all Brewery IDs...");

          List<String> breweryIds = new ArrayList<>();
          for(Brewery brewery : breweryDB){
               breweryIds.add(brewery.getBreweryId());
          }
          return breweryIds;
     }
     // ...some at a time
     public List<Brewery> searchByKeys(String country, String stateProvince, String city, String breweryName, String zipCode){
          if(country == null || country.equals("") || country.trim().equals("")) throw new IllegalArgumentException("SearchByKeys: country cannot be null or empty or blank");
          logger.debug("Searching for breweries countaining: '{}', '{}', '{}', '{},' '{}'", country, stateProvince, city, breweryName, zipCode);
          country = country.trim().toLowerCase();
          stateProvince = stateProvince.trim().toLowerCase();
          city = city.trim().toLowerCase();
          breweryName = breweryName.trim().toLowerCase();
          zipCode = zipCode.trim().toLowerCase();
          List<Brewery> breweryFound = new ArrayList<>();
          for(Brewery brewery : breweryDB){
               if(brewery.getCountry().toLowerCase().contains(country)){
                    if(brewery.getStateProvince().toLowerCase().contains(stateProvince)){
                         if(brewery.getName().toLowerCase().contains(breweryName)){
                              if(brewery.getCity().toLowerCase().contains(city)){
                                   if(brewery.getPostalCode().contains(zipCode)){
                                        breweryFound.add(brewery);
                                   }
                              }
                         }
                    }
               }
          }
          logger.debug("Found {} breweries with keywords '{}', '{}', '{}', '{}', '{}'", breweryFound.size(), country, stateProvince, city, breweryName, zipCode);
          return breweryFound;
     }
     public List<Brewery> search(String keyword){
          if (keyword == null || keyword.equals("")|| keyword.trim().equals(""))throw new IllegalArgumentException("search: keyword cannot be null or empty or blank");
          logger.debug("Searching for breweries containing: '{}'", keyword);
          keyword = keyword.toLowerCase();
          List<Brewery> breweriesFound = new ArrayList<>();
          for(Brewery brewery : breweryDB){
               if(brewery.getName().toLowerCase().contains(keyword)){
                    breweriesFound.add(brewery);
               }
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
          logger.debug("Trying to update brewery: {} ...", brewery.getBreweryId());
          for (int i = 0; i < breweryDB.size(); i++){
               if(breweryDB.get(i).getBreweryId() == brewery.getBreweryId()){
                    breweryDB.set(i, brewery);
                    logger.debug("Successfully updated brewery: {} !", brewery.getBreweryId());
                    return true;
               }
          }
          logger.debug("Unable to update list: {}. BreweryID not found.", brewery.getBreweryId());
          return false;
     }

     // Delete ---------------------------------------------

     public void delete(String breweryName){
          if(breweryName == null || breweryName.equals("") || breweryName.trim().equals("")) throw new IllegalArgumentException("delete: brewery name cannot be null or empty or blank");
          logger.debug("Trying to delete brewery with brewery name: {} ...", breweryName);
          Brewery breweryFound = null;
          for(Brewery brewery : breweryDB){
               if(brewery.getName().equals(breweryName)){
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

          insert(new Brewery(new UUID(0, 0), "Tap", "micro", "1st fl 155st NE", "", "", "Redmond", "Washington", "98052", "United States", "http://www.tap.com","425-809-9895", 7.2986, 12.4986));

          insert(new Brewery(new UUID(1, 0), "Beer Crawl", "micro", "5st fl 134st NE", "", "", "Seoul", "Seoul", "", "South Korea", "http://www.crawlinbeer.com","784-809-9895", 5.2986, 1.4986));
          logger.debug("{} lists inserted", breweryDB.size());
     }
     @Override
     public Brewery retrieveByID(int id) {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'retrieveByID'");
     }
     @Override
     public void delete(int id) {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'delete'");
     }
     @Override
     public Brewery retrieveByIndex(String index) {
          // TODO Auto-generated method stub
          throw new UnsupportedOperationException("Unimplemented method 'retrieveByIndex'");
     }
}
