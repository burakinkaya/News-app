package edu.sabanciuniv.cs310assignment;

import java.util.ArrayList;
import java.util.List;

public class Categories {

    public static List<Categories> objectList = new ArrayList<>();

    private int id;

    String name;

    public Categories(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Categories() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
