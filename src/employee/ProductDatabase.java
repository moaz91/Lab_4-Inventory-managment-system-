/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employee;

import java.util.ArrayList;

import database_model.Database;

/**
 *
 * @author AltAWKEl
 */
public class ProductDatabase extends Database<Product> {
    // ------------------------variables-------------------------------------------

    private String filename;

    // ------------------------constructor------------------------------------------
    public ProductDatabase(String fileName) {
        super(fileName);
    }

    // ------------------------getters-setters------------------------------------------
    public void setRecords(ArrayList<Product> records) {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    // ------------------------methods------------------------------------------

    // ------------------------createRecordFrom------------------------------------------
    public Product createRecordFrom(String line) {
        Product p;
        String[] parts = line.split(",");
        if (parts.length != 6) {
            System.out.print("wrong");
            p = null;
        } else
            p = new Product(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(),
                    Integer.parseInt(parts[4].trim()), Float.parseFloat(parts[5].trim()));

        return p;
    }

}
