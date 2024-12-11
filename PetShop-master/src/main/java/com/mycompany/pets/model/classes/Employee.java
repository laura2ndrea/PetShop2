package com.mycompany.pets.model.classes;

import com.mycompany.pets.controller.ControllerEmployee;
import com.mycompany.pets.model.classes.superclasses.Person;
import com.mycompany.pets.model.classes.superclasses.Type;
import java.util.Scanner;

public class Employee extends Person {

    private Type type;
    public Object get;

    public Employee(int id, String ID, String name, String phoneNumber, String email, Type type) {
        super(id, ID, name, phoneNumber, email);
        this.type = type;
    }

    public Employee() {
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.getId() + ". "+super.getName()+" - "+type.getType();
    }

    public static Employee assignEmployee(Scanner scanner) {
        Employee employee = null;
        while (employee == null) {
            try {
                System.out.println("\n--- Employee List ---");
                ControllerEmployee.listEmployees().forEach(System.out::println);
                System.out.println("---------------------");
                System.out.println("Enter an employee:");
                int id = scanner.nextInt();
                employee = ControllerEmployee.searchEmployee(id);
                if (employee == null) {
                    System.out.println("No valid, enter a valid value");
                }
            } catch (Exception e) {
                System.out.println("Error: enter correct format");
                scanner.nextLine();
            }
        }
        return employee;
    }

}
