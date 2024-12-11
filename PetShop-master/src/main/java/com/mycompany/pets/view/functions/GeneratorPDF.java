package com.mycompany.pets.view.functions;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.mycompany.pets.controller.ControllerAnimal;
import com.mycompany.pets.model.classes.superclasses.Animal;


import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneratorPDF {
    /*public void generatePdfFromDatabase() {
        String filePath = "animals_report.pdf";

        // Llamamos al ControllerAnimal para obtener la lista de animales
        ControllerAnimal controller = new ControllerAnimal();
        List<Animal> animals = null;
        
        try {
            animals = controller.listAnimals(); // Obtener lista de animales desde la base de datos
        } catch (Exception e) {
            System.out.println("Error al obtener animales: " + e.getMessage());
            return;
        }

        // Creamos el documento PDF
        try {
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(filePath));
            Document document = new Document(pdfDoc);

            // Añadir un título al PDF
            document.add(new Paragraph("Animal Report").setFont(PdfFontFactory.createFont()).setFontSize(18));

            // Crear la tabla en el PDF
            float[] columnWidths = {1, 3, 2, 2};
            Table table = new Table(columnWidths);

            // Añadir encabezados a la tabla
            table.addHeaderCell(new Cell().add(new Paragraph("ID")));
            table.addHeaderCell(new Cell().add(new Paragraph("Name")));
            table.addHeaderCell(new Cell().add(new Paragraph("Weight")));
            table.addHeaderCell(new Cell().add(new Paragraph("Available")));

            // Procesar cada animal y agregarlo a la tabla
            for (Animal animal : animals) {
                table.addCell(String.valueOf(animal.getId())); // Asumiendo que tienes el método getId()
                table.addCell(animal.getName());
                table.addCell(String.valueOf(animal.getWeight()));
                table.addCell(animal.getIsAvailable()); // Asegúrate de que el animal tenga este campo o método
            }

            // Agregar la tabla al documento
            document.add(table);

            // Cerrar el documento
            document.close();
            System.out.println("PDF generado exitosamente: " + filePath);

        } catch (Exception e) {
            System.out.println("Error al generar el PDF: " + e.getMessage());
        }
    }*/
}