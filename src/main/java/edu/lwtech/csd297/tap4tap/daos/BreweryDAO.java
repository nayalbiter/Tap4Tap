package edu.lwtech.csd297.tap4tap.daos;
import edu.lwtech.csd297.tap4tap.pojos.*;

import java.util.*;

// Brewery Data Access Object (DAO) Interface

public interface BreweryDAO<T> {

    // Create --------------------------------------------
    boolean insert(T item);

    // Retrieve ------------------------------------------
    // ...one at a time
    T retrieveByID(UUID id);

    // ...some at a time
    List<T> search(SearchParameter[] params);

    // Update ---------------------------------------------
    boolean update(T item);

    // Delete ---------------------------------------------
    int delete(UUID id);

    // Miscellaneous -------------------------------------
    int size();
}
