package com.mycompany.pets.model.classes;

import com.mycompany.pets.model.classes.superclasses.Animal;

public class Dog extends Animal {

    public Dog(int id, String name, String birthDate, String sex, double weight, String conditions, String allergies, String isAvailable, Owner owner, Identifier identifier) {
        super(id, name, birthDate, sex, weight, conditions, allergies, isAvailable, owner, identifier);
        setIDSpecie(1);
        setUrlPhoto("Dog.jpg");
    }

    @Override
    public void initializeCharacteristics() {
        addCharacteristic(new Characteristic("Breed", ""));
        addCharacteristic(new Characteristic("Color", ""));
        addCharacteristic(new Characteristic("Feeding", ""));
        addCharacteristic(new Characteristic("Size", ""));

    }

}
