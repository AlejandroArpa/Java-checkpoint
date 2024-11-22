package org.managment.entities;

import java.sql.Date;

public class Rent {
    private Date startDate;
    private Date endDate;
    private int clientId;
    private int machineId;

    public Rent (){}

    public Rent (int clientId, int machineId, Date startDate, Date endDate){
        this.machineId = machineId;
        this.clientId = clientId;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public void setStartDate (Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate (){
        return startDate;
    }

    public void setEndDate (Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate () {
        return endDate;
    }

    public void setClientId (int clientId) {
        this.clientId = clientId;
    }

    public int getClientId () {
        return clientId;
    }

    public void setMachineId (int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "clientId=" + clientId +
                ", machineId=" + machineId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
