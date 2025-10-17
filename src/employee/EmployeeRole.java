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
    private final ProductDatabase productsDatabase; //final because the filenames will not change.
    private final CustomerProductDatabase customerProductDatabase;
    
    public EmployeeRole() {
        productsDatabase=new ProductDatabase("Products.txt");//composition
          customerProductDatabase=new CustomerProductDatabase("CustomersProducts.txt");//composition
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName,
            int quantity, float price) {
        Product p=new Product(productID,productName,manufacturerName,supplierName,quantity,price);//composition
        productsDatabase.insertRecord(p);
        productsDatabase.saveToFile();
                
    }

    public Product[] getListOfProducts()  {
        ArrayList<Product> p = new ArrayList<>();
        //composition, can not inherit from more than one class, so we used composition here.
        productsDatabase.readFromFile();
        p=productsDatabase.returnAllRecords();
        Product[] products = new Product[p.size()];
        p.toArray(products);
        return products;

    }

    public CustomerProduct[] getListOfPurchasingOperations()  { 
     ArrayList<CustomerProduct> c = new ArrayList<>();
       customerProductDatabase.readFromFile();//composition, can not inherit from more than one class, so we used composition here.
      c=customerProductDatabase.returnAllRecords();
      CustomerProduct[] customer = new CustomerProduct[c.size()];
      c.toArray(customer);
      return customer;
      
       

    }
    
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate)
    {  Product p= productsDatabase.getRecord(productID);
    if(p.getQuantity()==0) //checks if the product is in stock.
    {System.out.println("The product is out of stock.");
    return false;}
    else
    {p.sellUnit();// decrements quantity by one.
    CustomerProduct c=new CustomerProduct(customerSSN,productID,purchaseDate);
    customerProductDatabase.insertRecord(c);
    customerProductDatabase.saveToFile();
    productsDatabase.deleteRecord(p.getSearchKey());
    productsDatabase.insertRecord(p);
    productsDatabase.saveToFile();
        System.out.println("The purchase was made successfully!");
        return true;
    }
    
    
    }
    
    

}
