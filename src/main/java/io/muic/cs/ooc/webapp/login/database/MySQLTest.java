package io.muic.cs.ooc.webapp.login.database;

import io.muic.cs.ooc.webapp.login.model.User;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class MySQLTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Before
    public void testCreateUser(){
        MySQL.emptyDb();
        boolean hackinteach = MySQL.createUser("hackinteach","koo","Nuttapat","Koonarangsri" ,"nuttapat.koo@student.mahidol.edu");
        boolean admin = MySQL.createUser("admin","1234","Admin","Test","ooc@ooc.com");
        assertTrue("create user failed: hackinteach",hackinteach);
        assertTrue("create user failed: admin",admin);
    }

    @Test
    public void testAuthenticate(){
        boolean trueHackinteach = MySQL.authenticate("hackinteach","koo");
        boolean wrongHackinteach1 = MySQL.authenticate("hackinteach","1234");
        boolean wrongHackinteach2 = MySQL.authenticate("koo","hackinteach");

        assertTrue(trueHackinteach);
        assertFalse(wrongHackinteach1);
        assertFalse(wrongHackinteach2);

        boolean trueAdmin = MySQL.authenticate("admin","1234");
        boolean wrongAdmin1 = MySQL.authenticate("admin","koo");
        boolean wrongAdmin2 = MySQL.authenticate("1234","admin");

        assertTrue(trueAdmin);
        assertFalse(wrongAdmin1);
        assertFalse(wrongAdmin2);
    }

    @Test
    public void testGetUsers(){
        List<User> users = MySQL.getUsers();
        assertTrue(users.size() == 2);
    }

    @Test
    public void testEmailExists(){
        assertTrue(MySQL.isEmailExists("nuttapat.koo@student.mahidol.edu"));
        assertFalse(MySQL.isEmailExists("webmaster@hackinteach.com"));
    }

    @Test
    public void testDeleteUser(){
//        assertFalse(MySQL.removeUserbyUsername("nuttapat"));
//        assertTrue(MySQL.removeUserbyUsername("hackinteach"));
    }

    @Test
    public void testGetUserByUsername(){
        User hackinteach = MySQL.getUserbyUsername("hackinteach");
        assertEquals("hackinteach",hackinteach.getUsername());
        assertEquals("koo",hackinteach.getPassword());

        User admin = MySQL.getUserbyUsername("admin");
        assertEquals("admin",admin.getUsername());
        assertEquals("1234",admin.getPassword());
    }

    @Test
    public void testUserExists(){
        boolean hackinteachExists = MySQL.isUserExists("hackinteach");
        assertTrue(hackinteachExists);

        boolean adminExists = MySQL.isUserExists("admin");
        assertTrue(adminExists);

        boolean falseOne = MySQL.isUserExists("koo");
        boolean falseTwo = MySQL.isUserExists("user");
        assertFalse(falseOne);
        assertFalse(falseTwo);
    }
}