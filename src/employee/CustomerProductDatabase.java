/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nour
 */
public class CustomerProductDatabase {
    private ArrayList<CustomerProduct> records = new ArrayList<>();
    private String fileName;

    public CustomerProductDatabase(String fileName) {
        this.fileName = fileName;
    }

    public void readFromFile() {
        String line;// to read a line as a string from the file.
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) // While the file has a next line (will read until no new line is found).
            {
                line = scan.nextLine();
                if (null == createRecordFrom(line)) {
                    break;
                } else {
                    records.add(createRecordFrom(line));
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public CustomerProduct createRecordFrom(String line) {
        String[] splitted;
        splitted = line.split(",");
        if (splitted.length != 4) {
            System.out.println("The file is not written correctly.");// Not all fields are written.
            return null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");// The default LocalDate is
                                                                                    // YYYY/MM/DD, so we need to tell it
                                                                                    // to read the date in the form of
                                                                                    // dd-MM-yyyy
            // also if you replace dd with DD, it will be wrong, beacuse DD would be the day
            // of the year like 1-365. And YY means week based year.
            CustomerProduct customerProduct = new CustomerProduct(splitted[0], splitted[1],
                    LocalDate.parse(splitted[2], formatter));
            customerProduct.setPaid(Boolean.parseBoolean(splitted[3]));
            return customerProduct;
        }
    }

    public ArrayList<CustomerProduct> returnAllRecords() {
        if (records != null)
            return records;
        else
            return null;
    }

    public boolean contains(String key) {
        return getRecord(key) != null;
    }

    public CustomerProduct getRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            String line = records.get(i).getSearchKey();// we convert the object in arraylist to string and then compare
                                                        // it with the key.
            if (line.equals(key)) {
                System.out.println("Found at index: " + i);
                return records.get(i);
            }
        }
        System.out.println("Not Found.");
        return null;
    }

    public void insertRecord(CustomerProduct record) {
        if (!contains(record.getSearchKey()))
            records.add(record);
        else
            System.out.println("The record is already there.");
    }

    public void deleteRecord(String key) {
        CustomerProduct c = getRecord(key);
        if (c != null)
            records.remove(c);

    }

    public void saveToFile() throws IOException {

        FileWriter writer = new FileWriter(fileName);
        for (int i = 0; i < records.size(); i++) {
            writer.write(records.get(i).toString());
            if (i != records.size() - 1)
                writer.write("\n");
        }

        writer.close();

    }

}
