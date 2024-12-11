/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pets.controller;

import com.mycompany.pets.model.classes.Consultation;
import com.mycompany.pets.model.classes.Pet;
import com.mycompany.pets.model.classes.enums.Status;
import com.mycompany.pets.model.classes.superclasses.Service;
import com.mycompany.pets.model.classes.utilities.UtilityTime;
import com.mycompany.pets.model.persistence.CRUD;
import com.mycompany.pets.model.persistence.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public abstract class ControllerConsultation {

    public static boolean addAppointment(Consultation c1) {
        CRUD.setConnection(DBConnection.connectionDB());

        String insertion = """
                           INSERT INTO Consultations (IDConsultation, IDReason, isControl, price, IDService) VALUES
                           (? ,? ,? , ?, ?)""";
        List<Object> parameters = new ArrayList<>();
        parameters.add(c1.getIdConsultation());
        parameters.add(c1.getReason().getId());
        parameters.add(c1.getIsControl());
        parameters.add(c1.getPrice());
        parameters.add(c1.getIdService());
        
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

public static List<Consultation> listConsultations() {
    CRUD.setConnection(DBConnection.connectionDB());
    List<Consultation> listConsultations = new ArrayList<>();
    String sql = "select \n"
            + "    Consultations.IDConsultation,\n"
            + "    Consultations.IDTypeReason,\n"
            + "    Consultations.isControl,\n"
            + "    Consultations.price,\n"
            + "    Consultations.IDService,\n"
            + "    Services.dateService,\n"
            + "    Services.IDEmployee,\n"
            + "    Services.IDPet,\n"
            + "    Services.IDTypeService\n"
            + "    Services.status\n"
            + "    Services.paid\n"
            + "from \n"
            + "    Consultations\n"
            + "join \n"
            + "    Services\n"
            + "on \n"
            + "    Consultations.IDService = Services.IDService;";
    List<Object> parameters = new ArrayList<>();
    try {
        ResultSet rs = CRUD.consultDB(sql, parameters);
        while (rs != null && rs.next()) {
            Service service = new Service(
                    rs.getInt("IDService"),
                    UtilityTime.changeSqlDate(rs.getString("dateService")),
                    new Pet(rs.getInt("IDPet")),
                    ControllerEmployee.searchEmployee(rs.getInt("IDEmployee")),
                    ControllerType.searchType(rs.getInt("IDTypeService"), "Service"),
                    Status.SCHEDULED, false);
            Consultation consultation = new Consultation.Builder(service)
                    .setIdConsultation(rs.getInt("IDConsultation"))
                    .setReason(ControllerType.searchType(rs.getInt("IDTypeReason"), "Reason"))
                    .setIsControl(rs.getBoolean("isControl"))
                    .setStatus(Status.valueOf(rs.getString("status").toUpperCase()))
                    .setPrice(rs.getDouble("price"))
                    .build();
            listConsultations.add(consultation);
        }
    } catch (SQLException ex) {
        System.out.println("Error listing Consultations: " + ex.getMessage());
    } finally {
        CRUD.closeCon();
    }

    return listConsultations;
}

}
