package edu.lwtech.csd297.tap4tap.daos;
import edu.lwtech.csd297.tap4tap.pojos.*;

import java.util.*;

// Data Access Object (DAO)
public interface FavoriteDAO {

    // Create --------------------------------------------
    boolean insert(Favorite item);

    // Retrieve ------------------------------------------
    Favorite retrieveByID(int id);
    List<Favorite> retrieveByUserId(int id);

    // Update ---------------------------------------------
    boolean update(Favorite item);

    // Delete ---------------------------------------------
    int delete(int id);

    // Miscellaneous -------------------------------------
    int size();
}
