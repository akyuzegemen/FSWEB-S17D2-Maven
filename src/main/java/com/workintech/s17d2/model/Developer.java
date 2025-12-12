package com.workintech.s17d2.model;


public class Developer {
    private String name;
    private int id;
    private double salary;
    private Experience experience;


    public Developer(int id, String name, double salary, Experience experience) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }
}
