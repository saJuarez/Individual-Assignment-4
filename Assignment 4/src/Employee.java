public class Employee {
    private String firstName;
    private String lastName;
    private String employeeID;

    /*
    * Constructers, getters, and setters
    */
    public Employee(String firstName, String lastName, String employeeID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}