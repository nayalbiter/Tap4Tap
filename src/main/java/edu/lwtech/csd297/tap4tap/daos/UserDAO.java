package edu.lwtech.csd297.tap4tap.daos;
import edu.lwtech.csd297.tap4tap.pojos.*;

import java.util.*;

// Data Access Object (DAO)
public interface UserDAO {

    // Create --------------------------------------------
    boolean insert(User item);

    // Retrieve ------------------------------------------
    User retrieveByID(int id);
    User retrieveByUsername(String username);
    // Update ---------------------------------------------
    boolean update(User item);

    // Delete ---------------------------------------------
    int delete(int id);

    // Miscellaneous -------------------------------------
    int size();
}
