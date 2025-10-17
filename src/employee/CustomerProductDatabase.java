/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import database_model.Database;

/**
 *
 * @author Nour
 */
public class CustomerProductDatabase extends Database<CustomerProduct> {
    public CustomerProductDatabase(String filename) {
        super(filename);
        // TODO Auto-generated constructor stub
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

    @Override
    protected String lineRepresentation(CustomerProduct record) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lineRepresentation'");
    }

    @Override
    protected String getSearchKey(CustomerProduct record) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSearchKey'");
    }

}
