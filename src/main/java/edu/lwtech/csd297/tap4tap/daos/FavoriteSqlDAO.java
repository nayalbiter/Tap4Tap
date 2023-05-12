package edu.lwtech.csd297.tap4tap.daos;

import java.util.List;

import edu.lwtech.csd297.tap4tap.pojos.Favorite;
import edu.lwtech.csd297.tap4tap.pojos.SearchParameter;

public class FavoriteSqlDAO implements DAO<Favorite, Integer> {

    @Override
    public boolean initialize(String initParams) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    @Override
    public void terminate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'terminate'");
    }

    @Override
    public int insert(Favorite item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public Favorite retrieveByID(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrieveByID'");
    }

    @Override
    public Favorite retrieveByIndex(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrieveByIndex'");
    }

    @Override
    public List<Favorite> retrieveAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrieveAll'");
    }

    @Override
    public List<Integer> retrieveAllIDs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrieveAllIDs'");
    }

    @Override
    public List<Favorite> search(SearchParameter[] params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public boolean update(Favorite item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }
    
}
