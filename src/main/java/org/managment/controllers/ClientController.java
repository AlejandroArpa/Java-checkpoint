package org.managment.controllers;

import org.managment.entities.Client;
import org.managment.persistence.DAO.ClientsDAO;

import java.util.Scanner;

public class ClientController {
    Scanner scanner;

    public ClientController(Scanner scanner) {
        this.scanner = scanner;
    }
    public void addClient () {
        System.out.println("Insert client's name");
        String name = scanner.nextLine();
        System.out.println("Insert client's address");
        String address = scanner.nextLine();
        System.out.println("Insert client's email");
        String email = scanner.nextLine();
        System.out.println("Insert client's phone");
        int phone = scanner.nextInt();

        Client newClient = new Client(0, name, email, phone, address);
        ClientsDAO.addClient(newClient);
    }

    public void getClients () {
        Client[] clients = ClientsDAO.getClients();
        for (Client client : clients) {
            System.out.println("ID: " + client.getId());
            System.out.println("Name: " + client.getName());
            System.out.println("Email: " + client.getEmail());
            System.out.println("Phone: " + client.getPhone());
            System.out.println("Address: " + client.getAddress());
            System.out.println("-------------------------");
        }
    }
}
