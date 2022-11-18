package com.revature.ReimbursementSystem.DAO;

import com.revature.ReimbursementSystem.Model.ReimbursementTicket;
import com.revature.ReimbursementSystem.Util.ConnectionFactory;
import com.revature.ReimbursementSystem.Util.Interface.Crudable;

import java.sql.*;
import java.util.List;

public class ReimbursementTicketDAO implements Crudable<ReimbursementTicket, Integer> {
    @Override
    public ReimbursementTicket insert(ReimbursementTicket newTicket) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            String sql = "insert into reimbursement_ticket_table(\"id\", \"username\", \"amount\", \"description\",\"reimbursement_type\") values (?, ?, ?, ?, ?::reimbursement_type)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newTicket.getId());
            preparedStatement.setString(2, newTicket.getUser().getUsername());
            preparedStatement.setDouble(3, newTicket.getAmount());
            preparedStatement.setString(4, newTicket.getDescription());
            preparedStatement.setString(5, newTicket.getType().toString());

            if (preparedStatement.executeUpdate() == 0) throw new SQLException("Ticket was not created.");

            return newTicket;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ReimbursementTicket> selectAll() {
        return null;
    }

    @Override
    public ReimbursementTicket selectByField(String field, Integer value) {
        return null;
    }

    @Override
    public boolean update(ReimbursementTicket updatedObject) {
        return false;
    }

    @Override
    public boolean deleteByField(String field, Integer value) {
        return false;
    }

    public int getRowCount() {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            String sql = "select max(id) from reimbursement_ticket_table";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) throw new SQLException("Unable to get row count/no rows exist.");

            return resultSet.getInt("max");

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
