package edu.lwtech.csd297.tap4tap.daos;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.logging.log4j.*;

import edu.lwtech.csd297.tap4tap.pojos.*;
import java.util.*;

public class UserMemoryDAO implements UserDAO{
    private static final Logger logger = LogManager.getLogger(UserSqlDAO.class.getName());

    private List<User> userDB;

    public UserMemoryDAO(){
        this.userDB = new ArrayList<>();
        addDemoUserData();
    }
    @Override
    public User retrieveByUsername(String name){
        if(name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name can't be null or empty");
        logger.debug("retrieving user list by name, {}", name);
        User result = null;
        for(User user : userDB){
           if(user.getUsername().equals(name)){
            logger.debug("found name {}", name);
            result = user;
            break;
           }
           else{
            logger.debug("not found name {}", name);
           }
        }
        return result;
    }
    @Override
    public boolean insert(User user) {
        if(user != null){
            userDB.add(user);
            logger.debug("adding user: {}", user);
            return true;
        }
        else{
            logger.debug("user can't be null or empty");
            return false;}


    }
    @Override
    public User retrieveByID(int id){
        if(id <=0)
        throw new IllegalArgumentException("id can't be 0 or negative");
        logger.debug("retrieving by id {}",id);
        for(User user : userDB){
            if(user.getUserId() == id){
                return user;
            }
        }
        return null;
    }
    @Override
    public boolean update(User item){
        if(item == null) throw new IllegalArgumentException("user to update can't be null");
        logger.debug("updating user {}", item);
        for(int i = 0; i < userDB.size(); i++){
            if(userDB.get(i).getUserId() == item.getUserId()){
                userDB.set(i, item);
                return true;
            }
        }
        return false;
    }
    @Override
    public int delete(int id){
        if(id <= 0)throw new IllegalArgumentException("id can't be 0 or negative to delete");
        logger.debug("deleting user id: {}", id);
        for(int i = 0; i < userDB.size(); i++){
            if(userDB.get(i).getUserId() == id){
                userDB.remove(i);
                return id;
            }
        }
        return -1;
    }
    @Override
    public int size(){
        logger.debug("getting number of users");
        return userDB.size();
    }

    //===============================================
    public void addDemoUserData(){
        logger.debug("Creating demo users: {}", userDB);

        insert(new User(1, "fred", BCrypt.withDefaults().hashToString(12,"fred".toCharArray()) , "Fred"));

        insert(new User(3, "mary", "mary", "Mary"));

        insert(new User(4, "anne", "anne", "Anne"));

        logger.debug("inserted {} users", userDB.size());
    }
}
