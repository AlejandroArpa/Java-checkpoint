package org.managment.controllers;


import org.managment.entities.Machine;
import org.managment.persistence.DAO.MachinesDAO;

import java.util.Scanner;

public class MachineController {
    Scanner scanner;

    public  MachineController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addMachine () {
        System.out.println("Insert machine's model");
        String model = scanner.nextLine();
        System.out.println("Insert machine's serial number");
        String serialNumber = scanner.nextLine();
        System.out.println("Insert machine's available");
        boolean available = scanner.nextBoolean();
        int res = MachinesDAO.getBySerialNum(serialNumber);
        if(res == 0) {
            Machine newMachine = new Machine(model, serialNumber, available);
            MachinesDAO.addMachine(newMachine);
        }
        else {
            System.out.println("Serial already exists");
        }
    }
    public void getMachines () {
        int page = 0;
        int numRegisters = MachinesDAO.getNumRegisters();
        int totalPages = numRegisters/5;
        while (true){
            Machine[] machines = MachinesDAO.getMachines(page);
            for (Machine machine : machines) {
                System.out.println("Model: " + machine.getModel());
                System.out.println("Serial number: " + machine.getSerialNumber());
                System.out.println("Available: " + machine.getAvailable());
                System.out.println("-------------------------");
            }
            System.out.println("           ");
            System.out.println("1. Pag anterior     2. Siguiente pag    0. Volver");
            int choise = scanner.nextInt();
            scanner.nextLine();

            switch (choise){
                case 1 -> {
                    if (page > 0){
                        page -= 1;
                    }
                }
                case  2 -> {
                    if(page < totalPages){
                        page += 1;
                    }
                }

                case  0 -> {
                    return;
                }
            }
        }

    }
}
