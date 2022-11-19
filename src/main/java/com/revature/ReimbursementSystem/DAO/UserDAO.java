package com.revature.ReimbursementSystem.DAO;

import com.revature.ReimbursementSystem.Model.Position;
import com.revature.ReimbursementSystem.Model.User;
import com.revature.ReimbursementSystem.Util.ConnectionFactory;
import com.revature.ReimbursementSystem.Util.Exception.InvalidUserInputException;
import com.revature.ReimbursementSystem.Util.Interface.Crudable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements Crudable<User, String> {

    @Override
    public User insert(User newUser) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            String sql = "insert into user_table(\"username\", \"password\") values (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newUser.getUsername());
            preparedStatement.setString(2, newUser.getPassword());

            if (preparedStatement.executeUpdate() == 0) throw new SQLException("User was not created.");

            return newUser;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public User selectByField(String field, String value) {
        return null;
    }

    @Override
    public boolean update(User updatedObject) {
        return false;
    }

    @Override
    public boolean deleteByField(String field, String value) {
        return false;
    }

    public User loginCheck(String username, String password) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from user_table where \"username\" = ? and \"password\" = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) throw new InvalidUserInputException("Incorrect username or password.");

            User user = new User();

            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setPosition(Position.valueOf(resultSet.getString("position")));

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
