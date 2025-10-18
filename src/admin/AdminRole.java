package admin;

import java.io.IOException;
import java.util.ArrayList;

public class AdminRole {
    private final EmployeeUserDatabase database; // Composition relationship: AdminRole owns EmployeeUserDatabase

    public AdminRole() {
        database = new EmployeeUserDatabase("Employees.txt");
    }

    public EmployeeUserDatabase getDatabase() {
        return database;
    }

    // ---------------------------- Add Employee ----------------------------
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        try {
            EmployeeUser newEmployee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
            database.insertRecord(newEmployee);
            database.saveToFile(); // may throw IOException
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

    // ---------------------------- Get List of Employees
    // ----------------------------
    public EmployeeUser[] getListOfEmployees() {
        database.readFromFile();

        ArrayList<EmployeeUser> list = database.returnAllRecords(); // Used ArrayList beacause it can change size
        // dynamically and offers convenient methods
        // (add,remove,search,filter)
        return list.toArray(new EmployeeUser[0]);
    }

    // ---------------------------- Remove Employee ----------------------------
    public void removeEmployee(String key) {
        try {
            database.deleteRecord(key);
            database.saveToFile();
        } catch (IOException e) {
            System.out.println("Error deleting employee record: " + e.getMessage());
        }
    }

    // ---------------------------- Logout ----------------------------
    public void logout() {
        try {
            database.saveToFile(); // may throw IOException
            System.out.println("Employee database saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving file during logout: " + e.getMessage());
        }
    }
}
