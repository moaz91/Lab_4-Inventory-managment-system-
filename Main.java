/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nour
 */

import admin.AdminRole;
import admin.EmployeeUser;
import admin.EmployeeUserDatabase;
import employee.CustomerProduct;
import employee.CustomerProductDatabase;
import employee.Product;
import employee.ProductDatabase;
import java.io.IOException;
   import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        // ========== Employee test ==========
        /*ArrayList<EmployeeUser>e=new ArrayList<>();
        EmployeeUserDatabase empDB = new EmployeeUserDatabase("Employees.txt");
        empDB.readFromFile();
        System.out.println("Employees:");
        e=empDB.returnAllRecords();
        for(EmployeeUser employee:e)
        {System.out.println(employee.lineRepresentation());}
        
        empDB.insertRecord(new EmployeeUser("E100", "Nour", "nour@email.com", "Alexandria", "0100000000"));
        empDB.insertRecord(new EmployeeUser("E101", "Ahmed", "ahmed@email.com", "Cairo", "0111111111"));

        System.out.println("Employees:");
        for(EmployeeUser employee:e)
        {System.out.println(employee.lineRepresentation());}
       System.out.println("Contains E101? " + empDB.contains("E101"));
        empDB.deleteRecord("E101");
        System.out.println("After deletion:");
        for(EmployeeUser employee:e)
        {System.out.println(employee.lineRepresentation());}
        empDB.saveToFile();
        empDB.insertRecord(new EmployeeUser("E101", "Ahmed", "ahmed@email.com", "Cairo", "0111111111"));
        empDB.insertRecord(new EmployeeUser("E100", "Nour", "nour@email.com", "Alexandria", "0100000000"));
        empDB.saveToFile();*/


    


        AdminRole a=new AdminRole();
        a.addEmployee("E100", "Nour", "nour@email.com", "Alexandria", "0100000000");
        a.addEmployee("E102", "Mariam", "Mariam@email.com", "Alexandria", "01112130000");
        a.removeEmployee("E103");
    }
}

    


