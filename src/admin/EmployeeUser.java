package admin;

public class EmployeeUser {
private final String  employeeId; 
    private final String name;
    private final String email;
    private final String address;
    private final String phoneNumber;//we can't change them so final

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String lineRepresentation(){
        return this.employeeId +","+this.name +","+this.email+","+this.address+","+this.phoneNumber;
    }
    public String getSearchKey(){
        return this.employeeId;
    }
    
}
