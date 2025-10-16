package admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeUserDatabase {
    private ArrayList<EmployeeUser> records;
    private final String filename;

    public EmployeeUserDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();//if we removed this it will give Exception in thread "main" java.lang.NullPointerException
                                         // because you are trying to use a list that was never created
    }

    public void readFromFile() {
        File file = new File(filename);
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                EmployeeUser employee = createRecordFrom(line);
                if (employee != null) { // there might be an empty line in the file by mistake
                    records.add(employee);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + filename);
        } // check if file exists or not
        // Scanner is closed automaticaly by the try resources
        
    }

    public EmployeeUser createRecordFrom(String line) {
        if(line==null)
            return null;
        String[] field = line.split(",");
        EmployeeUser employee = new EmployeeUser(field[0], field[1], field[2], field[3], field[4]);
        return employee;
    }

    public ArrayList<EmployeeUser> returnAllRecords() {
        return this.records;
    }

    public boolean contains(String key) {
        for (EmployeeUser employee : records) {
            if (employee.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public EmployeeUser getRecord(String key) {
        for (EmployeeUser employee : records) {
            if (employee.getSearchKey().equals(key)) {
                return employee;
            }
        }
        System.out.println("Employee User not found");
        return null;
    }

    public void insertRecord(EmployeeUser record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        EmployeeUser removeUser= getRecord(key);
        if(removeUser!=null)//check if the employee with that Id exists or not
            records.remove(removeUser);
        else
            System.out.println("Unable to remove: Record not found");
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter(filename)) {
            for (EmployeeUser employee : records) {
                writer.write(employee.lineRepresentation() + "\n");
            }//writer.close is handled by the try-with-resources
        } catch (Exception e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }
}
