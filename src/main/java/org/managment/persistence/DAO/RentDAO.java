package org.managment.persistence.DAO;

import org.managment.entities.Rent;
import org.managment.persistence.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentDAO {
    public static void addRent (Rent rent) {
        int machineExists = MachinesDAO.getById(rent.getMachineId());
        int clientExists = ClientsDAO.getById(rent.getClientId());
        boolean machineAvailable = MachinesDAO.availableMachineById(rent.getMachineId());
        if (machineExists == 0 || clientExists == 0) {
            System.out.println("Cliento or machine doesn't exists.");
        }
        else if(!machineAvailable){
            System.out.println("Maquina no disponible");
        }
        else {
            String sql = "INSERT INTO rents (client_id, machine_id, start_date, end_date, status) VALUES(?, ?, ?, ?, ?);";
            try(
                    Connection conn = Database.getConnection();
                    PreparedStatement statement = conn.prepareStatement(sql);
            ){
                statement.setInt(1, rent.getClientId());
                statement.setInt(2, rent.getMachineId());
                statement.setDate(3, rent.getStartDate());
                statement.setDate(4, rent.getEndDate());
                statement.setBoolean(5, true);

                statement.executeUpdate();
                System.out.println("Rent created");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static boolean getStatusById(int id) {
        String sql = "SELECT status FROM machines WHERE id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("status");
            } else {
                System.out.println("Machine not found.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void softDelete(int id) {
        String updateRentStatus = "UPDATE rents SET status = false WHERE id = ?;";
        String updateMachineStatus = "UPDATE machines SET status = true WHERE id = (SELECT machine_id FROM rents WHERE id = ?);";

        try (
                Connection conn = Database.getConnection();
                PreparedStatement updateRentStatement = conn.prepareStatement(updateRentStatus);
                PreparedStatement updateMachineStatement = conn.prepareStatement(updateMachineStatus);
        ) {
            updateRentStatement.setInt(1, id);
            int rentUpdated = updateRentStatement.executeUpdate();

            if (rentUpdated > 0) {
                updateMachineStatement.setInt(1, id);
                updateMachineStatement.executeUpdate();
                System.out.println("Rent marked as deactivated and machine set to available.");
            } else {
                System.out.println("No rent found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Rent> getAllRents(boolean onlyActive) {
        String sql = "SELECT * FROM rents";
        if (onlyActive) {
            sql += " WHERE status = true";
        }

        List<Rent> rents = new ArrayList<>();

        try (
                Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                Rent rent = new Rent(
                        resultSet.getInt("client_id"),
                        resultSet.getInt("machine_id"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date")
                );
                rents.add(rent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rents;
    }
}
