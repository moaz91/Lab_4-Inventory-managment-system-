/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employee;

import database_model.Representation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;//dd-MM-yyyy

/**
 *
 * @author AltAWKEl
 */
public class CustomerProduct implements Representation{
    // ------------------------variables-------------------------------------------
    private final String customerSSN;
    private final String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    // ------------------------constructor------------------------------------------
    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
    }

    // ------------------------getters-setters------------------------------------------
    // ---------------------remove this part if you dont need
    // it---------------------

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    // ------------------------methods------------------------------------------
    public String getCustomerSSN() {
        return this.customerSSN;
    }

    public String getProductID() {
        return this.productID;
    }

    public LocalDate getPurchaseDate() {
        return this.purchaseDate;
    }
@override
    public String lineRepresentation() {
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate + "," + this.paid;
    }

    public boolean isPaid() {
        return this.paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
@override
    public String getSearchKey() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = this.purchaseDate.format(formatter);
        return this.customerSSN + "," + this.productID + "," + formattedDate;
    }

}
