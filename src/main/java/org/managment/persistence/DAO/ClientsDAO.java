package org.managment.persistence.DAO;

import org.managment.entities.Client;
import org.managment.persistence.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsDAO {
    public static void addClient (Client objClient) {
        String sql = "INSERT INTO clients (name, email, phone, address) VALUES(?, ?, ?, ?);";
        try(
                Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ){
            statement.setString(1, objClient.getName());
            statement.setString(2, objClient.getEmail());
            statement.setInt(3, objClient.getPhone());
            statement.setString(4, objClient.getAddress());

            statement.executeUpdate();
            System.out.println("Client created");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static  Client[] getClients () {
        String sql = "SELECT * FROM clients;";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ) {
            ResultSet resultSet = statement.executeQuery();
            List<Client> clientList = new ArrayList<>();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setPhone(resultSet.getInt("phone"));
                client.setAddress(resultSet.getString("address"));
                clientList.add(client);
            }

            return clientList.toArray(new Client[0]);

        } catch (Exception e){
            e.printStackTrace();
            return new Client[0];
        }
    }

    public static int getById (int id) {
        String sql = "SELECT *FROM machines WHERE id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
