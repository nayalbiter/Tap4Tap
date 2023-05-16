package edu.lwtech.csd297.tap4tap;

import org.junit.jupiter.api.*;

import edu.lwtech.csd297.tap4tap.pojos.*;

import static org.junit.jupiter.api.Assertions.*;

class MemberTests {

    Member fred;
    Member tom;
    Member mary;

    @BeforeEach
    void setUp() {
        fred = new Member("fred@lwtech.edu", "12345678");
        tom = new Member("tom@lwtech.edu", "12345678");
        mary = new Member(2001, "mary@lwtech.edu", "12345678");
    }

    @Test
    void testGetRecID() {
        assertEquals(-1, fred.getUserId());
        assertEquals(-1, tom.getUserId());
        assertEquals(2001, mary.getUserId());
    }

    @Test
    void testSetRecID() {
        fred.setUserId(123);
        assertEquals(123, fred.getUserId());
        Exception ex;
        ex = assertThrows(IllegalArgumentException.class,
            () -> { fred.setUserId(456); }
        );
        assertTrue(ex.getMessage().contains("already"));
        ex = assertThrows(IllegalArgumentException.class,
            () -> { fred.setUserId(-789); }
        );
        assertTrue(ex.getMessage().contains("positive"));
    }

    @Test
    void testGetUsername() {
        assertTrue(fred.getUsername().contains("fred"));
        assertTrue(tom.getUsername().contains("tom"));
        assertTrue(mary.getUsername().contains("mary"));
    }

    @Test
    void testGetPassword() {
        assertTrue(fred.getPassword().startsWith("123"));
        assertTrue(tom.getPassword().startsWith("123"));
        assertTrue(mary.getPassword().startsWith("123"));
    }

    @Test
    void testToString() {
        assertTrue(fred.toString().startsWith("["));
        assertTrue(tom.toString().contains("***"));
        assertTrue(mary.toString().endsWith("]"));
    }

    @Test
    void testMemberConstructor() {
        Exception ex = assertThrows(
            IllegalArgumentException.class,
            () -> { new Member(123, null, "password"); }
        );
        assertTrue(ex.getMessage().contains("null"));

        ex = assertThrows(
            IllegalArgumentException.class,
            () -> { new Member(123, "", "password"); }
        );
        assertTrue(ex.getMessage().contains("empty"));

        ex = assertThrows(
            IllegalArgumentException.class,
            () -> { new Member(123, "fred", null); }
        );
        assertTrue(ex.getMessage().contains("null"));

        ex = assertThrows(
            IllegalArgumentException.class,
            () -> { new Member(123, "fred", ""); }
        );
        assertTrue(ex.getMessage().contains("empty"));

        ex = assertThrows(
            IllegalArgumentException.class,
            () -> { new Member(-666, "fred", ""); }
        );
        assertTrue(ex.getMessage().contains("positive"));

    }

}
