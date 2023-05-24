package edu.lwtech.csd297.tap4tap.daos;
import edu.lwtech.csd297.tap4tap.pojos.*;

import java.util.*;

// Brewery Data Access Object (DAO) Interface

public interface BreweryDAO {

    // Create --------------------------------------------
    boolean insert(Brewery item);

    // Retrieve ------------------------------------------
    // ...one at a time
    Brewery retrieveByID(UUID id);

    // ...some at a time
    List<Brewery> search(List<SearchParameter> params);

    // Update ---------------------------------------------
    boolean update(Brewery item);

    // Delete ---------------------------------------------
    int delete(UUID id);

    // Miscellaneous -------------------------------------
    int size();
}
