/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nour
 */
public class EmployeeRole {
    private final ProductDatabase productsDatabase; // final because the filenames will not change.
    private final CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");// composition
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");// composition
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName,
            int quantity, float price) throws IOException {
        Product p = new Product(productID, productName, manufacturerName, supplierName, quantity, price);// composition
        
            productsDatabase.insertRecord(p);
            productsDatabase.saveToFile();
      
    }

    public Product[] getListOfProducts() {
        ArrayList<Product> p = new ArrayList<>();
        // composition, can not inherit from more than one class, so we used composition
        // here.
      
            productsDatabase.readFromFile();
        
        p = productsDatabase.returnAllRecords();
        Product[] products = new Product[p.size()];
        p.toArray(products);
        return products;

    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<CustomerProduct> c = new ArrayList<>();
   
            customerProductDatabase.readFromFile();// composition, can not inherit from more than one class, so we used
                                                   // composition here.
      
        c = customerProductDatabase.returnAllRecords();
        CustomerProduct[] customer = new CustomerProduct[c.size()];
        c.toArray(customer);
        return customer;

    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) throws IOException {
        Product p = productsDatabase.getRecord(productID);
        if(p!=null) 
        {if (p.getQuantity() == 0) // checks if the product is in stock.
        {
            System.out.println("The product is out of stock.");
            return false;
        } else {
            p.sellUnit();// decrements quantity by one.
            CustomerProduct c = new CustomerProduct(customerSSN, productID, purchaseDate);
           
                customerProductDatabase.insertRecord(c);
                customerProductDatabase.saveToFile();
                productsDatabase.deleteRecord(p.getSearchKey());
                productsDatabase.insertRecord(p);
                productsDatabase.saveToFile();
            
            System.out.println("The purchase was made successfully!");
            return true;
        }

    } 
        else{ System.out.println("Product not found!");
        return false;}
    }
       

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) throws IOException {
        // checl if return date is before purchase or not
        
        if (returnDate.isBefore(purchaseDate))
        {System.out.println("Return date is incorrect.");
            return -1;}

       CustomerProduct c=new CustomerProduct(customerSSN,productID,purchaseDate);
           customerProductDatabase.readFromFile();// composition, can not inherit from more than one class, so we used
                                                   // composition here.
        if (customerProductDatabase.getRecord(c.getSearchKey())==null)
        {System.out.println("Record not found!");
            return -1;}
        // check if product is found or not

        if (ChronoUnit.DAYS.between(purchaseDate, returnDate) > 14)
        {System.out.println("Exceeded the 14 days.");
            return -1;}
        // ChronoUnit.DAYS it measure times in units like days,month, years
        // .between calculate the difference in time between two local objects
        productsDatabase.readFromFile();

        Product productMatch = productsDatabase.getRecord(productID);

        if (productMatch != null)
        {productMatch.returnUnit();
        
              
            productsDatabase.deleteRecord(productID);
            productsDatabase.insertRecord(productMatch);
            productsDatabase.saveToFile();
            customerProductDatabase.deleteRecord(c.getSearchKey());
            customerProductDatabase.saveToFile();
            System.out.println("The product was returned successfully!");
           return productMatch.getPrice();
        }
        else
        return -1;


        
        // update both customerproduct anf product databases files
     

    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) throws IOException {
        ArrayList<CustomerProduct> records = customerProductDatabase.returnAllRecords();

        for (CustomerProduct record : records) {
            if ((record.getCustomerSSN().equals(customerSSN)) && (record.getPurchaseDate().equals(purchaseDate))) {
                if (record.isPaid()) {
                    System.out.println("Already paid.");
                    return true;
                } // check if its paid or not
                record.setPaid(true);
               
                    customerProductDatabase.deleteRecord(record.getSearchKey());// deletes old record
                    customerProductDatabase.insertRecord(record);// insert the new updated record
                    customerProductDatabase.saveToFile();
                    System.out.println("Payment made successfully!");
                
                return true;
            } // not paid then set it to paid
        }
        return false;// record is not found
    }

    public void logout() {
       try{
            productsDatabase.saveToFile();
           customerProductDatabase.saveToFile();}
       catch(IOException e)
       {System.out.println(e.getMessage());}
        
    }
}

