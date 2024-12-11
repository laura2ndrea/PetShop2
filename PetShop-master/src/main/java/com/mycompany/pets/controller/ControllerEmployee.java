
package com.mycompany.pets.controller;

import com.mycompany.pets.model.classes.Employee;
import com.mycompany.pets.model.classes.superclasses.Type;
import com.mycompany.pets.model.persistence.CRUD;
import com.mycompany.pets.model.persistence.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ControllerEmployee {
    public static List<Employee> listEmployees() {
        CRUD.setConnection(DBConnection.connectionDB());
        List<Employee> listEmployees = new ArrayList<>();
        String sql = "SELECT * FROM Employees";
        List<Object> parameters = new ArrayList<>();

        try {
            ResultSet rs = CRUD.consultDB(sql, parameters);

            while (rs != null && rs.next()) {
                Employee e1 = new Employee();
                e1.setId(rs.getInt("IDEmployee"));
                e1.setID(rs.getString("ID"));
                e1.setName(rs.getString("name"));
                e1.setPhoneNumber(rs.getString("phoneNumber"));
                e1.setEmail(rs.getString("email"));
                Type type = ControllerType.searchType(rs.getInt("IDTypeEmployee"), "Employee");
                e1.setType(type);
                listEmployees.add(e1);
            }

        } catch (SQLException ex) {
            System.out.println("Error listing Employees: " + ex.getMessage());
        } finally {
            CRUD.closeCon();
        }

        return listEmployees;
    }
        
        public static Employee searchEmployee(int id) {
        CRUD.setConnection(DBConnection.connectionDB());
        String consult = "SELECT * FROM Employees WHERE IDEmployee= ?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Employee employee = null;

        try (ResultSet rs = CRUD.consultDB(consult, parameters)) {
            if (rs != null && rs.next()) { // Verify if there are results
                employee = new Employee();
                employee.setId(rs.getInt("IDEmployee"));
                employee.setID(rs.getString("ID"));
                employee.setName(rs.getString("name"));
                employee.setPhoneNumber(rs.getString("phoneNumber"));
                employee.setEmail(rs.getString("email"));
                Type type = ControllerType.searchType(rs.getInt("IDTypeEmployee"), "Employee");
                employee.setType(type);
            } else {
                System.out.println("No Employee has been found with the id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error searching employee: " + e.getMessage());
        } finally {
            CRUD.closeCon();
        }

        return employee;
    }
        
        public static Employee assignEmployee(int id) {
        CRUD.setConnection(DBConnection.connectionDB());
        String consult = "SELECT * FROM Employees WHERE IDEmployee= ?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Employee employee = null;

        try (ResultSet rs = CRUD.consultDB(consult, parameters)) {
            if (rs != null && rs.next()) { // Verify if there are results
                employee = new Employee();
                employee.setId(rs.getInt("IDEmployee"));
                employee.setID(rs.getString("ID"));
                employee.setName(rs.getString("name"));
                employee.setPhoneNumber(rs.getString("phoneNumber"));
                employee.setEmail(rs.getString("email"));
                Type type = ControllerType.searchType(rs.getInt("IDTypeEmployee"), "Employee");
                employee.setType(type);
            } else {
                System.out.println("No Employee has been found with the id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error searching employee: " + e.getMessage());
        } finally {
            CRUD.closeCon();
        }

        return employee;
    }
        
}
