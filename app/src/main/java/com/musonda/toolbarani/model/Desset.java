package com.musonda.toolbarani.model;

import java.util.ArrayList;
import java.util.List;

public class Desset {
    private String name, description, firstLetter;

    public Desset(String name, String description) {
        this.name = name;
        this.firstLetter = String.valueOf(name.charAt(0));
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public static List<Desset> prepareDesserts(String[] names, String[] descriptions) {
        List<Desset> dessets = new ArrayList<>(names.length);

        for (int i = 0; i < names.length; i++) {
            Desset desset = new Desset(names[i], descriptions[i]);
            dessets.add(desset);
        }

        return dessets;
    }
}
