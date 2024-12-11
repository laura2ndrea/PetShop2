package com.mycompany.pets.view;

import com.mycompany.pets.model.classes.utilities.AnimalUtils;
import java.sql.SQLException;
import java.util.Scanner;

public class PetsMain {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n=== Veterinary Clinic Management ===");
            System.out.println("1. Pets and Owners Management");
            System.out.println("2. Inventory and Supplies Management");
            System.out.println("3. Veterinary Services Schedule");
            System.out.println("4. Complete Veterinary Services");
            System.out.println("5. Billing and Finances");
            System.out.println("6. Special Activities");
            System.out.println("7. Transfer Pet");
            System.out.println("8. Alerts");
            System.out.println("9. Reports");
            System.out.println("10. History");
            System.out.println("11. Exit");
            System.out.print("Select an option: ");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> PetsAndOwners(scanner);
                case 2 -> Inventory(scanner);
                case 3 -> Inventory(scanner);
                case 4 -> Appointments(scanner);
                case 5 -> Billing(scanner);
                case 6 -> SpecialActivities(scanner);
                case 7 -> Transfers(scanner);
                case 8 -> Alerts(scanner);
                case 9 -> Reports(scanner);
                case 10 -> History(scanner);
                case 11 -> System.out.println("Exiting the system....");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 11);
    }

    private static void PetsAndOwners(Scanner scanner) throws SQLException {
        int option;
        do {
            System.out.println("\n--- Pets and Owners Management ---");
            System.out.println("1. Register Pet");
            System.out.println("2. Update Pet Information");
            System.out.println("3. Register Owner");
            System.out.println("4. Update Owner Information");
            System.out.println("5. Return to Main Menu");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> AnimalUtils.animalRegistration(scanner);
                case 2 -> AnimalUtils.displayAndSelectAnimal();
                case 3 -> System.out.println("Function: Register Owner");
                case 4 -> System.out.println("Function: Update Owner Information");
                case 5 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }
    
    private static void Inventory(Scanner scanner) {
        int option;
        do {
            System.out.println("\n--- Inventory and Supplies Management ---");
            System.out.println("1. View Supplies Inventory");
            System.out.println("2. Add New Product");
            System.out.println("3. Update Stock");
            System.out.println("4. Create Purchase Order");
            System.out.println("5. List Purchase Orders");
            System.out.println("6. Return to Main Menu");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> System.out.println("Function: View Supplies Inventory");
                case 2 -> System.out.println("Function: Add New Product");
                case 3 -> System.out.println("Function: Update Stock");
                case 4 -> System.out.println("Function: Alerts for Expiring Products");
                case 5 -> System.out.println("Function: Create Purchase Order");
                case 6 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 6);
    }

    private static void HealthAndVaccination(Scanner scanner) {
        int option;
        do {
            System.out.println("\n--- Health and Vaccination Management ---");
            System.out.println("7. View Medical History");
            System.out.println("8. Vaccination History");
            System.out.println("9. Vaccination Reminders");
            System.out.println("10. Return to Main Menu");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> System.out.println("Function: Register Vaccine");
                case 2 -> System.out.println("Function: Schedule Deworming");
                case 3 -> System.out.println("Function: Create Medical Appointment");
                case 4 -> System.out.println("Function: View Medical History");
                case 5 -> System.out.println("Function: Vaccination Reminders");
                case 6 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 6);
    }

    private static void Appointments(Scanner scanner) {
        int option;
        do {
            System.out.println("\n--- Veterinary Services Schedule ---");
            System.out.println("1. Schedule Service");
            System.out.println("2. Cancel Service");
            System.out.println("3. View Appointment Calendar");
            System.out.println("4. Return to Main Menu");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> System.out.println("Function: Schedule Appointment");
                case 2 -> System.out.println("Function: Update Appointment Status");
                case 3 -> System.out.println("Function: View Appointment Calendar");
                case 4 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 4);
    }

    private static void Billing(Scanner scanner) {
        int option;
        do {
            System.out.println("\n--- Billing and Finance ---");
            System.out.println("1. Generate Invoice");
            System.out.println("2. Register Transaction");
            System.out.println("3. View Invoice History");
            System.out.println("4. Financial Reports");
            System.out.println("5. Return to Main Menu");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> System.out.println("Function: Generate Invoice");
                case 2 -> System.out.println("Function: Register Transaction");
                case 3 -> System.out.println("Function: View Invoice History");
                case 4 -> System.out.println("Function: Financial Reports");
                case 5 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }

    private static void SpecialActivities(Scanner scanner) {
        int option;
        do {
            System.out.println("\n--- Special Activities ---");
            System.out.println("1. Create Event");
            System.out.println("2. Register Event Services");
            System.out.println("3. Generate Event Report");
            System.out.println("4. Frequent Pet Club");
            System.out.println("5. Return to Main Menu");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> System.out.println("Function: Adoption Event Management");
                case 2 -> System.out.println("Function: Vaccination/Spay-Neuter Events");
                case 3 -> System.out.println("Function: Frequent Pet Club");
                case 4 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }

    private static void History(Scanner scanner) {
        int option;
        do {
            System.out.println("\n--- History ---");
            System.out.println("1. View History");
            System.out.println("2. View Pet History");
            System.out.println("3. View Owner History");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> System.out.println("Function: Register Pet");
                case 2 -> System.out.println("Function: Update Pet Information");
                case 3 -> System.out.println("Function: Register Owner");
                case 4 -> System.out.println("Function: Transfer Pet");
                case 5 -> System.out.println("Function: View Pet History");
                case 6 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 6);
    }

    private static void Reports(Scanner scanner) {
        int option;
        do {
            System.out.println("\n--- Reports ---");
            System.out.println("1. Pets Attended");
            System.out.println("2. Most Requested Services");
            System.out.println("3. Employee Performance");
            System.out.println("4. Billing Report");
            System.out.println("5. Supplies Usage Report");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> System.out.println("Function: Pets Attended");
                case 2 -> System.out.println("Function: Most Requested Services");
                case 3 -> System.out.println("Function: Employee Performance");
                case 4 -> System.out.println("Function: Billing Report");
                case 5 -> System.out.println("Function: Supplies Usage Report");
                case 6 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 6);
    }

    private static void Transfers(Scanner scanner) {
        int option;
        do {
            System.out.println("\n--- Transfers ---");
            System.out.println("1. Change Animal Status");
            System.out.println("2. Animal Adoption Process");
            System.out.println("3. Sell Pet");
            System.out.println("4. Create Control Visit");
            System.out.println("5. Update Control Visit");
            System.out.println("6. View Pets for Adoption");
            System.out.println("7. View Pets for Sale");
            System.out.println("8. Generate Adoption Contract");
            System.out.println("9. Supplies Usage Report");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> System.out.println("Function: Change Animal Status");
                case 2 -> System.out.println("Function: Animal Adoption Process");
                case 3 -> System.out.println("Function: Sell Pet");
                case 4 -> System.out.println("Function: Create Control Visit");
                case 5 -> System.out.println("Function: Update Control Visit");
                case 6 -> System.out.println("Function: View Pets for Adoption");
                case 7 -> System.out.println("Function: View Pets for Sale");
                case 8 -> System.out.println("Function: Generate Adoption Contract");
                case 9 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 9);
    }

    private static void Alerts(Scanner scanner) {
        int option;
        do {
            System.out.println("\n--- Alerts ---");
            System.out.println("1. Pet Health Alerts");
            System.out.println("2. Pet Activity Alerts");
            System.out.println("3. Inventory Alerts");
            System.out.println("4. Report Alerts");
            System.out.println("5. Return to Main Menu");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> System.out.println("Function: Pet Health Alerts");
                case 2 -> System.out.println("Function: Pet Activity Alerts");
                case 3 -> System.out.println("Function: Inventory Alerts");
                case 4 -> System.out.println("Function: Report Alerts");
                case 5 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }
}