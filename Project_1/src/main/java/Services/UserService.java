package Services;

import models.Position;
import models.User;

import java.util.*;

public class UserService {

    List<User> users;

    public UserService() {
        users = new LinkedList<>();
    }

    public List<User> getAllUsers() {
        users.add(new User("Joe", "123", Position.Employee));
        users.add(new User("Jill", "123", Position.Employee));
        users.add(new User("Jake", "123", Position.Employee));
        return users;
    }


    public boolean addUser(User user) {
        if (!user.isValid()) return false;
        users.add(user);
        return true;
    }
}
