package admin;

import database_model.Representation;

public class EmployeeUser implements Representation  {
    private final String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber; 

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        this.employeeId = employeeId;
        setName(name);
        setEmail(email);
        this.address = address;
        setphoneNumber(phoneNumber);
    }

    //---------------Validation name-------------------------------//
    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' ') {
                throw new IllegalArgumentException("Name can only contain letters and spaces");
            }
        }

        this.name = name;
    }

    //------------------Email Validation---------------------------//
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        // Simple email format check using regular expression
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            //it means it should start with a letter and @ should not come last
            throw new IllegalArgumentException("Invalid email format");
        }

        this.email = email;
    }

    //----------------------Phone Number Validation-----------------//
    public void setphoneNumber(String phoneNumber){
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if(phoneNumber.length()<11){
            throw new IllegalArgumentException("Phone number is too short");
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                throw new IllegalArgumentException("Phone number must contain only digits");
            }
        }
        this.phoneNumber=phoneNumber;
    }
    @Override
    public String lineRepresentation() {
        return this.employeeId + "," + this.name + "," + this.email + "," + this.address + "," + this.phoneNumber;
    }
@Override
    public String getSearchKey() {
        return this.employeeId;
    }

}
