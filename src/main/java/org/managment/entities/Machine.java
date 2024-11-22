package org.managment.entities;

public class Machine {
    private String model;
    private String serialNumber;
    private boolean available;

    public Machine (String model, String serialNumber, boolean available) {
        this.available = available;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    public  Machine () {}

    public String getModel() {
        return  model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber () {
        return serialNumber;
    }

    public void  setSerialNumber (String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean getAvailable () {
        return available;
    }

    public void setAvailable (boolean available) {
        this.available = available;
    }
}
