package com.mycompany.pets.model.classes.utilities;

import java.util.List;
import java.util.Scanner;

public abstract class Utility {

    public static boolean askYesNo(Scanner scanner, String question) {
        int response;
        while (true) {
            System.out.println(question + "\n1. Yes, 2. No: ");
            try {
                response = Integer.parseInt(scanner.nextLine().trim());
                if (response == 1) {
                    return true;
                } else if (response == 2) {
                    return false;
                } else {
                    System.out.println("Invalid input. Please enter '1' for Yes or '2' for No.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number ('1' or '2').");
            }
        }
    }

    public static int getIntFromUser(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("¡Error! No es un número entero válido. Intenta nuevamente:");
            }
        }
    }

    public static String getValidInput(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("¡Error! La entrada no puede estar vacía. Intenta nuevamente.");
        }
    }

    // Utility methods for user input
    public static String promptString(Scanner scanner, String prompt) {
        System.out.print(prompt + ": ");
        String input = scanner.nextLine();

        // Si el scanner ha leído un salto de línea no deseado, lo consumimos
        if (input.isEmpty()) {
            input = scanner.nextLine(); // Consume el salto de línea residual
        }

        return input;
    }

    public static int promptInt(Scanner scanner, String prompt) {
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt + ": ");
                value = Integer.parseInt(scanner.nextLine());  // Intenta parsear el valor ingresado como un entero
                validInput = true;  // Si no ocurre excepción, el valor es válido
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número entero válido.");
            }
        }

        return value;
    }

    public static double promptDouble(Scanner scanner, String prompt) {
        double value = 0.0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt + ": ");
                value = Double.parseDouble(scanner.nextLine());  // Intenta parsear el valor ingresado como un double
                validInput = true;  // Si no ocurre excepción, el valor es válido
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número decimal válido.");
            }
        }

        return value;
    }

    public static String promptOption(Scanner scanner, String prompt, List<String> validOptions) {
        String input = "";
        boolean valid = false;

        // Mostrar la pregunta
        System.out.print(prompt + " (" + String.join("/", validOptions) + "): ");

        // Mientras no sea una opción válida, seguir pidiendo la entrada
        while (!valid) {
            input = scanner.nextLine().toUpperCase(); // Convertimos la entrada a mayúsculas para evitar errores de caso
            if (validOptions.contains(input)) {
                valid = true; // Si la opción es válida, salimos del bucle
            } else {
                System.out.println("Invalid option. Please enter one of the following: " + String.join("/", validOptions));
            }
        }

        return input.toUpperCase();
    }
}
