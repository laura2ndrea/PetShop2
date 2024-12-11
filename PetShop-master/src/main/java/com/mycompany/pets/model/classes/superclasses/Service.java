package com.mycompany.pets.model.classes.superclasses;

import com.mycompany.pets.controller.ControllerService;
import static com.mycompany.pets.controller.ControllerService.listServices;
import com.mycompany.pets.model.classes.Employee;
import com.mycompany.pets.model.classes.Pet;
import com.mycompany.pets.model.classes.enums.Status;
import com.mycompany.pets.model.classes.superclasses.Type;
import com.mycompany.pets.model.classes.utilities.UtilityTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

public class Service {

    private int idService;
    private LocalDateTime date;
    private Pet pet;
    private Employee employee;
    private Type typeService;
    private Status status;
    private boolean paid;

    public Service(int idService, LocalDateTime date, Pet pet, Employee employee, Type typeService, Status status, boolean paid) {
        this.idService = idService;
        this.date = date;
        this.pet = pet;
        this.employee = employee;
        this.typeService = typeService;
        this.status = status;
        this.paid = paid;
    }

    public Service() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployeee(Employee employee) {
        this.employee = employee;
    }

    public Type getTypeService() {
        return typeService;
    }

    public void setTypeService(Type typeService) {
        this.typeService = typeService;
    }

    @Override
    public String toString() {
        return """
               Service Details:
               -----------------
               ID Service: """ + idService + "\n"
                + "Date: " + UtilityTime.convertDateString(date) + "\n"
                + "Pet: " + pet + "\n"
                + "Employee: " + employee + "\n"
                + "Paid: " + paid + "\n"
                + "Status: " + status + "\n"
                + "Type of Service: " + typeService + "\n";
    }
    public static int getMaxServiceId() {
        Optional<Integer> maxId = listServices().stream()
            .map(Service::getIdService) 
            .max(Integer::compare);
        return maxId.orElseThrow(() -> new IllegalArgumentException("No services found!"));
    }

    public static Service registerService(Scanner scanner) {
        LocalDateTime appoinment = UtilityTime.getValidatedDate(scanner);
        System.out.println("Enter pet name:");
        String petName = scanner.next();
        Pet pet = new Pet(petName);
        Employee employee = Employee.assignEmployee(scanner);
        Type type = Type.assignType(scanner, "Service");
        int max = getMaxServiceId() + 1;
        Service service = new Service(max, appoinment, pet, employee, type, Status.SCHEDULED, false);
        System.out.println("Service registered successfully:");
        System.out.println(service);
        return service;
    }

}
