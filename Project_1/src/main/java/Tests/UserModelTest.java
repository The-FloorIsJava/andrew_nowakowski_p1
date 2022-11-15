package Tests;

import models.Position;
import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserModelTest {
    User user;

    @Before
    public void setUp() {
        this.user = new User("John Doe", "password", Position.Employee);
    }

    @Test
    public void testGetUsername() {
        String expected = "John Doe";

        String actual = user.getUsername();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetUsername() {
        String expected = "Adam West";

        user.setUsername(expected);
        String actual = user.getUsername();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetPosition() {
        Position expected = Position.Employee;

        Position actual = user.getPosition();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetPosition() {
        Position expected = Position.Manager;

        user.setPosition(expected);
        Position actual = user.getPosition();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsValidPassword() {
        String correctPassword = "password";
        String incorrectPassword = "denied";

        boolean valid = user.isValidPassword(correctPassword);
        boolean invalid = user.isValidPassword(incorrectPassword);

        Assert.assertTrue(valid);
        Assert.assertFalse(invalid);
    }
    @Test
    public void testIsValid() {
        User badUser = new User();

        boolean validUser = user.isValid();
        boolean invalidUser = badUser.isValid();

        Assert.assertTrue(validUser);
        Assert.assertFalse(invalidUser);
    }
}
