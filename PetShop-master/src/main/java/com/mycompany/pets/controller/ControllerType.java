package com.mycompany.pets.controller;

import com.mycompany.pets.model.classes.superclasses.Type;
import com.mycompany.pets.model.persistence.CRUD;
import com.mycompany.pets.model.persistence.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ControllerType {

    public static List<Type> listTypes(String typeOfType) {
        CRUD.setConnection(DBConnection.connectionDB());
        List<Type> listTypes = new ArrayList<>();
        String sql = "SELECT * FROM Type" + typeOfType;
        List<Object> parameters = new ArrayList<>();

        try {
            ResultSet rs = CRUD.consultDB(sql, parameters);

            while (rs != null && rs.next()) {
                Type t1 = new Type();
                t1.setId(rs.getInt("IDType" + typeOfType));
                t1.setType(rs.getString("name"));
                listTypes.add(t1);
            }

        } catch (SQLException ex) {
            System.out.println("Error listing animals: " + ex.getMessage());
        } finally {
            CRUD.closeCon();
        }

        return listTypes;
    }

    public static Type searchType(int id, String typeOfType) {
        CRUD.setConnection(DBConnection.connectionDB());
        String consult = "SELECT * FROM Type" + typeOfType + " WHERE IDType" + typeOfType + "= ?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        Type type = null;

        try (ResultSet rs = CRUD.consultDB(consult, parameters)) {
            if (rs != null && rs.next()) { // Verify if there are results
                type = new Type();
                type.setId(rs.getInt("IDType" + typeOfType));
                type.setType(rs.getString("name"));
            } else {
                System.out.println("No Type has been found with the id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error searching for type: " + e.getMessage());
        } finally {
            CRUD.closeCon();
        }

        return type;
    }

}
