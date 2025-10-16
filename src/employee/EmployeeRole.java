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
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName,
            int quantity, float price) throws IOException {
        FileWriter writer = new FileWriter("Products.txt", true);
        writer.write(
                "\n" + productID + "," + productName + "," + manufacturerName + "," + supplierName + "," + quantity
                        + "," + price);
        writer.close();
    }

    public Product[] getListOfProducts() throws IOException {
        ArrayList<Product> p = new ArrayList<>();
        String line;// to read a line as a string from the file.
        String[] splitted;

        File file = new File("Product.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) // While the file has a next line (will read until no new line is found).
        {
            line = scan.nextLine();
            splitted = line.split(",");
            if (splitted.length != 6) {
                System.out.println("File is not written correctly.");
                break;
            }
            p.add(new Product(splitted[0], splitted[1], splitted[2], splitted[3], Integer.parseInt(splitted[4]),
                    Float.parseFloat(splitted[5])));
        }
        Product[] products = new Product[p.size()];
        for (int i = 0; i < p.size(); i++) {
            products[i] = p.get(i);
        }
        return products;

    }

    public CustomerProduct[] getListOfPurchasingOperations() throws IOException {
        ArrayList<CustomerProduct> c = new ArrayList<>();
        String line;// to read a line as a string from the file.
        String[] splitted;

        File file = new File("CustomersProducts.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) // While the file has a next line (will read until no new line is found).
        {
            line = scan.nextLine();
            splitted = line.split(",");
            if (splitted.length != 4) {
                System.out.println("The file is not written correctly.");// Not all fields are written.
                return null;
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");// The default LocalDate is
                                                                                        // YYYY/MM/DD, so we need to
                                                                                        // tell it to read the date in
                                                                                        // the form of dd-MM-yyyy
                // also if you replace dd with DD, it will be wrong, beacuse DD would be the day
                // of the year like 1-365. And YY means week based year.
                CustomerProduct newCustomerProduct = new CustomerProduct(splitted[0], splitted[1],
                        LocalDate.parse(splitted[2], formatter));
                newCustomerProduct.setPaid(Boolean.parseBoolean(splitted[3]));
                c.add(newCustomerProduct);

            }
        }

        CustomerProduct[] customer = new CustomerProduct[c.size()];
        for (int i = 0; i < c.size(); i++) {
            customer[i] = c.get(i);
        }
        return customer;

    }

}
