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
        this.records = new ArrayList<>();
    }

    public void readFromFile() {
        File file = new File(filename);
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                EmployeeUser employee = createRecordFrom(line);
                if (employee != null) {//there might be an empty line in the file by mistake
                    records.add(employee);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + filename);
        }//check if file exists or not
    }

    public EmployeeUser createRecordFrom(String line) {
        String[] word = line.split(",");
        EmployeeUser employee = new EmployeeUser(word[0], word[1], word[2], word[3], word[4]);
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
        records.remove(getRecord(key));
    }

    public void saveToFile() {
        try(FileWriter writer = new FileWriter(filename)){
        for (EmployeeUser employee : records) {
            writer.write(employee.lineRepresentation()+"\n");
        }
        }catch(Exception e){
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }
}
