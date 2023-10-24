import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private String filePath;

    public EmployeeDAO(String filePath) {
        this.filePath = filePath;
    }

    //Create new employee in CSV file
    public void create(Employee employee) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(employee.getFirstName() + "," + employee.getLastName() + "," + employee.getEmployeeID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Read all employees from text file
    public List<Employee> readAll() {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    employees.add(new Employee(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    //Getter for access to filePath outside of EmployeeDAO class
    public String getFilePath() {
        return filePath;
    }
}