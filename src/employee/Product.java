package employee;

import database_model.Representation;

public class Product implements Representation{
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;

    public Product(String productID, String productName, String manufacturerName,
            String supplierName, int quantity, float price) {
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }

    // ================ Accessors and Mutators ================
    // To be editied in linking phase

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
@override
    public String getSearchKey() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public float getPrice() {
        return price;
    }

    // ================ Methods ================
@override
    public String lineRepresentation() { // Returns a comma-separated line of product data
        return productID + "," + productName + "," + manufacturerName + "," +
                supplierName + "," + quantity + "," + price;
    }

    // Optional methods
    // To be edited in linking phase

    public void sellUnit() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public void returnUnit() {
        quantity++;
    }
}
