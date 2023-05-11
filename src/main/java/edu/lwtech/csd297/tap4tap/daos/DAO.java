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
    T retrieveByID(int id);
    T retrieveByIndex(String index);
    T retrieveByName(String name);
    // ...all at once
    List<T> retrieveAll();
    List<String> retrieveAllIDs();
    // ...some at a time
    List<T> search(String keyword);
    List<T> searchByKeys(String country, String stateProvince, String city, String breweryName, String zipCode);
    // Update ---------------------------------------------
    boolean update(T item);
    
    // Delete ---------------------------------------------
    void delete(int id);

    // Miscellaneous -------------------------------------
    int size();
}
