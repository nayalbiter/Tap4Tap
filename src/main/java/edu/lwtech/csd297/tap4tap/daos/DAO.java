package edu.lwtech.csd297.tap4tap.daos;

import java.util.*;

// Generic Data Access Object (DAO) Interface

public interface DAO<T> {

    // Life Cycle ----------------------------------------
    boolean initialize(String initParams);
    void terminate();

    // Create --------------------------------------------
    String insert(T item);

    // Retrieve ------------------------------------------
    // ...one at a time
    T retrieveByID(String id);
    T retrieveByIndex(int index);
    // ...all at once
    List<T> retrieveByName(String name);
    List<T> retrieveAll();
    List<String> retrieveAllIDs();
    
    // ...some at a time
    List<T> search(String[] params);
    //List<T> searchByKeys(String country, String stateProvince, String city, String breweryName, String zipCode);
    // Update ---------------------------------------------
    boolean update(T item);
    
    // Delete ---------------------------------------------
    void delete(String id);

    // Miscellaneous -------------------------------------
    int size();
}
