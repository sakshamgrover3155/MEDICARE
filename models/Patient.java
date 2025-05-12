// models/Patient.java
package models;

import datastructures.LinkedList;

public class Patient extends User {
    private String name;
    private int age;
    private String disease;
    private String bloodType; // Add a field for blood type
    private LinkedList<HealthRecord> healthRecords = new LinkedList<>(); // Initialize health records

    public Patient(String id, String username, String password, String name, int age, String disease) {
        super(id, username, password);
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDisease() {
        return disease;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getBloodType() {
        return bloodType; // Return the blood type
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType; // Set the blood type
    }

    public LinkedList<HealthRecord> getHealthRecords() {
        return healthRecords; // Return the health records
    }

    public void addHealthRecord(HealthRecord record) {
        healthRecords.add(record); // Add a health record to the list
    }
}
