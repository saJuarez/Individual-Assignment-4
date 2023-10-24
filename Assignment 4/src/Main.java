import java.util.Scanner;

public class Main {
    private final EmployeeDAO employeeDAO;
    private final Scanner scanner;

    public Main(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO("employees.txt");
        Main console = new Main(employeeDAO);
        EmployeeUI employeeUI = new EmployeeUI(employeeDAO, console.scanner);
        employeeUI.start();
    }
}