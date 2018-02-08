package io.muic.cs.ooc.webapp.login.database;

import io.muic.cs.ooc.webapp.login.model.User;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class MySQLTest {

    MySQL mySQL = new MySQL();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Before
    public void testCreateUser(){
        mySQL.emptyDb();
        boolean hackinteach = mySQL.createUser("hackinteach","koo");
        boolean admin = mySQL.createUser("admin","1234");
        assertTrue("create user failed: hackinteach",hackinteach);
        assertTrue("create user failed: admin",admin);
    }

    @Test
    public void testAuthenticate(){
        boolean trueHackinteach = mySQL.authenticate("hackinteach","koo");
        boolean wrongHackinteach1 = mySQL.authenticate("hackinteach","1234");
        boolean wrongHackinteach2 = mySQL.authenticate("koo","hackinteach");

        assertTrue(trueHackinteach);
        assertFalse(wrongHackinteach1);
        assertFalse(wrongHackinteach2);

        boolean trueAdmin = mySQL.authenticate("admin","1234");
        boolean wrongAdmin1 = mySQL.authenticate("admin","koo");
        boolean wrongAdmin2 = mySQL.authenticate("1234","admin");

        assertTrue(trueAdmin);
        assertFalse(wrongAdmin1);
        assertFalse(wrongAdmin2);
    }

    @Test
    public void testGetUsers(){
        List<User> users = mySQL.getUsers();
        assertTrue(users.size() == 2);
    }

    @Test
    public void testGetUserByUsername(){
        User hackinteach = mySQL.getUserbyUsername("hackinteach");
        assertEquals("hackinteach",hackinteach.getUsername());
        assertEquals("koo",hackinteach.getPassword());

        User admin = mySQL.getUserbyUsername("admin");
        assertEquals("admin",admin.getUsername());
        assertEquals("1234",admin.getPassword());
    }
}