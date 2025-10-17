package admin;

import java.util.ArrayList;

import database_model.Database;

public class EmployeeUserDatabase extends Database<EmployeeUser> {

    public EmployeeUserDatabase(String filename) {
        super(filename);
        this.records = new ArrayList<>();
    }

    public EmployeeUser createRecordFrom(String line) {
        if (line == null)
            return null;
        String[] field = line.split(",");
        EmployeeUser employee = new EmployeeUser(field[0], field[1], field[2], field[3], field[4]);
        return employee;
    }

}
