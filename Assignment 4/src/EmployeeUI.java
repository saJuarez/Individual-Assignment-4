import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class EmployeeUI {
    private final EmployeeDAO employeeDAO;
    private final Scanner scanner;

    public EmployeeUI(EmployeeDAO employeeDAO, Scanner scanner) {
        this.employeeDAO = employeeDAO;
        this.scanner = scanner;
    }

    //Option Menu
    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Create\n2. Display all\n3. Update\n4. Delete\n5. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                //Create an employee
                case 1 -> {
                    System.out.println("Enter employee's first name: ");
                    String firstName = scanner.next();
                    System.out.println("Enter employee's last name: ");
                    String lastName = scanner.next();

                    String employeeID = generateEmployeeID();

                    Employee newEmployee = new Employee(firstName, lastName, employeeID);
                    employeeDAO.create(newEmployee);

                    System.out.println("Employee created successfully with ID: " + employeeID);
                }
                //Display all employees
                case 2 -> {
                    List<Employee> employees = employeeDAO.readAll();
                    if (employees.isEmpty()) {
                        System.out.println("No employees found.");
                    } else {
                        System.out.println("List of Employees:\n");
                        for (Employee employee : employees) {
                            System.out.println("First name: " + employee.getFirstName());
                            System.out.println("Last name: " + employee.getLastName());
                            System.out.println("Employee ID: " + employee.getEmployeeID());
                            System.out.println("----------------------");
                        }
                    }
                }
                //Update an employee's information
                case 3 -> {
                    System.out.println("Enter employee ID to update: ");
                    String employeeID = scanner.next();

                    String filePath = employeeDAO.getFilePath();

                    List<Employee> employees = employeeDAO.readAll(); //Read employee data
                    boolean employeeFound = false;

                    for (Employee employee : employees) {
                        if (employee.getEmployeeID().equals(employeeID)) {
                            //Employee found
                            employeeFound = true;
                            System.out.println("Enter new first name: ");
                            String newFirstName = scanner.next();
                            System.out.println("Enter new last name: ");
                            String newLastName = scanner.next();

                            //Update employee's information
                            employee.setFirstName(newFirstName);
                            employee.setLastName(newLastName);

                            // Write updated employee information back to the CSV file
                            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                                for (Employee updatedEmployee : employees) {
                                    writer.println(updatedEmployee.getFirstName() + "," + updatedEmployee.getLastName() + "," + updatedEmployee.getEmployeeID());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Employee information updated successfully.");
                            break;
                        }
                    }
                    //Case where employee is not found
                    if (!employeeFound) {
                        System.out.println("Employee with ID " + employeeID + " not found.");
                    }
                }
                //Delete an employee
                case 4 -> {
                    System.out.println("Enter employee ID to delete: ");
                    String deleteEmployeeID = scanner.next();

                    String filePath = employeeDAO.getFilePath();

                    List<Employee> employees = employeeDAO.readAll(); //Read employee data
                    boolean employeeFound = false;

                    for (Employee employee : employees) {
                        if (employee.getEmployeeID().equals(deleteEmployeeID)) {
                            //Employee found
                            employeeFound = true;

                            // Remove the employee from the list
                            employees.remove(employee);

                            // Write updated employee information back to the CSV file
                            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                                for (Employee updatedEmployee : employees) {
                                    writer.println(updatedEmployee.getFirstName() + "," + updatedEmployee.getLastName() + "," + updatedEmployee.getEmployeeID());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Employee with ID " + deleteEmployeeID + " has been deleted.");
                            break;
                        }
                    }
                    //Case where employee is not found
                    if (!employeeFound) {
                        System.out.println("Employee with ID " + deleteEmployeeID + " not found.");
                    }
                }
                //Exit the program
                case 5 -> exit = true;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    //Generate a random, 8-digit employee ID
    private String generateEmployeeID() {
        Random random = new Random();
        int min = 10000000;
        int max = 99999999;
        int randomID = random.nextInt(max - min + 1) + min;
        return String.valueOf(randomID);
    }
}