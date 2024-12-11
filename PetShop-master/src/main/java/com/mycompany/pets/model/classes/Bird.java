package com.mycompany.pets.model.classes;

import com.mycompany.pets.model.classes.superclasses.Animal;

public class Bird extends Animal {

    public Bird(int id, String name, String birthDate, String sex, double weight, String conditions, String allergies, String isAvailable, Owner owner, Identifier identifier) {
        super(id, name, birthDate, sex, weight, conditions, allergies, isAvailable, owner, identifier);
        setIDSpecie(3);
        setUrlPhoto("Bird.jpg");
    }

    @Override
    public void initializeCharacteristics() {
        addCharacteristic(new Characteristic("Color", ""));
        addCharacteristic(new Characteristic("Type", ""));
        addCharacteristic(new Characteristic("Habitat", ""));
        addCharacteristic(new Characteristic("Feeding", ""));
        addCharacteristic(new Characteristic("Size", ""));
        addCharacteristic(new Characteristic("Flight ability", ""));
    }

}
