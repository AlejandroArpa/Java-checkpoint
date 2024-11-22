package org.managment.utils;

import org.managment.entities.Machine;
import org.managment.persistence.DAO.MachinesDAO;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;


public class ODSReader {

    public static String odsFilePath = "src/main/resources/seeder.ods";

    public static void executeSeeder () {
        try {
            OdfSpreadsheetDocument document = OdfSpreadsheetDocument.loadDocument(odsFilePath);


            OdfTable table = document.getTableList().get(0);
            for (int i = 1; i < table.getRowCount(); i++) {
                // Obtener las celdas de la fila
                String model = table.getCellByPosition(0, i).getStringValue();
                String serialNumber = table.getCellByPosition(1, i).getStringValue();
                String availableString = table.getCellByPosition(2, i).getStringValue();

                Boolean available = Boolean.parseBoolean(availableString);
                int res = MachinesDAO.getBySerialNum(serialNumber);
                if(res == 0) {
                    Machine newMachine = new Machine(model, serialNumber, available);
                    MachinesDAO.addMachine(newMachine);
                }
                else {
                    System.out.println("Serial already exists");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
