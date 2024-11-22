package org.managment.persistence.DAO;

import org.managment.entities.Client;
import org.managment.entities.Machine;
import org.managment.persistence.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachinesDAO {
    public static void addMachine (Machine objMachine) {
        String sql = "INSERT INTO machines (model, serial_number, status) VALUES(?, ?, ?);";
        try(
                Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ){
            statement.setString(1, objMachine.getModel());
            statement.setString(2, objMachine.getSerialNumber());
            statement.setBoolean(3, objMachine.getAvailable());

            statement.executeUpdate();
            System.out.println("Machine created");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Machine[] getMachines (int page) {
        String sql = "SELECT * FROM machines LIMIT 5 OFFSET ?;";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            int offset = page * 5;
            statement.setInt(1, offset);
            ResultSet resultSet = statement.executeQuery();
            List<Machine> machineList = new ArrayList<>();
            while (resultSet.next()) {
                Machine machine = new Machine();
                machine.setAvailable(resultSet.getBoolean("status"));
                machine.setSerialNumber(resultSet.getString("serial_number"));
                machine.setModel(resultSet.getString("model"));
                machineList.add(machine);
            }

            return machineList.toArray(new Machine[0]);

        } catch (Exception e){
            e.printStackTrace();
            return new Machine[0];
        }
    }

    public static int getNumRegisters () {
        String sql = "SELECT COUNT(*) FROM machines";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()){
                return resultSet.getInt(1);
            }
            else {
                return 0;
            }

        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static int getBySerialNum (String serialNum ) {
        String sql = "SELECT *FROM machines WHERE serial_number = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ) {
            statement.setString(1, serialNum);
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

    public static void updateStatus(int id, boolean newStatus) {
        String sql = "UPDATE machines SET status = ? WHERE id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setBoolean(1, newStatus);
            statement.setInt(2, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Machine status updated successfully.");
            } else {
                System.out.println("Machine not found or status was already set.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean availableMachineById (int id) {
        String sql = "SELECT *FROM machines WHERE id = ? AND status = true";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
