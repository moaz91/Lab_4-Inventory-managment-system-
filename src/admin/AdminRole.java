package admin;

import java.util.ArrayList;

public class AdminRole {
    private EmployeeUserDatabase database;

    public AdminRole() {
        database = new EmployeeUserDatabase("Employees.txt");
    }

    // ================ Accessors and Mutators ================
    // To be editied in linking phase

    public EmployeeUserDatabase getDatabase() {
        return database;
    }

    // ================ Methods ================

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        EmployeeUser newEmployee = new EmployeeUser(employeeId, name, email, address, phoneNumber);

        // ADD TRY CATCH
        database.insertRecord(newEmployee);
    }

    public EmployeeUser[] getListOfEmployees() {
        ArrayList<EmployeeUser> list = database.returnAllRecords(); // Used ArrayList beacause it can change size
                                                                    // dynamically and offers convenient methods
                                                                    // (add,remove,search,filter)
        return list.toArray(new EmployeeUser[0]);
    }

    public void removeEmployee(String key) {
        database.deleteRecord(key);
    }

    public void logout() {
        database.saveToFile();
    }
}
