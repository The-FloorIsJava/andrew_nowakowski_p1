package com.revature.ReimbursementSystem.DAO;

import com.revature.ReimbursementSystem.Model.*;
import com.revature.ReimbursementSystem.Util.ConnectionFactory;
import com.revature.ReimbursementSystem.Util.DTO.ReimbursementTicketAction;
import com.revature.ReimbursementSystem.Util.Interface.Crudable;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ReimbursementTicketDAO implements Crudable<ReimbursementTicket, Integer> {
    @Override
    public ReimbursementTicket insert(ReimbursementTicket newTicket) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            String sql = """
            insert into reimbursement_ticket_table("id", "username", "amount",
             "description","reimbursement_type")
             values (?, ?, ?, ?, ?::reimbursement_type)
             """;

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

    public List<ReimbursementTicket> getPendingTickets() {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<ReimbursementTicket> pendingTickets = new LinkedList<>();

            String sql = """
                    select * from reimbursement_ticket_table join user_table
                     on user_table.username = reimbursement_ticket_table.username
                     where reimbursement_ticket_table.status = 'Pending'
                     order by reimbursement_ticket_table.id
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                pendingTickets.add(convertSqlInfoToReimbursementTicket(resultSet, null));
            }

            return pendingTickets;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private ReimbursementTicket convertSqlInfoToReimbursementTicket(ResultSet resultSet, User user) throws SQLException {
        ReimbursementTicket ticket = new ReimbursementTicket();

        if (user == null) {
            user = new User();

            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setPosition(Position.valueOf(resultSet.getString("position")));
        }


        ticket.setId(resultSet.getInt("id"));
        ticket.setUser(user);
        ticket.setAmount(resultSet.getDouble("amount"));
        ticket.setDescription(resultSet.getString("description"));
        ticket.setStatus(TicketStatus.valueOf(resultSet.getString("status")));
        ticket.setType(ReimbursementType.valueOf(resultSet.getString("reimbursement_type")));

        return ticket;
    }

    public ReimbursementTicketAction updateTicket(ReimbursementTicketAction action) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            String sql = """
                    update reimbursement_ticket_table set
                     status = ?::status where id = ? and status = 'Pending'::status
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, action.getAction().toString());
            preparedStatement.setInt(2, action.getTicketId());

            if (preparedStatement.executeUpdate() == 0) throw new SQLException("Ticket was not updated.");

            return action;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ReimbursementTicket> getPreviousTicketsForUser(User user) {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<ReimbursementTicket> userTickets = new LinkedList<>();

            String sql = """
                    select * from reimbursement_ticket_table
                     where username = ?
                     order by reimbursement_ticket_table.id
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                userTickets.add(convertSqlInfoToReimbursementTicket(resultSet, user));
            }

            return userTickets;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
