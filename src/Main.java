import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.io.IOException;

import admin.AdminRole;
import admin.EmployeeUser;
import employee.EmployeeRole;
import employee.Product;
import employee.CustomerProduct;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Admin");
            System.out.println("2. Employee");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    runAdminMenu(scanner);
                    break;
                case "2":
                    runEmployeeMenu(scanner);
                    break;
                case "3":
                    exitProgram = true;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }

    // -------------------- ADMIN MENU --------------------
    private static void runAdminMenu(Scanner scanner) {
        AdminRole admin = new AdminRole();
        boolean logout = false;

        while (!logout) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. View Employee Database");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter employee ID: ");
                    String employeeId = scanner.nextLine().trim();
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter employee email: ");
                    String email = scanner.nextLine().trim();
                    System.out.print("Enter employee address: ");
                    String address = scanner.nextLine().trim();
                    System.out.print("Enter employee phone number: ");
                    String phone = scanner.nextLine().trim();

                    try {
                        admin.addEmployee(employeeId, name, email, address, phone);
                        System.out.println("Employee added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error adding employee: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.print("Enter employee ID to remove: ");
                    String key = scanner.nextLine().trim();
                    try {
                        admin.removeEmployee(key);
                        System.out.println("Employee removed successfully.");
                    } catch (Exception e) {
                        System.out.println("Error removing employee: " + e.getMessage());
                    }
                    break;

                case "3":
                    try {
                        EmployeeUser[] list = admin.getListOfEmployees();
                        if (list == null || list.length == 0) {
                            System.out.println("No employees found.");
                        } else {
                            System.out.println("Employees:");
                            for (EmployeeUser u : list) {
                                System.out.println(" - " + u.lineRepresentation());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error retrieving database: " + e.getMessage());
                    }
                    break;

                case "4":
                    admin.logout();
                    logout = true;
                    System.out.println("Admin logged out.");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // -------------------- EMPLOYEE MENU --------------------
    private static void runEmployeeMenu(Scanner scanner) {
        EmployeeRole employee = new EmployeeRole();
        boolean logout = false;

        while (!logout) {
            System.out.println("\n=== EMPLOYEE MENU ===");
            System.out.println("1. Add Product");
            System.out.println("2. List Products");
            System.out.println("3. Purchase Product");
            System.out.println("4. Return Product");
            System.out.println("5. List Purchases");
            System.out.println("6. Apply Payment");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    try {
                        System.out.print("Enter product ID: ");
                        String productID = scanner.nextLine().trim();
                        System.out.print("Enter product name: ");
                        String productName = scanner.nextLine().trim();
                        System.out.print("Enter manufacturer name: ");
                        String manufacturer = scanner.nextLine().trim();
                        System.out.print("Enter supplier name: ");
                        String supplier = scanner.nextLine().trim();
                        System.out.print("Enter quantity (integer): ");
                        int quantity = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter price (float): ");
                        float price = Float.parseFloat(scanner.nextLine().trim());

                        employee.addProduct(productID, productName, manufacturer, supplier, quantity, price);
                        System.out.println("Product added successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number input. Please try again.");
                    } catch (IOException e) {
                        System.out.println("I/O error while adding product: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error adding product: " + e.getMessage());
                    }
                    break;

                case "2":
                    try {
                        Product[] products = employee.getListOfProducts();
                        if (products == null || products.length == 0) {
                            System.out.println("No products found.");
                        } else {
                            System.out.println("Products:");
                            for (Product p : products) {
                                System.out.println(" - ID: " + p.getSearchKey() + ", Name: " + p.getProductName()
                                        + ", Manufacturer: " + p.getManufacturerName() + ", Supplier: "
                                        + p.getSupplierName() + ", Quantity: " + p.getQuantity() + ", Price: "
                                        + p.getPrice());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error listing products: " + e.getMessage());
                    }
                    break;

                case "3":
                    try {
                        System.out.print("Enter customer SSN: ");
                        String customerSSN = scanner.nextLine().trim();
                        System.out.print("Enter product ID to purchase: ");
                        String productId = scanner.nextLine().trim();
                        System.out.print("Enter purchase date (YYYY-MM-DD): ");
                        LocalDate purchaseDate = LocalDate.parse(scanner.nextLine().trim());

                        employee.purchaseProduct(customerSSN, productId, purchaseDate);
                        //System.out.println("Product purchased successfully.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    } catch (Exception e) {
                        System.out.println("Error purchasing product: " + e.getMessage());
                    }
                    break;

                case "4":
                    try {
                        System.out.print("Enter customer SSN: ");
                        String custSSN = scanner.nextLine().trim();
                        System.out.print("Enter product ID to return: ");
                        String prodId = scanner.nextLine().trim();
                        System.out.print("Enter original purchase date (YYYY-MM-DD): ");
                        LocalDate pDate = LocalDate.parse(scanner.nextLine().trim());
                        System.out.print("Enter return date (YYYY-MM-DD): ");
                        LocalDate rDate = LocalDate.parse(scanner.nextLine().trim());

                        employee.returnProduct(custSSN, prodId, pDate, rDate);
                   
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    } catch (Exception e) {
                        System.out.println("Error returning product: " + e.getMessage());
                    }
                    break;

                case "5":
                    try {
                        CustomerProduct[] purchases = employee.getListOfPurchasingOperations();
                        if (purchases == null || purchases.length == 0) {
                            System.out.println("No purchasing operations found.");
                        } else {
                            System.out.println("Purchasing operations:");
                            for (CustomerProduct cp : purchases) {
                                System.out.println(" - " + cp.lineRepresentation());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error listing purchases: " + e.getMessage());
                    }
                    break;

                case "6":
                    try {
                        System.out.print("Enter customer SSN for payment: ");
                        String custPaymentId = scanner.nextLine().trim();
                        System.out.print("Enter payment date (YYYY-MM-DD): ");
                        LocalDate paymentDate = LocalDate.parse(scanner.nextLine().trim());

                         employee.applyPayment(custPaymentId, paymentDate);
                        
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    } catch (Exception e) {
                        System.out.println("Error applying payment: " + e.getMessage());
                    }
                    break;

                case "7":
                    employee.logout();
                    logout = true;
                    System.out.println("Employee logged out.");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
