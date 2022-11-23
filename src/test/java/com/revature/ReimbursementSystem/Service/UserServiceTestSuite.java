package com.revature.ReimbursementSystem.Service;

import com.revature.ReimbursementSystem.DAO.UserDAO;
import com.revature.ReimbursementSystem.Model.User;
import com.revature.ReimbursementSystem.Util.DTO.LoginCredentials;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class UserServiceTestSuite {

    UserService sut;
    UserDAO mockUserDAO;

    @Before
    public void setUp() {
        mockUserDAO = mock(UserDAO.class);
        sut = new UserService(mockUserDAO);
    }

    @Test
    public void test_registerUser_returnUser_givenValidUser() {
        // Arrange
        User newUser = new User("test", "123");

        // Act
        when(mockUserDAO.insert(newUser)).thenReturn(newUser);
        User actual = sut.registerUser(newUser);

        // Assert
        verify(mockUserDAO, times(1)).insert(newUser);
        Assert.assertNotNull(actual);
    }

    @Test
    public void test_registerUser_returnNull_givenInvalidUser() {
        // Arrange
        User newUser = new User();

        // Act
        when(mockUserDAO.insert(newUser)).thenReturn(null);
        User actual = sut.registerUser(newUser);

        // Assert
        verify(mockUserDAO, times(1)).insert(newUser);
        Assert.assertNull(actual);
    }

    @Test
    public void test_registerUser_returnNull_givenUsernameIsTaken() {
        // Arrange
        User newUser = new User("test", "123");
        User oldUser = new User("test", "456");

        // Act
        when(mockUserDAO.insert(oldUser)).thenReturn(oldUser);
        when(mockUserDAO.insert(newUser)).thenReturn(null);
        sut.registerUser(oldUser);
        User actual = sut.registerUser(newUser);

        // Assert
        verify(mockUserDAO, times(1)).insert(oldUser);
        verify(mockUserDAO, times(1)).insert(newUser);
        Assert.assertNull(actual);
    }

    @Test
    public void test_registerUser_returnUser_givenUsernameIsNotTaken() {
        // Arrange
        User newUser = new User("test", "123");
        User oldUser = new User("notTest", "456");

        // Act
        when(mockUserDAO.insert(oldUser)).thenReturn(oldUser);
        when(mockUserDAO.insert(newUser)).thenReturn(newUser);
        sut.registerUser(oldUser);
        User actual = sut.registerUser(newUser);

        // Assert
        verify(mockUserDAO, times(1)).insert(oldUser);
        verify(mockUserDAO, times(1)).insert(newUser);
        Assert.assertNotNull(actual);
    }

    @Test
    public void test_loginUser_returnUser_givenCredentialAreCorrect() {
        // Arrange
        User newUser = new User("test", "123");
        LoginCredentials userCred = new LoginCredentials();
        userCred.setUsername("test");
        userCred.setPassword("123");

        // Act
        when(mockUserDAO.loginCheck(userCred.getUsername(), userCred.getPassword())).thenReturn(newUser);
        User actual = sut.loginUser(userCred);

        //Assert
        verify(mockUserDAO, times(1)).loginCheck(userCred.getUsername(), userCred.getPassword());
        Assert.assertNotNull(actual);
    }

    @Test
    public void test_loginUser_returnFalse_givenCredentialAreIncorrect() {
        // Arrange
        User newUser = new User("test", "123");
        LoginCredentials userCred = new LoginCredentials();
        userCred.setUsername("test");
        userCred.setPassword("124");

        // Act
        when(mockUserDAO.loginCheck(userCred.getUsername(), userCred.getPassword())).thenReturn(null);
        User actual = sut.loginUser(userCred);

        //Assert
        verify(mockUserDAO, times(1)).loginCheck(userCred.getUsername(), userCred.getPassword());
        Assert.assertNull(actual);
    }
}
