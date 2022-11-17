package com.revature.ReimbursementSystem.Model;

import org.junit.Assert;
import org.junit.Test;

public class UserTestSuite {

    @Test
    public void test_isValidUser_returnTrue_givenValidUser() {
        User user = new User("test", "123");

        boolean actual = user.isValidUser();

        Assert.assertTrue(actual);
    }

    @Test
    public void test_isValidUser_returnFalse_givenInvalidUser() {
        User user = new User();

        boolean actual = user.isValidUser();

        Assert.assertFalse(actual);
    }

    @Test
    public void test_isValidPassword_returnTrue_givenMatchingPasswords() {
        User user = new User("test", "password");

        boolean actual = user.isValidPassword("password");

        Assert.assertTrue(actual);
    }

    @Test
    public void test_isValidPassword_returnFalse_givenNonMatchingPasswords() {
        User user = new User("test", "password");

        boolean actual = user.isValidPassword("notAPassword");

        Assert.assertFalse(actual);
    }

    @Test
    public void test_isValidPassword_returnTrue_givenUserWithMatchingPassword() {
        User user = new User("test", "password");

        User newUser = new User("test", "password");
        boolean actual = user.isValidPassword(newUser);

        Assert.assertTrue(actual);
    }

    @Test
    public void test_isValidPassword_returnFalse_givenUserWithNonMatchingPassword() {
        User user = new User("test", "password");

        User newUser = new User("notTest", "notAPassword");
        boolean actual = user.isValidPassword(newUser);

        Assert.assertFalse(actual);
    }
}
