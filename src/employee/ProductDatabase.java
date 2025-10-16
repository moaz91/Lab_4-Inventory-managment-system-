/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.mamagement.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author AltAWKEl
 */
public class ProductDatabase extends Product  {
//------------------------variables-------------------------------------------

    private ArrayList<Product> records;
    private String filename;

//------------------------constructor------------------------------------------
    public ProductDatabase(String filename) {
        this.filename = filename;
                this.records = new ArrayList<>();//--------review.............

    }
//------------------------getters-setters------------------------------------------
public void setRecords(ArrayList<Product> records) {
        this.records = records;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
//------------------------methods------------------------------------------

//------------------------readFromFile------------------------------------------
public void readFromFile() {
        records.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Product p = createRecordFrom(line);
                if (p != null) {
                    records.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file ");
        }
    }
//------------------------createRecordFrom------------------------------------------
public Product createRecordFrom(String line) {
        Product p=new Product();//--....................<review>_....................
        String[] parts = line.split(",");
        if(parts.length!=6)
        { System.out.print("wrong");
        return null;}
        else
        p.setproductID(parts[0].trim());
        p.setproductName(parts[1].trim());
        p.setmanufacturerName(parts[2].trim());
        p.setsupplierName(parts[3].trim());
        p.setquantity(Integer.parseInt(parts[4].trim()));
        p.setprice(Float.parseFloat(parts[5].trim()));
 return p;
    }
//------------------------returnAllRecords------------------------------------------
public ArrayList<Product> returnAllRecords() {
      return records;   
    }
//------------------------contains------------------------------------------
public boolean contains(String key) {
        for (int i = 0; i < records.size(); i++) {
        if (records.get(i).getSearchKey().equals(key)) {
            return true;
        }
    }
    return false;
    }
//------------------------getRecord------------------------------------------
public Product getRecord(String key) {
           for (int i = 0; i < records.size(); i++) {
        if (records.get(i).getSearchKey().equals(key)) {
            return records.get(i);
                                  }
                      }
    return null;
    }
//------------------------insertRecord------------------------------------------
public void insertRecord(Product record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        } else {
            System.out.println("Product with ID " + record.getSearchKey() + " already exists.");
        }
    }
//------------------------deleteRecord------------------------------------------
public void deleteRecord(String key) {
        Product target = null;
         for (int i = 0; i < records.size(); i++) {
        if (records.get(i).getSearchKey().equals(key)) {
           target =records.get(i);
           break;
        }
    }
    if(target!=null)records.remove(target);
     else System.out.println("No product found with ID: " + key);  
    }
//------------------------saveToFile------------------------------------------
 public void saveToFile() {
     try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            
             for (int i = 0; i < records.size(); i++) {
        Product p=records.get(i);
         pw.println(p.lineRepresentation());
                            } 
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
     
    
}


