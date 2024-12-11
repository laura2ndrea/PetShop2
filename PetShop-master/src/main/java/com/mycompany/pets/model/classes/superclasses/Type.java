/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pets.model.classes.superclasses;

import com.mycompany.pets.controller.ControllerType;
import java.util.Scanner;

/**
 *
 * @author DELLi
 */
public class Type {

    private int id;
    private String type;

    public Type(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Type() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return id + ". " + type;
    }

    public static Type assignType(Scanner scanner, String typeOfType) {
        Type type = null;
        while (type == null) {
            try {
                System.out.println("\n--- Types List ---");
                ControllerType.listTypes(typeOfType).forEach(System.out::println);
                System.out.println("---------------------");
                System.out.print("Choose one: ");
                int id = scanner.nextInt();
                type = ControllerType.searchType(id, typeOfType);
                if (type == null) {
                    System.out.println("Not valid, enter a valid value.");
                }
            } catch (Exception e) {
                System.out.println("Error: enter correct format.");
                scanner.nextLine();
            }
        }
        return type;
    }

}
