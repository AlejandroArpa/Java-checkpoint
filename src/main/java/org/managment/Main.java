package org.managment;

import org.managment.controllers.ClientController;
import org.managment.controllers.MachineController;
import org.managment.controllers.RentController;
import org.managment.utils.ODSReader;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ClientController clientController = new ClientController(scanner);
        MachineController machineController = new MachineController(scanner);
        RentController rentController = new RentController(scanner);
        System.out.println("Welcome to rent managment");


        while (true) {
            System.out.println("1. Clients register");
            System.out.println("2. Show clients");
            System.out.println("3. Create machine");
            System.out.println("4. Get machines");
            System.out.println("5. Execute seeder");
            System.out.println("6. Create a rent");
            System.out.println("7. Delete a rent");
            System.out.println("8. Show all rents");
            System.out.println("9. Show only rents with status true");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch ( choice ) {
                case 1 -> {
                    clientController.addClient();
                }
                case 2 -> {
                    clientController.getClients();
                }
                case 3 -> {
                    machineController.addMachine();
                }
                case 4 -> {
                    machineController.getMachines();
                }
                case 5 -> {
                    ODSReader.executeSeeder();
                }
                case 6 -> {
                    rentController.addRent();
                }
                case 7 -> {
                    rentController.softDelete();
                }
                case 8 -> {
                    rentController.allRents();
                }
                case 9 -> {
                    rentController.allRentsStatusTrue();
                }
                case 0 -> {
                    System.out.println("Bye.");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}