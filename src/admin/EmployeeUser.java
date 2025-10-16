package admin;

public class EmployeeUser {
private final String  employeeId; 
    private final String Name;
    private final String Email;
    private final String Address;
    private final String PhoneNumber;//we can't change them so final

    public EmployeeUser(String employeeId, String Name, String Email, String Address, String PhoneNumber) {
        this.employeeId = employeeId;
        this.Name = Name;
        this.Email = Email;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
    }

    public String lineRepresentation(){
        return this.employeeId +","+this.Name +","+this.Email+","+this.Address+","+this.PhoneNumber;
    }
    public String getSearchKey(){
        return this.employeeId;
    }
    
}
