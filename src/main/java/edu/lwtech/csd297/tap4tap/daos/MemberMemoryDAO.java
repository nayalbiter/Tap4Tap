package edu.lwtech.csd297.tap4tap.daos;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.*;

import edu.lwtech.csd297.tap4tap.pojos.*;

// Memory-based DAO class - stores objects in a List.  No persistance.

public class MemberMemoryDAO implements DAO<Member, Integer> {

    private static final Logger logger = LogManager.getLogger(MemberMemoryDAO.class.getName());

    private AtomicInteger nextMemberRecID;
    private List<Member> memberDB;          // Our "database" table for Members

    // ----------------------------------------------------------------

    public MemberMemoryDAO() {
        this.nextMemberRecID = new AtomicInteger(1000);
        this.memberDB = new ArrayList<>();
    }

    // ----------------------------------------------------------------

    public boolean initialize(String initParams) {
        if (initParams == null)
            throw new IllegalArgumentException("init: initParams cannot be null");
        logger.debug("Initializing Member Memory DAO with: '{}'", initParams);

        addDemoMemberData();
        return true;
    }

    public void terminate() {
        logger.debug("Terminating Member Memory DAO...");
        memberDB = null;
    }

    public String insert(Member member) {
        if (member == null)
            throw new IllegalArgumentException("insert: member cannot be null");
        if (member.getUserId() != -1)
            throw new IllegalArgumentException("insert: member is already in database (recID != -1)");
        logger.debug("Inserting {}...", member);

        member.setUserId(nextMemberRecID.incrementAndGet());
        memberDB.add(member);

        logger.debug("Member successfully inserted!");
        return Integer.toString(member.getUserId());
    }

    public void delete(Integer id) {
        if (id < 0)
            throw new IllegalArgumentException("delete: id cannot be negative");
        logger.debug("Trying to delete member with ID: {} ...", id);

        Member memberFound = null;
        for (Member member : memberDB) {
            if (member.getUserId() == id) {
                memberFound = member;
                break;
            }
        }
        if (memberFound != null) {
            memberDB.remove(memberFound);
            logger.debug("Successfully deleted member with ID: {}", id);
        } else {
            logger.debug("Unable to delete member with ID: {}. Member not found.", id);
        }
    }

    public Member retrieveByID(int id) {
        if (id < 0)
            throw new IllegalArgumentException("retrieveByID: id cannot be negative");
        logger.debug("Getting member with ID: {} ...", id);

        Member memberFound = null;
        for (Member member : memberDB) {
            if (member.getUserId() == id) {
                memberFound = member;
                break;
            }
        }
        return memberFound;
    }

    public Member retrieveByIndex(int index) {
        // Note: indexes are zero-based
        if (index < 0)
            throw new IllegalArgumentException("retrieveByIndex: index cannot be negative");
        logger.debug("Getting member with index: {} ...", index);

        return memberDB.get(index);
    }

    public List<Member> retrieveByName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("retrieveByName: name cannot be empty or null");
        logger.debug("Getting member with name: {} ...", name);
        List<Member> members = new ArrayList<>();
        for (Member member : memberDB) {
            if (member.getUsername().equals(name))
                members.add(member);
        }
        return members;
    }

    public List<Member> retrieveAll() {
        logger.debug("Getting all items...");
        return new ArrayList<>(memberDB);               // Return copy of Member collection
    }

    public List<String> retrieveAllIDs() {
        logger.debug("Getting all member IDs...");

        List<String> recIDs = new ArrayList<>();
        for (Member member : memberDB) {
            //recIDs.add(member.getUserId());
        }
        return recIDs;
    }

    public List<Member> search(String keyword) {
        if (keyword == null)
            throw new IllegalArgumentException("search: keyword cannot be null");
        logger.debug("Searching for members with username containing: '{}'", keyword);

        keyword = keyword.toLowerCase();
        List<Member> membersFound = new ArrayList<>();
        for (Member member : memberDB) {
            if (member.getUsername().toLowerCase().contains(keyword)) {
                membersFound.add(member);
                break;
            }
        }
        logger.debug("Found {} members with the keyword '{}'!", membersFound.size(), keyword);
        return membersFound;
    }

    public int size() {
        return memberDB.size();
    }

    public boolean update(Member member) {
        if (member == null)
            throw new IllegalArgumentException("update: member cannot be null");
        logger.debug("Trying to update member: {} ...", member.getUserId());

        for (int i = 0; i < memberDB.size(); i++) {
            if (memberDB.get(i).getUserId() == member.getUserId()) {
                memberDB.set(i, member);
                logger.debug("Successfully updated member: {} !", member.getUserId());
                return true;
            }
        }
        logger.debug("Unable to update member: {}.  RecID not found.", member);
        return false;
    }

    // =================================================================

    private void addDemoMemberData() {
        logger.debug("Creating demo Members...");

        Member member;
        member = new Member("fred@lwtech.edu", "fred");
        insert(member);
        member = new Member("tom@lwtech.edu", "tom");
        insert(member);
        member = new Member("mary@lwtech.edu", "mary");
        insert(member);

        logger.debug("{} members inserted!", size());
    }

    @Override
    public Member retrieveByID(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrieveByID'");
    }

    @Override
    public List<Member> search(String[] params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    // @Override
    // public Member retrieveByIndex(String index) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'retrieveByIndex'");
    // }



}
