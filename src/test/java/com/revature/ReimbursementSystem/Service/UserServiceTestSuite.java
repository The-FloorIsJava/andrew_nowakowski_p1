package com.revature.ReimbursementSystem.Service;

import com.revature.ReimbursementSystem.Model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTestSuite {

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void test_registerUser_returnTrue_givenNoUsersExist() {
        User newUser = new User("test", "123");

        boolean actual = userService.registerUser(newUser);
        User[] expected = {newUser};

        Assert.assertTrue(actual);
        Assert.assertArrayEquals(expected, userService.getAllUsers().toArray());
    }

    @Test
    public void test_registerUser_returnFalse_givenUserIsInvalid() {
        User newUser = new User();

        boolean actual = userService.registerUser(newUser);
        User[] expected = {};

        Assert.assertFalse(actual);
        Assert.assertArrayEquals(expected, userService.getAllUsers().toArray());
    }

    @Test
    public void test_registerUser_returnFalse_givenUsernameIsTaken() {
        User newUser = new User("test", "123");
        User oldUser = new User("test", "456");

        userService.addUser(oldUser);
        boolean actual = userService.registerUser(newUser);
        User[] expected = {oldUser};

        Assert.assertFalse(actual);
        Assert.assertArrayEquals(expected, userService.getAllUsers().toArray());
    }

    @Test
    public void test_registerUser_returnTrue_givenUsernameIsNotTaken() {
        User newUser = new User("test", "123");
        User oldUser = new User("notTest", "5565");

        userService.registerUser(oldUser);
        boolean actual = userService.registerUser(newUser);
        User[] expected = {oldUser, newUser};

        Assert.assertTrue(actual);
        Assert.assertArrayEquals(expected, userService.getAllUsers().toArray());
    }

    @Test
    public void test_loginUser_returnTrue_givenCredentialAreCorrect() {
        User newUser = new User("test", "123");

        userService.registerUser(newUser);
        boolean actual = userService.loginUser(newUser);

        Assert.assertTrue(actual);
    }

    @Test
    public void test_loginUser_returnFalse_givenCredentialAreIncorrect() {
        User user = new User("test", "123");

        userService.registerUser(user);
        boolean actual = userService.loginUser(new User("test", "124"));

        Assert.assertFalse(actual);
    }

    @Test
    public void test_loginUser_returnFalse_givenUserIsNotRegistered() {
        User user = new User("test", "123");

        boolean actual = userService.loginUser(user);

        Assert.assertFalse(actual);
    }

}
