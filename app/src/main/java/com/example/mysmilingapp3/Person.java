package com.example.mysmilingapp3;

public class Person {
    private int id;
    private String name;
    private String email;
    private int happy;
    private int unhappy;
    private int nornal;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Person() {
    }

    public Person(int id, String name, String email, int happy, int unhappy, int nornal) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.happy = happy;
        this.unhappy = unhappy;
        this.nornal = nornal;
    }

    public Person(String name, String email, int happy, int unhappy, int nornal) {
        this.name = name;
        this.email = email;
        this.happy = happy;
        this.unhappy = unhappy;
        this.nornal = nornal;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getUnhappy() {
        return unhappy;
    }

    public void setUnhappy(int unhappy) {
        this.unhappy = unhappy;
    }

    public int getNornal() {
        return nornal;
    }

    public void setNornal(int nornal) {
        this.nornal = nornal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", happy=" + happy +
                ", unhappy=" + unhappy +
                ", nornal=" + nornal +
                '}';
    }
}
