package com.mycompany.pets.controller;

import com.mycompany.pets.model.persistence.CRUD;
import com.mycompany.pets.model.persistence.DBConnection;
import com.mycompany.pets.model.classes.superclasses.Service;
import com.mycompany.pets.model.classes.superclasses.Type;
import com.mycompany.pets.model.classes.utilities.UtilityTime;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerService {

    public static boolean addService(Service s1) {
        CRUD.setConnection(DBConnection.connectionDB());

        String insertion = """
                           INSERT INTO Services (IDService, dateService, IDEmployee, IDPet, IDTypeService) VALUES
                           (? ,? ,? , ?, ?)""";
        List<Object> parameters = new ArrayList<>();
        parameters.add(s1.getIdService());
        parameters.add(s1.getDate());
        parameters.add(s1.getEmployee().getId());
        parameters.add(s1.getPet().getId());
        parameters.add(s1.getTypeService().getId());

        try {
            if (CRUD.setAutoCommitDB(false)) {
                boolean success = CRUD.insertDB(insertion, parameters);
                if (success) {
                    CRUD.commitDB();
                    return true;
                } else {
                    CRUD.rollbackDB();
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            CRUD.rollbackDB();
            throw e;
        } finally {
            CRUD.closeCon();
        }
    }

    public static List<Service> listServices() {
        CRUD.setConnection(DBConnection.connectionDB());
        List<Service> listServices = new ArrayList<>();
        String sql = "SELECT * FROM Services";
        List<Object> parameters = new ArrayList<>();
        try {
            ResultSet rs = CRUD.consultDB(sql, parameters);
            while (rs != null && rs.next()) {
                Service s1 = new Service();
                s1.setIdService(rs.getInt("IDService"));
                s1.setEmployeee(ControllerEmployee.searchEmployee(rs.getInt("IDEmployee")));
                s1.setDate(UtilityTime.changeSqlDate(rs.getString("dateService")));
                Type type = ControllerType.searchType(rs.getInt("IDTypeService"), "Employee");
                s1.setTypeService(type);
                listServices.add(s1);
            }
        } catch (SQLException ex) {
            System.out.println("Error listing Services: " + ex.getMessage());
        } finally {
            CRUD.closeCon();
        }

        return listServices;
    }

}
