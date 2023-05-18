package edu.lwtech.csd297.tap4tap.daos;
import edu.lwtech.csd297.tap4tap.pojos.*;

import java.util.*;

// Brewery Data Access Object (DAO) Interface

public interface BreweryDAO<T> {

    // Life Cycle ----------------------------------------
    boolean initialize(String initParams);
    // void terminate();

    // Create --------------------------------------------
    boolean insert(T item);

    // Retrieve ------------------------------------------
    // ...one at a time
    T retrieveByID(UUID id);
    //T retrieveByIndex(int index);

    // ...some at a time
    // List<T> retrievedByName(String name);
    // List<T> retrievedByCountry(String country);
    // List<T> retrievedByStateProvince(String stateProvince);
    // List<T> retrievedByCity(String city);
    // List<T> retrievedByZipCode(String zipCode);
    List<T> search(SearchParameter[] params, int limit, int offset);

    // ...all at a time
    // List<T> retrieveAll();
    //List<String> retrieveAllIDs();

    // Update ---------------------------------------------
    boolean update(T item);

    // Delete ---------------------------------------------
    int delete(UUID id);

    // Miscellaneous -------------------------------------
    int size();
}
