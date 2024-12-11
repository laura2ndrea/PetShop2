package com.mycompany.pets.model.classes.utilities;

import com.mycompany.pets.controller.ControllerOwner;
import com.mycompany.pets.model.classes.Owner;

import java.util.List;
import java.util.Scanner;

public class OwnerUtils {
    
     public static Owner promptOwner(Scanner scanner) {
        System.out.println("Listing all available owners:");
        List<Owner> owners = ControllerOwner.listOwners();

        if (owners.isEmpty()) {
            System.out.println("No owners found in the database.");
            return null;
        }

        // Displaying all owners with the ID and name
        for (Owner owner : owners) {
            System.out.printf("IDOwner: %d | Name: %s%n", owner.getId(), owner.getName());  // Usando getId() para obtener el id del propietario
        }

        // Prompting user for owner ID selection
        int selectedId = Utility.promptInt(scanner, "Select an Owner by entering their IDOwner");

        // Iterating over the list of owners to find the selected one
        for (Owner owner : owners) {
            // Use getId() here to compare the owner's ID
            if (owner.getId() == selectedId) {
                System.out.println("Owner selected: " + owner.getName());
                return owner;
            }
        }

        // If no match is found
        System.out.println("Invalid ID selected. Please try again.");
        return promptOwner(scanner);  // Recurse if the selection was invalid
    }
}
