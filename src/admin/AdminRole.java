package admin;

import java.util.ArrayList;

public class AdminRole {
    private EmployeeUserDatabase database; // association type

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

        // check id using contains method
        EmployeeUser newEmployee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.insertRecord(newEmployee);
        database.saveToFile(); //because addEmployee should also add to file not only arraylist.
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
