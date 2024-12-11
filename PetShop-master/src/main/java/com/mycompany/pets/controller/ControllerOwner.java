package com.mycompany.pets.controller;

import com.mycompany.pets.model.classes.Owner;
import com.mycompany.pets.model.persistence.CRUD;
import com.mycompany.pets.model.persistence.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControllerOwner {

    public static boolean addOwner(Owner owner) {
        CRUD.setConnection(DBConnection.connectionDB());
        String insertion = """
                       INSERT INTO Owners (ID, name, adress, phoneNumber, email, signature, points, subscription, CUFE) 
                       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        List<Object> parameters = new ArrayList<>();
        parameters.add(owner.getId());
        parameters.add(owner.getName());
        parameters.add(owner.getAdress());
        parameters.add(owner.getPhoneNumber());
        parameters.add(owner.getEmail());
        parameters.add(owner.getSignature());
        parameters.add(owner.getPoints());
        parameters.add(owner.isSubscripcion());
        parameters.add(owner.getCUFE());

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

    public static List<Owner> listOwners() {
        CRUD.setConnection(DBConnection.connectionDB());
        List<Owner> listOwners = new ArrayList<>();
        String sql = "SELECT * FROM Owners";
        List<Object> parameters = new ArrayList<>();

        try {
            ResultSet rs = CRUD.consultDB(sql, parameters);
            while (rs != null && rs.next()) {
                Owner owner = new Owner(
                        rs.getString("adress"),
                        rs.getString("signature"),
                        rs.getInt("points"),
                        rs.getBoolean("subscription"),
                        rs.getString("CUFE"),
                        rs.getInt("IDOwner"),
                        rs.getString("ID"),
                        rs.getString("name"),
                        rs.getString("phoneNumber"),
                        rs.getString("email")
                );
                listOwners.add(owner);
            }
        } catch (SQLException ex) {
            System.out.println("Error listing Owners: " + ex.getMessage());
        } finally {
            CRUD.closeCon();
        }

        return listOwners;
    }

    public static Owner searchOwner(int id) {
        CRUD.setConnection(DBConnection.connectionDB());
        String query = """
                   SELECT 
                       IDOwner, ID, name, adress, phoneNumber, email, signature, points, subscription, CUFE
                   FROM 
                       Owners
                   WHERE 
                       IDOwner = ?""";
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);

        try {
            ResultSet rs = CRUD.consultDB(query, parameters);
            if (rs != null && rs.next()) {
                return new Owner(
                        rs.getString("adress"),
                        rs.getString("signature"),
                        rs.getInt("points"),
                        rs.getBoolean("subscription"),
                        rs.getString("CUFE"),
                        rs.getInt("IDOwner"),
                        rs.getString("ID"),
                        rs.getString("name"),
                        rs.getString("phoneNumber"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error while searching for Owner: " + e.getMessage());
        } finally {
            CRUD.closeCon();
        }
        return null;
    }

    public static Owner assignOwner(Scanner scanner) {
        Owner owner = null;
        while (owner == null) {
            try {
                System.out.println("\n--- Owners List ---");
                ControllerOwner.listOwners().forEach(System.out::println);
                System.out.println("---------------------");
                System.out.print("Choose one (enter the ID): ");
                int id = scanner.nextInt();
                owner = ControllerOwner.searchOwner(id);
                if (owner == null) {
                    System.out.println("Not valid, enter a valid value.");
                }
            } catch (Exception e) {
                System.out.println("Error: enter correct format.");
                scanner.nextLine();
            }
        }
        return owner;
    }

}
