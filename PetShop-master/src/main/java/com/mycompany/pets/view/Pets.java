package com.mycompany.pets.view;

import com.mycompany.pets.controller.ControllerConsultation;
import com.mycompany.pets.controller.ControllerEmployee;
import com.mycompany.pets.controller.ControllerService;
import com.mycompany.pets.controller.ControllerType;
import com.mycompany.pets.model.classes.Employee;
import com.mycompany.pets.model.classes.enums.Status;
import com.mycompany.pets.model.classes.superclasses.Service;
import com.mycompany.pets.model.classes.superclasses.Type;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Pets {

    public static void main(String[] args) {
//        String type = "Supplies";
//        ControllerType.listTypes(type).forEach(System.out::println);
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("A number");
//        int num = scanner.nextInt();
//        Type t1 = ControllerType.searchType(num, type);
//        System.out.println(t1);
//        ControllerEmployee.listEmployees().forEach(System.out::println);
        for (Status status : Status.values()) {
            System.out.println(status);
        }
        System.out.println(Service.getMaxServiceId());

        Scanner scanner = new Scanner(System.in);
//        System.out.println("A number");
//        int num = scanner.nextInt();
//        Employee e1 = ControllerEmployee.searchEmployee(1);
//        System.out.println(e1);
//        ControllerService.addService(date, pet, e1, typeService)
        

//            // Registrar nuevos servicios sencillos de la clase padre
//        Service service = Service.registerService(scanner);
//        ControllerService.addService(service);
Type.assignType(scanner, "Service");
//ControllerService.listServices().forEach(System.out::println);
        
        // Consultar consultas como appointments
//        ControllerConsultation.listConsultations().forEach(e->System.out.println(e.getDetailsAppointment()));
    }
}
