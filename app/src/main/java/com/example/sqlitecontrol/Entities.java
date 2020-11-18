package com.example.sqlitecontrol;

public class Entities {
    private int id;
    private String name;

    public Entities(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Entities() {
    }

    public Entities(String name) {
        this.name = name;
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

