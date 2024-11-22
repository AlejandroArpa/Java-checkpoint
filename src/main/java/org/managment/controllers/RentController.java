package org.managment.controllers;

import org.managment.entities.Machine;
import org.managment.entities.Rent;
import org.managment.persistence.DAO.MachinesDAO;
import org.managment.persistence.DAO.RentDAO;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class RentController {
    Scanner scanner;

    public RentController(Scanner scanner){
        this.scanner = scanner;
    }

    public void addRent () {
        System.out.println("Insert client id");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insert machine id");
        int machineId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insert start date (yyyy-MM-dd):");
        String startDateStr = scanner.nextLine().trim();
        System.out.println("Insert end date (yyyy-MM-dd):");
        String endDateStr = scanner.nextLine().trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date startUtilDate = dateFormat.parse(startDateStr);
            java.util.Date endUtilDate = dateFormat.parse(endDateStr);

            Date startDate = new Date(startUtilDate.getTime());
            Date endDate = new Date(endUtilDate.getTime());

            Rent objRent = new Rent(clientId, machineId, startDate, endDate);
            RentDAO.addRent(objRent);
            MachinesDAO.updateStatus(machineId, false);
        } catch (Exception e) {
            System.out.println("Invalid date format, please use yyyy-MM-dd.");
        }

    }

    public void softDelete () {
        System.out.println("Insert rent id to delete");
        int rentId = scanner.nextInt();
        scanner.nextLine();
        RentDAO.softDelete(rentId);
    }

    public void allRents () {
        List<Rent> rents = RentDAO.getAllRents(false);
        if (rents.isEmpty()) {
            System.out.println("No rents found.");
        } else {
            rents.forEach(System.out::println);
        }
    }

    public void allRentsStatusTrue () {
        List<Rent> activeRents = RentDAO.getAllRents(true);
        if (activeRents.isEmpty()) {
            System.out.println("No active rents found.");
        } else {
            activeRents.forEach(System.out::println);
        }
    }
}
