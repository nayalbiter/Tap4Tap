package edu.lwtech.csd297.tap4tap.daos;

import java.util.*;
import edu.lwtech.csd297.tap4tap.pojos.*;
import org.apache.logging.log4j.*;


public class BreweryMemoryDAO implements BreweryDAO<Brewery> {
     private static final Logger logger = LogManager.getLogger(BreweryMemoryDAO.class.getName());

     private List<Brewery> breweryDB;

     public BreweryMemoryDAO(){
          this.breweryDB = new ArrayList<>();
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
     @Override
     public boolean insert(Brewery brewery){
          logger.debug("Inserting {}...", brewery);
          if(brewery == null)
               throw new IllegalArgumentException("insert: brewery cannot be null");
          
          breweryDB.add(brewery);
          logger.debug("Item successfully inserted!");
          return breweryDB.size() > 0;
     }

     // Retrieve ------------------------------------------
     // ...one at a time
     @Override
     public Brewery retrieveByID(UUID id){
          // if(id == null || id.equals("") || id.trim().equals("")) throw new IllegalArgumentException("retrieveByID: id cannot be empty, null or blank");
          logger.debug("Getting brewery with ID: {} ...", id);

          Brewery breweryFound = null;
          for(Brewery brewery : breweryDB){
               if(brewery.getBreweryId().equals(id)){
                    breweryFound = brewery;
                    break;
               }
          }
          return breweryFound;
     }

     // @Override
     // public Brewery retrieveByIndex(int index) {
     //      if (index < 0)throw new IllegalArgumentException("retrieveByIndex: Index cannot be negative");
     //      logger.debug("Trying to get Brewery with index: {} ...", index);

     //      return breweryDB.get(index);
     // }
     // // ...all at once
     // public List<Brewery> retrieveAll(){
     //      logger.debug("Getting all breweries ...");
     //      return new ArrayList<>(breweryDB);
     // }
     // public List<String> retrieveAllIDs() {
     //      logger.debug("Getting all Brewery IDs...");

     //      List<String> breweryIds = new ArrayList<>();
     //      for(Brewery brewery : breweryDB){
     //           breweryIds.add(brewery.getBreweryId());
     //      }
     //      return breweryIds;
     // }
     // // ...some at a time
     // public List<Brewery> retrieveByName(String name){
     //      if(name == null || name.equals("")|| name.trim().equals("")) throw new IllegalArgumentException("retrieveByName: name cannot be null or empty or blank");
     //      logger.debug("Getting brewery with name: {} ...", name);

     //      List<Brewery> breweryFound = new ArrayList<>();
     //      for(Brewery brewery : breweryDB){
     //           if(brewery.getName() == name){
     //                breweryFound.add(brewery);
     //                break;
     //           }
     //      }
     //      return breweryFound;
     // }
     // public List<Brewery> retrievedByCountry(String country){
     //      if(country == null || country.equals("")|| country.trim().equals("")) throw new IllegalArgumentException("retrieveByName: name cannot be null or empty or blank");
     //      logger.debug("Getting brewery with country: {} ...", country);

     //      List<Brewery> breweryFound = new ArrayList<>();
     //      for(Brewery brewery : breweryDB){
     //           if(brewery.getName() == country){
     //                breweryFound.add(brewery);
     //                break;
     //           }
     //      }
     //      return breweryFound;
     // }
     // public List<Brewery> retrievedByStateProvince(String stateProvince){
     //      if(stateProvince == null || stateProvince.equals("")|| stateProvince.trim().equals("")) throw new IllegalArgumentException("retrieveByName: name cannot be null or empty or blank");
     //      logger.debug("Getting brewery with country: {} ...", stateProvince);

     //      List<Brewery> breweryFound = new ArrayList<>();
     //      for(Brewery brewery : breweryDB){
     //           if(brewery.getName() == stateProvince){
     //                breweryFound.add(brewery);
     //                break;
     //           }
     //      }
     //      return breweryFound;
     // }
     // public List<Brewery> retrievedByCity(String city){
     //      if(city == null || city.equals("")|| city.trim().equals("")) throw new IllegalArgumentException("retrieveByName: name cannot be null or empty or blank");
     //      logger.debug("Getting brewery with country: {} ...", city);

     //      List<Brewery> breweryFound = new ArrayList<>();
     //      for(Brewery brewery : breweryDB){
     //           if(brewery.getName() == city){
     //                breweryFound.add(brewery);
     //                break;
     //           }
     //      }
     //      return breweryFound;
     // }
     // public List<Brewery> retrievedByZipCode(String zipCode){
     //      if(zipCode == null || zipCode.equals("")|| zipCode.trim().equals("")) throw new IllegalArgumentException("retrieveByName: name cannot be null or empty or blank");
     //      logger.debug("Getting brewery with country: {} ...", zipCode);

     //      List<Brewery> breweryFound = new ArrayList<>();
     //      for(Brewery brewery : breweryDB){
     //           if(brewery.getName() == zipCode){
     //                breweryFound.add(brewery);
     //                break;
     //           }
     //      }
     //      return breweryFound;
     // }

     /**
      * search breweries with country, state/province, city, brewery name, and zip code
      * params String array search parameters
      * returns list of Brewery based on search
      */
     @Override
     public List<Brewery> search(SearchParameter[] params, int limit, int offset){
          List<Brewery> result = new ArrayList<>();
          for(Brewery brewery : breweryDB){
               if (offset > 0) {
                    offset--;
                    continue;
               }
               if(params.length > 0){
                    for (SearchParameter param : params) {
                         if (param.getName().equals("country")) {
                              if (!brewery.getCountry().equals(param.getValue())) {
                                   continue;
                              }
                         }
                         if (param.getName().equals("name")) {
                              if (!brewery.getName().equals(param.getValue())) {
                                   continue;
                              }
                         }
                         if (param.getName().equals("state_province")) {
                              if (!brewery.getStateProvince().equals(param.getValue())) {
                                   continue;
                              }
                         }
                         if (param.getName().equals("city")) {
                              if (!brewery.getCity().equals(param.getValue())) {
                                   continue;
                              }
                         }
                         if (param.getName().equals("postal_code")) {
                              if (!brewery.getPostalCode().equals(param.getValue())) {
                                   continue;
                              }
                         }
                         result.add(brewery);
                    }
               }
               else{
                    result.add(brewery);
               }
               if (result.size() == limit) {
                    break;
               }
          }
          return result;
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

     public int delete(UUID breweryId){
          if(breweryId == null) throw new IllegalArgumentException("delete: brewery name cannot be null or empty or blank");
          logger.debug("Trying to delete brewery with brewery name: {} ...", breweryId);
          Brewery breweryFound = null;
          for(Brewery brewery : breweryDB){
               if(brewery.getBreweryId().equals(breweryId)){
                    breweryFound = brewery;
                    break;
               }
          }
          if(breweryFound != null){
               breweryDB.remove(breweryFound);
               logger.debug("Successfully deleted brewery name: {}", breweryId);
               return 1;
          }
          else{
               logger.debug("Unabe to delete brewery name: {}. Brewery not found.", breweryId);
               return -1;
          }
     }
     //===========================================================
     public void addDemoBreweryData(){
          logger.debug(("Creating demo Breweries"), breweryDB);

          insert(new Brewery(new UUID(0, 0), "Tap", "micro", "1st fl 155th st NE", "", "", "Redmond", "Washington", "98052", "United States", "http://www.tap.com","425-809-9895", 7.2786, 12.3986));

          insert(new Brewery(new UUID(2, 0), "Chill", "micro", "100tt fl 80th st NE", "", "", "Renton", "Washington", "98052", "United States", "http://www.tap.com","425-809-9895", 7.2956, 12.4956));

          insert(new Brewery(new UUID(0, 1), "Brew", "micro", "11st fl 170th st SE", "", "", "Bellevue", "Washington", "98052", "United States", "http://www.tap.com","425-809-9895", 7.2986, 12.4986));

          insert(new Brewery(new UUID(1, 0), "Beer Crawl", "micro", "5st fl 134th st NE", "", "", "Seoul", "Seoul", "", "South Korea", "http://www.crawlinbeer.com","784-809-9895", 5.2986, 1.4986));

          insert(new Brewery(new UUID(1, 1), "Beer Hub", "micro", "6st fl 132nd Ave NE", "", "", "Busan", "Seoul", "", "South Korea", "http://www.crawlinbeer.com","784-809-9895", 1.2986, 1.4980));
          
          insert(new Brewery(new UUID(1, 1), "together Hub", "micro", "6st fl 132nd Ave NE", "", "", "London", "London", "", "England", "http://www.crawlinbeer.com","784-809-9895", 6.2986, 1.4976));

          logger.debug("{} lists inserted", breweryDB.size());
     }

}
