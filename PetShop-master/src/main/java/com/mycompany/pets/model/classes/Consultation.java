package com.mycompany.pets.model.classes;

import com.mycompany.pets.controller.ControllerConsultation;
import com.mycompany.pets.controller.ControllerType;
import com.mycompany.pets.model.classes.enums.Status;
import com.mycompany.pets.model.classes.superclasses.Service;
import com.mycompany.pets.model.classes.superclasses.Supply;
import com.mycompany.pets.model.classes.superclasses.Type;
import com.mycompany.pets.model.classes.utilities.Utility;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Consultation extends Service {

    private int idConsultation;
    private Type reason;
    private boolean isControl;
    private double price;
    // esta es la parte de appointment
    private String recommendations;
    private String diagnostic;
    private List<Service> services;
    private List<Supply> supplies;
    // /esto es lo que la convierte en consulta

    // Constructor privado para el Builder
    private Consultation(Builder builder) {
        super(builder.serviceId, builder.serviceDate, builder.pet, builder.employee, builder.typeService, builder.status, builder.paid);
        this.idConsultation = builder.idConsultation;
        this.reason = builder.reason;
        this.isControl = builder.isControl;
        this.price = builder.price;
        this.recommendations = builder.recommendations;
        this.diagnostic = builder.diagnostic;
        this.services = builder.services;
        this.supplies = builder.supplies;
    }

    // Getters
    public int getIdConsultation() {
        return idConsultation;
    }

    public Type getReason() {
        return reason;
    }

    public boolean getIsControl() {
        return isControl;
    }


    public double getPrice() {
        return price;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public List<Service> getServices() {
        return services;
    }

    public List<Supply> getSupplies() {
        return supplies;
    }

    public String getDetailsAppointment() {
        return super.toString()
                + "idConsultation: " + idConsultation
                + "\nreason: " + reason
                + "\nisControl: " + isControl
                + "\nprice: " + price
                + "\n-----------------\n";
    }

    public static int getMaxAppointmentId() {
        Optional<Integer> maxId = ControllerConsultation.listConsultations().stream()
                .map(Consultation::getIdConsultation)
                .max(Integer::compare);
        return maxId.orElseThrow(() -> new IllegalArgumentException("No services found!"));
    }

    public static Consultation registerConsultation(Scanner scanner) {
        Service baseService = Service.registerService(scanner);

        Type type = Type.assignType(scanner, "Reason");
        boolean isControl = Utility.askYesNo(scanner, "Is for some Control?");
        Consultation consultation = new Consultation.Builder(baseService)
                .setIdConsultation(getMaxAppointmentId())
                .setReason(type)
                .setIsControl(isControl)
                .setPrice(50.0)
                .build();
        return consultation;
    }

//    @Override
//    public String toString() {
//        return "Consultation{" +
//                "idConsultation=" + idConsultation +
//                ", reason=" + reason +
//                ", isControl=" + isControl +
//                ", status=" + status +
//                ", price=" + price +
//                ", recommendations='" + recommendations + '\'' +
//                ", diagnostic='" + diagnostic + '\'' +
//                ", services=" + services +
//                ", supplies=" + supplies +
//                "} " + super.toString();
//    }
    // Builder
    public static class Builder {

        // Atributos de Service
        private int serviceId;
        private LocalDateTime serviceDate;
        private Pet pet;
        private Employee employee;
        private Type typeService;
        private Status status;
        private boolean paid;
        

        // Atributos específicos de Consultation
        private int idConsultation;
        private Type reason;
        private boolean isControl;
        private double price;
        private String recommendations;
        private String diagnostic;
        private List<Service> services;
        private List<Supply> supplies;

        // Constructor para iniciar desde un objeto Service
        public Builder(Service service) {
            this.serviceId = service.getIdService();
            this.serviceDate = service.getDate();
            this.pet = service.getPet();
            this.employee = service.getEmployee();
            this.typeService = service.getTypeService();
        }

        // Constructor vacío para construir todo desde cero
        public Builder() {
        }
        
        
        
        // Métodos para los atributos de Service
        public Builder setServiceId(int serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder setServiceDate(LocalDateTime serviceDate) {
            this.serviceDate = serviceDate;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setPaid(boolean paid) {
            this.paid = paid;
            return this;
            
        }
        
        

        public Builder setPet(Pet pet) {
            this.pet = pet;
            return this;
        }

        public Builder setEmployee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public Builder setTypeService(Type typeService) {
            this.typeService = typeService;
            return this;
        }

        // Métodos para los atributos de Consultation
        public Builder setIdConsultation(int idConsultation) {
            this.idConsultation = idConsultation;
            return this;
        }

        public Builder setReason(Type reason) {
            this.reason = reason;
            return this;
        }

        public Builder setIsControl(boolean isControl) {
            this.isControl = isControl;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setRecommendations(String recommendations) {
            this.recommendations = recommendations;
            return this;
        }

        public Builder setDiagnostic(String diagnostic) {
            this.diagnostic = diagnostic;
            return this;
        }

        public Builder setServices(List<Service> services) {
            this.services = services;
            return this;
        }

        public Builder setSupplies(List<Supply> supplies) {
            this.supplies = supplies;
            return this;
        }

        // Método para construir el objeto Consultation
        public Consultation build() {
            return new Consultation(this);
        }
    }
}
