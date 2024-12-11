package com.mycompany.pets.controller;

import com.mycompany.pets.model.classes.Bird;
import com.mycompany.pets.model.classes.Cat;
import com.mycompany.pets.model.classes.Characteristic;
import com.mycompany.pets.model.classes.Dog;
import com.mycompany.pets.model.classes.Identifier;
import com.mycompany.pets.model.classes.Owner;
import com.mycompany.pets.model.classes.superclasses.Animal;
import com.mycompany.pets.model.persistence.CRUD;
import com.mycompany.pets.model.persistence.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ControllerAnimal {

    // Metodo para insertar el animal en la base de datos (toda la información), recibe un objeto tipo Animal 
    //PRUEBA DE ACTUALIZACION
    public static boolean insertAnimal(Animal animal) {
        try {
            CRUD.setConnection(DBConnection.connectionDB());
            CRUD.setAutoCommitDB(false);

            // Paso 1: Insertar información básica del animal
            int animalID = insertBasicAnimalData(animal);

            // Paso 2: Insertar características específicas del animal
            insertAnimalCharacteristics(animalID, animal.getCharacteristics());

            // Paso 3: Insertar identificador (si aplica)
            if (animal.getIdentifier() != null) {
                insertAnimalIdentifier(animalID, animal.getIdentifier());
            }

            CRUD.commitDB(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            CRUD.rollbackDB(); // Revertir transacción en caso de error
            System.out.println("Error al insertar el animal: " + e.getMessage());
            return false;
        } finally {
            CRUD.closeCon(); // Cerrar conexión
        }
    }

    // Metodo para insertar información basica del animal a la base de datos (recibe un animal)
    private static int insertBasicAnimalData(Animal animal) throws SQLException {
        String insertAnimalSQL = """
            INSERT INTO Pets (name, dateBirth, sex, weight, conditions, allergies, isAvailable, urlPhoto, IDTypeSpecies, IDOwner)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        String conditionsValue = (animal.getConditions() != null && !animal.getConditions().isEmpty())
                ? animal.getConditions()
                : "";
        String allergiesValue = (animal.getAllergies() != null && !animal.getAllergies().isEmpty())
                ? animal.getAllergies()
                : "";

        List<Object> animalParams = List.of(
                animal.getName(),
                animal.getBirthDate(),
                animal.getSex(),
                animal.getWeight(),
                conditionsValue,
                allergiesValue,
                animal.getIsAvailable(),
                animal.getUrlPhoto(),
                animal.getIDSpecie(),
                animal.getOwner().getId()
        );

        if (CRUD.insertDB(insertAnimalSQL, animalParams)) {
            ResultSet rs = CRUD.consultDB("SELECT LAST_INSERT_ID()", List.of());
            if (rs != null && rs.next()) {
                return rs.getInt(1); // Retorna el ID generado
            }
        }
        throw new SQLException("Error al insertar datos básicos del animal.");
    }

    // Método para insertar las caracteristicas del animal en la base de datos (recibe el id de animal y la lista de caracteristicas) 
    private static void insertAnimalCharacteristics(int animalID, List<Characteristic> characteristics) throws SQLException {
        String insertCharacteristicSQL = """
            INSERT INTO PetsFeaturesPets (IDPet, IDFeaturePet, value)
            VALUES (?, ?, ?)
        """;

        for (Characteristic characteristic : characteristics) {
            int featureID = getFeatureID(characteristic.getName());
            List<Object> params = List.of(animalID, featureID, characteristic.getValue());
            if (!CRUD.insertDB(insertCharacteristicSQL, params)) {
                throw new SQLException("Error al insertar característica: " + characteristic.getName());
            }
        }
    }

    // Metodo para obtener el ID de una caracteristica (recibe el nombre de la caracteristica)
    private static int getFeatureID(String featureName) throws SQLException {
        String findFeatureSQL = "SELECT IDFeaturePet FROM FeaturesPets WHERE feature = ?";
        ResultSet rs = CRUD.consultDB(findFeatureSQL, List.of(featureName));
        if (rs != null && rs.next()) {
            return rs.getInt("IDFeaturePet");
        }
        throw new SQLException("Característica no encontrada: " + featureName);
    }

    // Método para insertar la identificación del animal a la base de datos (recibe el id del animal y objeto de identificador)
    private static void insertAnimalIdentifier(int animalID, Identifier identifier) throws SQLException {
        String insertIdentifierSQL = """
            INSERT INTO Identifiers (number, IDPet, IDTypeIdentifier)
            VALUES (?, ?, ?)
        """;

        List<Object> params = List.of(
                identifier.getNumber(),
                animalID,
                identifier.getIdTypeIdentifier()
        );

        if (!CRUD.insertDB(insertIdentifierSQL, params)) {
            throw new SQLException("Error al insertar identificador del animal.");
        }
    }

    // Method to list all animals
    public static List<Animal> listAnimals() {
        CRUD.setConnection(DBConnection.connectionDB());
        List<Animal> animals = new ArrayList<>();
        String query = "SELECT * FROM Pets";
        List<Object> parameters = new ArrayList<>();

        try {
            ResultSet rs = CRUD.consultDB(query, parameters);
            while (rs != null && rs.next()) {
                int animalId = rs.getInt("IDPet");
                int speciesId = rs.getInt("IDTypeSpecies");
                Owner owner = ControllerOwner.searchOwner(rs.getInt("IDOwner"));

                // Get the identifier for the animal
                Identifier identifier = getIdentifierForAnimal(animalId);

                // Create the animal with the identifier
                Animal animal = createAnimalBySpeciesId(speciesId, rs, owner, identifier);

                // Assign characteristics to the animal
                animal.setCharacteristics(getCharacteristicsForAnimal(animalId));

                // Add the animal to the list
                animals.add(animal);
            }
        } catch (Exception e) { // Changed SQLException to Exception
            System.out.println("Error listing animals: " + e.getMessage());
        } finally {
            CRUD.closeCon();
        }

        return animals;
    }

    // Crear animal según el tipo de especie
    public static Animal createAnimalBySpeciesId(int speciesId, ResultSet rs, Owner owner, Identifier identifier) throws SQLException {
        switch (speciesId) {
            case 1: // Perro
                return new Dog(
                        rs.getInt("IDPet"),
                        rs.getString("name"),
                        rs.getString("dateBirth"),
                        rs.getString("sex"),
                        rs.getDouble("weight"),
                        rs.getString("conditions"),
                        rs.getString("allergies"),
                        rs.getString("isAvailable"),
                        owner,
                        identifier // Se pasa el identificador al crear el animal
                );
            case 2: // Gato
                return new Cat(
                        rs.getInt("IDPet"),
                        rs.getString("name"),
                        rs.getString("dateBirth"),
                        rs.getString("sex"),
                        rs.getDouble("weight"),
                        rs.getString("conditions"),
                        rs.getString("allergies"),
                        rs.getString("isAvailable"),
                        owner,
                        identifier // Se pasa el identificador al crear el animal
                );
            case 3: // Pájaro
                return new Bird(
                        rs.getInt("IDPet"),
                        rs.getString("name"),
                        rs.getString("dateBirth"),
                        rs.getString("sex"),
                        rs.getDouble("weight"),
                        rs.getString("conditions"),
                        rs.getString("allergies"),
                        rs.getString("isAvailable"),
                        owner,
                        identifier // Se pasa el identificador al crear el animal
                );
            default:
                throw new IllegalArgumentException("ID de especie desconocido: " + speciesId);
        }
    }

    // Method to get characteristics for an animal
    public static List<Characteristic> getCharacteristicsForAnimal(int animalId) {
        List<Characteristic> characteristics = new ArrayList<>();
        String query = "SELECT f.feature, pf.value FROM FeaturesPets f JOIN PetsFeaturesPets pf ON f.IDFeaturePet = pf.IDFeaturePet WHERE pf.IDPet = ?";

        try {
            CRUD.setConnection(DBConnection.connectionDB());
            List<Object> parameters = new ArrayList<>();
            parameters.add(animalId);
            ResultSet rs = CRUD.consultDB(query, parameters);

            while (rs != null && rs.next()) {
                String featureName = rs.getString("feature");
                String featureValue = rs.getString("value");
                characteristics.add(new Characteristic(featureName, featureValue));
            }
        } catch (Exception e) { // Changed SQLException to Exception
            System.out.println("Error getting characteristics: " + e.getMessage());
        } finally {
            CRUD.closeCon();
        }

        return characteristics;
    }

    // Method to get the identifier for an animal
    public static Identifier getIdentifierForAnimal(int animalId) {
        String query = "SELECT number, IDTypeIdentifier FROM Identifiers WHERE IDPet = ?";
        List<Object> parameters = new ArrayList<>();
        parameters.add(animalId);

        try {
            CRUD.setConnection(DBConnection.connectionDB());
            ResultSet rs = CRUD.consultDB(query, parameters);

            if (rs != null && rs.next()) {
                String identifierNumber = rs.getString("number");
                int identifierTypeId = rs.getInt("IDTypeIdentifier");
                return new Identifier(identifierNumber, identifierTypeId, animalId);
            }
        } catch (Exception e) { // Changed SQLException to Exception
            System.out.println("Error getting identifier: " + e.getMessage());
        } finally {
            CRUD.closeCon();
        }

        return null;
    }

    // Método para actualizar la información del animal en la base de datos (recibe un objeto tipo Animal)
    public static boolean updateAnimal(Animal animal) {
        try {
            CRUD.setConnection(DBConnection.connectionDB());
            CRUD.setAutoCommitDB(false);

            // Paso 1: Actualizar la información básica del animal
            updateBasicAnimalData(animal);

            // Paso 2: Actualizar las características específicas del animal
            updateAnimalCharacteristics(animal.getId(), animal.getCharacteristics());

            // Paso 3: Actualizar el identificador (si aplica)
            if (animal.getIdentifier() != null) {
                updateAnimalIdentifier(animal.getId(), animal.getIdentifier());
            }

            CRUD.commitDB(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            CRUD.rollbackDB(); // Revertir transacción en caso de error
            System.out.println("Error al actualizar el animal: " + e.getMessage());
            return false;
        } finally {
            CRUD.closeCon(); // Cerrar conexión
        }
    }

// Método para actualizar la información básica del animal en la base de datos
    private static void updateBasicAnimalData(Animal animal) throws SQLException {
        String updateAnimalSQL = """
        UPDATE Pets
        SET name = ?, dateBirth = ?, sex = ?, weight = ?, conditions = ?, allergies = ?, isAvailable = ?, urlPhoto = ?, IDTypeSpecies = ?, IDOwner = ?
        WHERE IDPet = ?
    """;

        String conditionsValue = (animal.getConditions() != null && !animal.getConditions().isEmpty())
                ? animal.getConditions()
                : "";
        String allergiesValue = (animal.getAllergies() != null && !animal.getAllergies().isEmpty())
                ? animal.getAllergies()
                : "";

        List<Object> animalParams = List.of(
                animal.getName(),
                animal.getBirthDate(),
                animal.getSex(),
                animal.getWeight(),
                conditionsValue,
                allergiesValue,
                animal.getIsAvailable(),
                animal.getUrlPhoto(),
                animal.getIDSpecie(),
                animal.getOwner().getId(),
                animal.getId() // El ID del animal para identificar la fila a actualizar
        );

        if (!CRUD.updateDB(updateAnimalSQL, animalParams)) {
            throw new SQLException("Error al actualizar datos básicos del animal.");
        }
    }

// Método para actualizar las características del animal en la base de datos
    private static void updateAnimalCharacteristics(int animalID, List<Characteristic> characteristics) throws SQLException {
        // Primero eliminamos las características anteriores
        String deleteOldCharacteristicsSQL = "DELETE FROM PetsFeaturesPets WHERE IDPet = ?";
        List<Object> deleteParams = List.of(animalID);
        if (!CRUD.deleteDB(deleteOldCharacteristicsSQL, deleteParams)) {
            throw new SQLException("Error al eliminar características anteriores del animal.");
        }

        // Insertamos las nuevas características
        insertAnimalCharacteristics(animalID, characteristics);
    }

// Método para actualizar la identificación del animal en la base de datos
    private static void updateAnimalIdentifier(int animalID, Identifier identifier) throws SQLException {
        String updateIdentifierSQL = """
        UPDATE Identifiers
        SET number = ?, IDTypeIdentifier = ?
        WHERE IDPet = ?
    """;

        List<Object> params = List.of(
                identifier.getNumber(),
                identifier.getIdTypeIdentifier(),
                animalID
        );

        if (!CRUD.updateDB(updateIdentifierSQL, params)) {
            throw new SQLException("Error al actualizar identificador del animal.");
        }
    }

}