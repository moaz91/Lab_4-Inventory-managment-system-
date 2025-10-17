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
            int quantity, float price) {
        Product p = new Product(productID, productName, manufacturerName, supplierName, quantity, price);// composition
        try {
            productsDatabase.insertRecord(p);
            productsDatabase.saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Product[] getListOfProducts() {
        ArrayList<Product> p = new ArrayList<>();
        // composition, can not inherit from more than one class, so we used composition
        // here.
        try {
            productsDatabase.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        p = productsDatabase.returnAllRecords();
        Product[] products = new Product[p.size()];
        p.toArray(products);
        return products;

    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<CustomerProduct> c = new ArrayList<>();
        try {
            customerProductDatabase.readFromFile();// composition, can not inherit from more than one class, so we used
                                                   // composition here.
        } catch (IOException e) {
            e.printStackTrace();
        }
        c = customerProductDatabase.returnAllRecords();
        CustomerProduct[] customer = new CustomerProduct[c.size()];
        c.toArray(customer);
        return customer;

    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        Product p = productsDatabase.getRecord(productID);
        if (p.getQuantity() == 0) // checks if the product is in stock.
        {
            System.out.println("The product is out of stock.");
            return false;
        } else {
            p.sellUnit();// decrements quantity by one.
            CustomerProduct c = new CustomerProduct(customerSSN, productID, purchaseDate);
            try {
                customerProductDatabase.insertRecord(c);
                customerProductDatabase.saveToFile();
                productsDatabase.deleteRecord(p.getSearchKey());
                productsDatabase.insertRecord(p);
                productsDatabase.saveToFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("The purchase was made successfully!");
            return true;
        }

    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        // checl if return date is before purchase or not
        if (returnDate.isBefore(purchaseDate))
            return -1;

        String customerKey = customerSSN + "," + productID + "," + String.format("%02d-%02d-%04d",
                purchaseDate.getDayOfMonth(), purchaseDate.getMonthValue(), purchaseDate.getYear());
        if (!customerProductDatabase.contains(customerKey))
            return -1;
        // check if product is found or not

        if (ChronoUnit.DAYS.between(purchaseDate, returnDate) > 14)
            return -1;
        // ChronoUnit.DAYS it measure times in units like days,month, years
        // .between calculate the difference in time between two local objects

        Product productMatch = productsDatabase.getRecord(productID);

        if (productMatch != null)
            productMatch.setQuantity(productMatch.getQuantity() + 1);
        else
            return -1;

        try {
            productsDatabase.deleteRecord(productID);
            productsDatabase.insertRecord(productMatch);
            productsDatabase.saveToFile();
            customerProductDatabase.deleteRecord(customerKey);
            customerProductDatabase.saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // update both customerproduct anf product databases files
        return productMatch.getPrice();

    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        ArrayList<CustomerProduct> records = customerProductDatabase.returnAllRecords();

        for (CustomerProduct record : records) {
            if (record.getCustomerSSN().equals(customerSSN) && record.getPurchaseDate().equals(purchaseDate)) {
                if (record.isPaid()) {
                    return false;
                } // check if its paid or not
                record.setPaid(true);
                try {
                    customerProductDatabase.deleteRecord(record.getSearchKey());// deletes old record
                    customerProductDatabase.insertRecord(record);// insert the new updated record
                    customerProductDatabase.saveToFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } // not paid then set it to paid
        }
        return false;// record is not found
    }

    public void logout() {
        try {
            ProductDatabase.saveToFile();
            CustomerProductDatabase.saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
