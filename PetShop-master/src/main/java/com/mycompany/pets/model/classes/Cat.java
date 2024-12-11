package com.mycompany.pets.model.classes;

import com.mycompany.pets.model.classes.superclasses.Animal;

public class Cat extends Animal {

    public Cat(int id, String name, String birthDate, String sex, double weight, String conditions, String allergies, String isAvailable, Owner owner, Identifier identifier) {
        super(id, name, birthDate, sex, weight, conditions, allergies, isAvailable, owner, identifier);
        setIDSpecie(2);
        setUrlPhoto("Cat.jpg");
    }

    @Override
    public void initializeCharacteristics() {
        addCharacteristic(new Characteristic("Breed", "None"));
        addCharacteristic(new Characteristic("Color", "None"));
        addCharacteristic(new Characteristic("Feeding", "None"));
    }

}
